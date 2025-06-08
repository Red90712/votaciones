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
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-grafica-votos',
  standalone: true,
  imports: [CommonModule, NgChartsModule, FormsModule],
  templateUrl: './grafica-votos.component.html'
})
export class GraficaVotosComponent implements OnChanges {
  @Input() datos: ResumenVoto[] = [];

  @ViewChild(BaseChartDirective) chart: BaseChartDirective | undefined;

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
    },
    animation: {
      duration: 500
    }
  };

  public chartType: 'bar' = 'bar';

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['datos'] && this.datos?.length) {
      console.log('Actualizando gráfica con:', this.datos);

      this.chartData.labels = this.datos.map(
        d => `${d.candidato} (${d.vereda})`
      );
      this.chartData.datasets[0].data = this.datos.map(d => d.totalVotos);

      setTimeout(() => {
        this.chart?.update();
      });
    }
  }
}
