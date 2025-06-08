import {
  Component,
  Input,
  OnChanges,
  SimpleChanges,
  ViewChild
} from '@angular/core';
import { ChartConfiguration } from 'chart.js';
import { CommonModule } from '@angular/common';
import { NgChartsModule, BaseChartDirective } from 'ng2-charts';
import { ResumenVoto } from '../../services/votos.service';

@Component({
  selector: 'app-grafica-candidatos',
  standalone: true,
  imports: [CommonModule, NgChartsModule],
  template: `
    <canvas baseChart
      [data]="chartData"
      [type]="chartType"
      [options]="chartOptions">
    </canvas>
  `
})
export class GraficaCandidatosComponent implements OnChanges {
  @Input() datos: ResumenVoto[] = [];
  @ViewChild(BaseChartDirective) chart: BaseChartDirective | undefined;

  chartType: 'bar' = 'bar';

  chartOptions: ChartConfiguration<'bar'>['options'] = {
    responsive: true,
    plugins: {
      legend: {
        display: false
      }
    },
    animation: {
      duration: 30
    }
  };

  chartData: ChartConfiguration<'bar'>['data'] = {
    labels: [],
    datasets: [
      {
        label: 'Total de Votos por Candidato',
        data: [],
        backgroundColor: []
      }
    ]
  };

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['datos'] && this.datos?.length) {
      this.chartData.labels = this.datos.map(d => `${d.candidato} (${d.partido})`);
      this.chartData.datasets[0].data = this.datos.map(d => d.totalVotos);
      this.chartData.datasets[0].backgroundColor = this.datos.map(d =>
      this.colorPorPartido(d.partido)
      );

      setTimeout(() => this.chart?.update());
    }
  }

  colorPorPartido(partido: string): string {
    const colores: { [key: string]: string } = {
      'Partido A': '#4e73df',
      'Partido B': '#1cc88a',
      'Partido C': '#36b9cc',
      'Partido D': '#f6c23e',
      'Partido E': '#e74a3b',
      'Partido F': '#858796',
      'Partido G': '#fd7e14'
    };
    return colores[partido] || '#888';
  }
}
