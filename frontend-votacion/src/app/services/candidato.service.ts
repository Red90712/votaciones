import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CandidatoService {
  private apiUrl = 'http://localhost:8080/sistema-votacion/api/candidatos';

  constructor(private http: HttpClient) {}

  obtenerCandidatos(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  obtenerCandidatosPorPartido(partidoId: number): Observable<any[]> {
  return this.http.get<any[]>(`http://localhost:8080/sistema-votacion/api/candidatos?partidoId=${partidoId}`);
}

}
