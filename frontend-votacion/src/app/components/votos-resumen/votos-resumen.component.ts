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


  constructor(private votosService: VotosService) {}

  ngOnInit(): void {
    this.votosService.obtenerResumen().subscribe(data => {
      this.resumenVotos = data;
      this.resumenFiltrado = [...this.resumenVotos];
    });
  }

aplicarFiltro(): void {
    const f = this.filtro.toLowerCase();
    const b = this.busquedaGeneral.toLowerCase();

    this.resumenFiltrado = this.resumenVotos.filter(r => {
      const cumpleFiltroTipo = this.tipoFiltro === 'partido' ? r.partido.toLowerCase().includes(f)
                          : this.tipoFiltro === 'candidato' ? r.candidato.toLowerCase().includes(f)
                          : r.vereda.toLowerCase().includes(f);

      const cumpleBusqueda = r.partido.toLowerCase().includes(b)
                          || r.candidato.toLowerCase().includes(b)
                          || r.vereda.toLowerCase().includes(b);

      return cumpleFiltroTipo && cumpleBusqueda;
    });
  }
}