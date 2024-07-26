import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Salle } from '../models/salle';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class SalleService {
  url = 'http://localhost:8080/factory/api/salle';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Salle[]> {
    return this.httpClient.get<Salle[]>(this.url);
  }

  public getByNom(): Observable<Salle> {
    return this.httpClient.get<Salle>(this.url + '/recherche-nom');
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

  public searchByNom(search: string): Observable<Salle[]> {
    let params = new HttpParams().set('search', search);
    return this.httpClient.get<Salle[]>(this.url, { params });
  }
}
