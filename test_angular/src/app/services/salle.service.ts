import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Salle } from '../models/salle';

@Injectable()
export class SalleService {
  url = 'http://localhost:8080/factory/api/salle';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Salle[]> {
    return this.httpClient.get<Salle[]>(this.url);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public create(salle: Salle): Observable<Salle> {
    return this.httpClient.post<Salle>(this.url, salle);
  }

  public getById(id: number): Observable<Salle> {
    return this.httpClient.get<Salle>(`${this.url}/${id}`);
  }

  public update(salle: Salle): Observable<Salle> {
    return this.httpClient.put<Salle>(`${this.url}/${salle.id}`, salle);
  }
}
