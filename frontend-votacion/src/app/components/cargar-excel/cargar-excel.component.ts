import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import * as XLSX from 'xlsx';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-cargar-excel',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './cargar-excel.component.html',
  styleUrls: ['./cargar-excel.component.scss']
})
export class CargarExcelComponent {
  datosExcel: any[] = [];

  constructor(private http: HttpClient) {}

  onFileChange(event: any): void {
    const file = event.target.files[0];

    if (file) {
      const reader: FileReader = new FileReader();
      reader.onload = (e: any) => {
        const data: Uint8Array = new Uint8Array(e.target.result);
        const workbook: XLSX.WorkBook = XLSX.read(data, { type: 'array' });
        const hojaNombre: string = workbook.SheetNames[0];
        const hoja: XLSX.WorkSheet = workbook.Sheets[hojaNombre];
        this.datosExcel = XLSX.utils.sheet_to_json(hoja);
        console.log('Datos cargados:', this.datosExcel);
      };
      reader.readAsArrayBuffer(file);
    }
  }

guardarVotos() { 
  if (this.datosExcel.length === 0) {
    alert("No hay datos para guardar.");
    return;
  }

  let votosExitosos = 0;
  let votosOmitidos = 0;

  for (const voto of this.datosExcel) {
    const body = {
      nombreVotante: voto.nombreVotante,
      veredaId: Number(voto.veredaId),
      candidatoId: Number(voto.candidatoId)
    };

    this.http.post('http://localhost:8080/sistema-votacion/api/votos', body)
      .subscribe({
        next: () => {
          console.log('✅ Voto guardado:', body);
          votosExitosos++;
        },
        error: err => {
          if (err.status === 409) {
            console.warn('⚠️ Voto duplicado, omitido:', body.nombreVotante);
            votosOmitidos++;
          } else {
            console.error('❌ Error al guardar voto:', err);
          }
        }
      });
  }

  setTimeout(() => {
    alert(`✅ Votos guardados: ${votosExitosos}\n⚠️ Votos omitidos (duplicados): ${votosOmitidos}`);
  }, 1500);
}
}
