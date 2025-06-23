import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VotosResumenComponent } from './components/votos-resumen/votos-resumen.component';
import { RouterOutlet } from '@angular/router';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, VotosResumenComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend-votacion';
}
