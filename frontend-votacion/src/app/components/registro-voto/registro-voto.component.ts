import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CandidatoService } from '../../services/candidato.service';
import { VeredaService } from '../../services/veredas.service';
import { PartidoService } from '../../services/partido.service';
import Swal from 'sweetalert2';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-registrar-voto',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './registro-voto.component.html',
  styleUrls: ['./registro-voto.component.scss']
})
export class RegistroVotoComponent {
  nombreVotante: string = '';
  veredaId: number | null = null;
  candidatoId: number | null = null;
  partidoId: number | null = null;

  veredas: any[] = [];
  partidos: any[] = [];
  candidatos: any[] = [];

  constructor(
    private veredaService: VeredaService,
    private candidatoService: CandidatoService,
    private partidoService: PartidoService,
    private http: HttpClient
  ) {}

  ngOnInit() {
    this.veredaService.obtenerVeredas().subscribe({
      next: (data) => this.veredas = data,
      error: () => Swal.fire('Error', 'No se pudieron cargar las veredas', 'error')
    });

    this.partidoService.obtenerPartidos().subscribe({
      next: (data) => this.partidos = data,
      error: () => Swal.fire('Error', 'No se pudieron cargar los partidos', 'error')
    });
  }

  obtenerCandidatosPorPartido(idPartido: number) {
    this.candidatoService.obtenerCandidatos().subscribe({
      next: (data) => this.candidatos = data,
      error: () => Swal.fire('Error', 'No se pudieron cargar los candidatos del partido', 'error')
    });

    this.http.get<any[]>('http://localhost:8080/sistema-votacion/api/partidos').subscribe({
      next: (data) => this.partidos = data,
      error: () => Swal.fire('Error', 'No se pudieron cargar los partidos', 'error')
    });
  }

  onPartidoChange() {
  this.candidatoId = null; 
  if (this.partidoId != null) {
    this.candidatoService.obtenerCandidatosPorPartido(this.partidoId).subscribe({
      next: (data) => this.candidatos = data,
      error: () => Swal.fire('Error', 'No se pudieron cargar los candidatos', 'error')
    });
  }
  }

  registrarVoto() {
    if (!this.nombreVotante || !this.veredaId || !this.candidatoId) {
      Swal.fire('Faltan campos', 'Por favor completa todos los datos', 'warning');
      return;
    }

    const body = {
      nombreVotante: `Votante ${this.nombreVotante.trim()}`,
      veredaId: Number(this.veredaId),
      candidatoId: Number(this.candidatoId)
    };

    console.log('Enviando voto:', body);

    this.http.post('http://localhost:8080/sistema-votacion/api/votos', body)
      .subscribe({
        next: () => {
          Swal.fire('Éxito', 'Voto registrado correctamente', 'success');
          this.limpiarFormulario();
        },
        error: (error) => {
          console.error('Error al registrar voto:', error);
          Swal.fire('Error', 'No se pudo registrar el voto', 'error');
        }
      });
  }

  limpiarFormulario() {
    this.nombreVotante = '';
    this.veredaId = null;
    this.partidoId = null;
    this.candidatoId = null;
    this.candidatos = [];
  }
}
