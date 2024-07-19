import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Formation } from '../models/formation';

@Injectable({
  providedIn: 'root',
})
export class FormationService {
  url = 'http://localhost:8080/demo/api/formation';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Formation[]> {
    return this.httpClient.get<Formation[]>(this.url);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public create(formation: Formation): Observable<Formation> {
    return this.httpClient.post<Formation>(this.url, formation);
  }

  public getById(id: number): Observable<Formation> {
    return this.httpClient.get<Formation>(`${this.url}/${id}`);
  }

  public update(formation: Formation): Observable<Formation> {
    return this.httpClient.put<Formation>(
      `${this.url}/${formation.id}`,
      formation
    );
  }
}
