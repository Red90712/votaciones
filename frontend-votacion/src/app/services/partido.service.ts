import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PartidoService {
  private apiUrl = 'http://localhost:8080/sistema-votacion/api/partidos';

  constructor(private http: HttpClient) {}

  obtenerPartidos(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
