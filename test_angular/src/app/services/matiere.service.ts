import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Matiere } from '../models/matiere';

@Injectable({
  providedIn: 'root',
})
export class MatiereService {
  url = 'http://localhost:8080/demo/api/matiere';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Matiere[]> {
    return this.httpClient.get<Matiere[]>(this.url);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public create(matiere: Matiere): Observable<Matiere> {
    return this.httpClient.post<Matiere>(this.url, matiere);
  }

  public getById(id: number): Observable<Matiere> {
    return this.httpClient.get<Matiere>(`${this.url}/${id}`);
  }

  public update(matiere: Matiere): Observable<Matiere> {
    return this.httpClient.put<Matiere>(`${this.url}/${matiere.id}`, matiere);
  }
}
