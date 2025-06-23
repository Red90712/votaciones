import { Routes } from '@angular/router';
import { VotosResumenComponent } from './components/votos-resumen/votos-resumen.component';
import { RegistroVotoComponent } from './components/registro-voto/registro-voto.component';

export const routes: Routes = [
  { path: '', component: VotosResumenComponent },
  { path: 'registro-voto', component: RegistroVotoComponent }
];
