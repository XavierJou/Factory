import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Competence } from '../models/competence';

@Injectable({
  providedIn: 'root',
})
export class CompetenceService {
  url = 'http://localhost:8080/factory/api/competence';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Competence[]> {
    return this.httpClient.get<Competence[]>(this.url);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public create(competence: Competence): Observable<Competence> {
    return this.httpClient.post<Competence>(this.url, competence);
  }

  public getById(id: number): Observable<Competence> {
    return this.httpClient.get<Competence>(`${this.url}/${id}`);
  }

  public update(competence: Competence): Observable<Competence> {
    return this.httpClient.put<Competence>(
      `${this.url}/${competence.id}`,
      competence
    );
  }
}
