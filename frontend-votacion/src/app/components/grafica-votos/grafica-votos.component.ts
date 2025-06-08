import { Component, Input, OnChanges } from '@angular/core';
import { ChartConfiguration, ChartType } from 'chart.js';
import { CommonModule } from '@angular/common';
import { NgChartsModule } from 'ng2-charts';

@Component({
  selector: 'app-grafica-votos',
  standalone: true,
  imports: [CommonModule, NgChartsModule],
  templateUrl: './grafica-votos.component.html'
})
export class GraficaVotosComponent implements OnChanges {
  @Input() datos: any[] = [];

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
      legend: {
        display: false
      }
    }
  };

public chartType: 'bar' = 'bar';

  ngOnChanges(): void {
    if (this.datos && this.datos.length > 0) {
      this.chartData.labels = this.datos.map(d => `${d.candidato} (${d.partido})`);
      this.chartData.datasets[0].data = this.datos.map(d => d.totalVotos);
    }
  }
}
