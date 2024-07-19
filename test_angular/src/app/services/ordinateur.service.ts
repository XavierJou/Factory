import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ordinateur } from '../models/ordinateur';

@Injectable({
  providedIn: 'root',
})
export class OrdinateurService {
  url = 'http://localhost:8080/demo/api/ordinateur';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Ordinateur[]> {
    return this.httpClient.get<Ordinateur[]>(this.url);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public create(ordinateur: Ordinateur): Observable<Ordinateur> {
    return this.httpClient.post<Ordinateur>(this.url, ordinateur);
  }

  public getById(id: number): Observable<Ordinateur> {
    return this.httpClient.get<Ordinateur>(`${this.url}/${id}`);
  }

  public update(ordinateur: Ordinateur): Observable<Ordinateur> {
    return this.httpClient.put<Ordinateur>(
      `${this.url}/${ordinateur.id}`,
      ordinateur
    );
  }
}
