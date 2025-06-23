import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VeredaService {
  private apiUrl = 'http://localhost:8080/sistema-votacion/api/veredas';

  constructor(private http: HttpClient) {}

  obtenerVeredas(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
