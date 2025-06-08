import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { VotosService, ResumenVoto } from '../../services/votos.service';
import { GraficaVotosComponent } from '../grafica-votos/grafica-votos.component';

@Component({
  selector: 'app-votos-resumen',
  standalone: true,
  imports: [CommonModule, FormsModule, GraficaVotosComponent],
  templateUrl: './votos-resumen.component.html',
  styleUrls: ['./votos-resumen.component.scss']
})

export class VotosResumenComponent implements OnInit {
  resumenVotos: ResumenVoto[] = [];
  resumenFiltrado: ResumenVoto[] = [];

  filtro: string = '';
  tipoFiltro: 'partido' | 'candidato' | 'vereda' = 'partido';
  busquedaGeneral: string = '';

  modoVereda: boolean = false;

  constructor(private votosService: VotosService) {}

  ngOnInit(): void {
    this.votosService.obtenerResumen().subscribe(data => {
      this.resumenVotos = data;
      this.actualizarVista();
    });
  }

  alternarVista(): void {
    this.modoVereda = !this.modoVereda;
    this.actualizarVista();
  }

  actualizarVista(): void {
    const data = this.modoVereda ? this.agruparPorVereda(this.resumenVotos) : [...this.resumenVotos];
    this.resumenFiltrado = this.aplicarFiltroInterno(data);
  }

  aplicarFiltro(): void {
    this.actualizarVista(); // cada vez que cambie el filtro se actualiza con el modo actual
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
      const clave = `${item.partido}|${item.candidato}|${item.vereda}`;
      if (mapa.has(clave)) {
        mapa.get(clave)!.totalVotos += item.totalVotos;
      } else {
        mapa.set(clave, { ...item }); // clonamos para no modificar el original
      }
    }

    return Array.from(mapa.values());
  }
}

