import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { VotosService, ResumenVoto } from '../../services/votos.service';
import { GraficaVotosComponent } from '../grafica-votos/grafica-votos.component';
import { GraficaCandidatosComponent } from '../grafica-candidatos/grafica-candidatos.component';
import { Router } from '@angular/router';


@Component({
  selector: 'app-votos-resumen',
  standalone: true,
  imports: [CommonModule, FormsModule, GraficaVotosComponent, GraficaCandidatosComponent],
  templateUrl: './votos-resumen.component.html',
  styleUrls: ['./votos-resumen.component.scss'],
})

export class VotosResumenComponent implements OnInit {
  resumenVotos: ResumenVoto[] = [];
  resumenFiltrado: ResumenVoto[] = [];
  datos: ResumenVoto[] = [];

  filtro: string = '';
  tipoFiltro: 'partido' | 'candidato' | 'vereda' = 'partido';
  busquedaGeneral: string = '';

  modoVereda: boolean = false;

  constructor(private votosService: VotosService,
              private router: Router
              
  ) {}

irACargarExcel() {
  this.router.navigate(['/cargar-excel']);
}
irARegistroVoto() {
  this.router.navigate(['/registro-voto']);
}


cargarDatos(): void {
  const observable = this.modoVereda
    ? this.votosService.obtenerResumenPorVereda()
    : this.votosService.obtenerResumen();

  observable.subscribe(data => {
    this.resumenVotos = data;
    this.actualizarVista();
  });
}

ngOnInit(): void {
  this.cargarDatos(); 
}

alternarVista(): void {
  this.modoVereda = !this.modoVereda;
  this.cargarDatos();
}

actualizarVista(): void {
  let data = this.modoVereda
    ? this.agruparPorVereda(this.resumenVotos)
    : [...this.resumenVotos];

  for (const item of data) {
    item.candidato = this.limpiarNombreCandidato(item.candidato);
  }

  if (this.modoVereda) {
    data = this.ordenarPorPartidoCandidatoVereda(data);
  }

  this.resumenFiltrado = this.aplicarFiltroInterno(data);

  this.datos = [...this.resumenFiltrado];
}

  limpiarNombreCandidato(nombre: string): string {
  return nombre.split(' (')[0].trim();
}

  aplicarFiltro(): void {
    this.actualizarVista(); 
  }

  ordenarPorPartidoCandidatoVereda(data: ResumenVoto[]): ResumenVoto[] {
  return data.sort((a, b) => {
    const partidoComp = a.partido.localeCompare(b.partido);
    if (partidoComp !== 0) return partidoComp;

    const candidatoComp = a.candidato.localeCompare(b.candidato);
    if (candidatoComp !== 0) return candidatoComp;

    const numA = parseInt(a.vereda.replace(/\D/g, '')) || 0;
    const numB = parseInt(b.vereda.replace(/\D/g, '')) || 0;
    return numA - numB;
  });
}

  aplicarFiltroInterno(data: ResumenVoto[]): ResumenVoto[] {
    const f = this.filtro.toLowerCase();
    const b = this.busquedaGeneral.toLowerCase();

    return data.filter(r => {
      const cumpleFiltroTipo = this.tipoFiltro === 'partido' ? r.partido.toLowerCase().includes(f)
        : this.tipoFiltro === 'candidato' ? r.candidato.toLowerCase().includes(f)
        : r.vereda.toLowerCase().includes(f);

      const cumpleBusqueda = r.partido.toLowerCase().includes(b)
        || r.candidato.toLowerCase().includes(b)
        || r.vereda.toLowerCase().includes(b);

      return cumpleFiltroTipo && cumpleBusqueda;
    });
  }

agruparPorVereda(data: ResumenVoto[]): ResumenVoto[] {
  const mapa = new Map<string, ResumenVoto>();

  for (const item of data) {
    
    const veredaMatch = item.vereda.match(/\(Vereda\s+\d+\)/);
    const vereda = veredaMatch ? veredaMatch[0].replace(/[()]/g, '').trim() : item.vereda;

    const clave = `${item.partido}|${item.candidato}|${vereda}`;

    if (mapa.has(clave)) {
      mapa.get(clave)!.totalVotos += item.totalVotos;
    } else {
      mapa.set(clave, { ...item, vereda });
    }
  }

  return Array.from(mapa.values());
}

trackResumen(index: number, item: ResumenVoto): string {
  return `${item.partido}-${item.candidato}-${item.vereda}`;
}

get resumenPorCandidato(): ResumenVoto[] {
  const mapa = new Map<string, ResumenVoto>();

  for (const voto of this.resumenVotos) {
    const clave = `${voto.partido}|${voto.candidato}`;
    if (!mapa.has(clave)) {
      mapa.set(clave, { ...voto });
    } else {
      mapa.get(clave)!.totalVotos += voto.totalVotos;
    }
  }

  return Array.from(mapa.values());
}


}