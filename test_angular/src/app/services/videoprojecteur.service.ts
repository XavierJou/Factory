import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Videoprojecteur } from '../models/videoprojecteur';

@Injectable({
  providedIn: 'root',
})
export default class VideoprojecteurService {
  url = 'http://localhost:8080/demo/api/videoprojecteur';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Videoprojecteur[]> {
    return this.httpClient.get<Videoprojecteur[]>(this.url);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public create(videoprojecteur: Videoprojecteur): Observable<Videoprojecteur> {
    return this.httpClient.post<Videoprojecteur>(this.url, videoprojecteur);
  }

  public getById(id: number): Observable<Videoprojecteur> {
    return this.httpClient.get<Videoprojecteur>(`${this.url}/${id}`);
  }

  public update(videoprojecteur: Videoprojecteur): Observable<Videoprojecteur> {
    return this.httpClient.put<Videoprojecteur>(
      `${this.url}/${videoprojecteur.id}`,
      videoprojecteur
    );
  }
}
