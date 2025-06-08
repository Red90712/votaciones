import { Component } from '@angular/core';
import { VotosResumenComponent } from './components/votos-resumen/votos-resumen.component';

@Component({
  selector: 'app-root',
  imports: [VotosResumenComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'frontend-votacion';
}
