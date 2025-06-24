import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VotosResumenComponent } from './components/votos-resumen/votos-resumen.component';
import { RouterOutlet } from '@angular/router';
import { CargarExcelComponent } from "./components/cargar-excel/cargar-excel.component";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend-votacion';
}
