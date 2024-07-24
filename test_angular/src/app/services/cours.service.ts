import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cours } from '../models/cours';

@Injectable({
  providedIn: 'root',
})
export class CoursService {
  url = 'http://localhost:8080/factory/api/cours';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Cours[]> {
    return this.httpClient.get<Cours[]>(this.url);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public create(cours: Cours): Observable<Cours> {
    return this.httpClient.post<Cours>(this.url, cours);
  }

  public getById(id: number): Observable<Cours> {
    return this.httpClient.get<Cours>(`${this.url}/${id}`);
  }

  public getCountFormateurById(id: number): Observable<number> {
    return this.httpClient.get<number>(`${this.url}/countFormateur/${id}`);
  }

  public update(cours: Cours): Observable<Cours> {
    return this.httpClient.put<Cours>(`${this.url}/${cours.id}`, cours);
  }
}
