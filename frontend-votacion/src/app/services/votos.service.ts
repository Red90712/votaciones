import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface ResumenVoto {
  partido: string;
  candidato: string;
  vereda: string;
  totalVotos: number;
}

@Injectable({
  providedIn: 'root'
})
export class VotosService {
  private apiUrl = 'http://localhost:8080/sistema-votacion/api/resumen-votos'; 

  constructor(private http: HttpClient) {}

  obtenerResumen(): Observable<ResumenVoto[]> {
    return this.http.get<ResumenVoto[]>(this.apiUrl);
  }
}
