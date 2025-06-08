import { Component, Input, OnChanges, SimpleChanges, ChangeDetectorRef } from '@angular/core';
import { ChartConfiguration } from 'chart.js';
import { CommonModule } from '@angular/common';
import { NgChartsModule } from 'ng2-charts';
import { ResumenVoto } from '../../services/votos.service';

@Component({
  selector: 'app-grafica-votos',
  standalone: true,
  imports: [CommonModule, NgChartsModule],
  templateUrl: './grafica-votos.component.html',
  styleUrls: ['./grafica-votos.component.scss']
})
export class GraficaVotosComponent implements OnChanges {
  @Input() datos: ResumenVoto[] = [];

  public chartData: ChartConfiguration<'bar'>['data'] = {
    labels: [],
    datasets: [
      {
        label: 'Total de Votos',
        data: [],
        backgroundColor: '#4e73df'
      }
    ]
  };

  public chartOptions: ChartConfiguration<'bar'>['options'] = {
    responsive: true,
    plugins: {
      legend: { display: false },
    },
    animation: {
      duration: 800,
      easing: 'easeInOutQuart'
    }
  };

  public chartType: 'bar' = 'bar';

  constructor(private cdr: ChangeDetectorRef) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['datos'] && this.datos?.length > 0) {
      this.chartData.labels = this.datos.map(d => `${d.candidato} (${d.partido})`);
      this.chartData.datasets[0].data = this.datos.map(d => d.totalVotos);
      this.cdr.detectChanges(); // 🔁 Forzar detección de cambios
    }
  }
}
