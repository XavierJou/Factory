import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Formateur } from '../models/formateur';

@Injectable({
  providedIn: 'root',
})
export class FormateurService {
  url = 'http://localhost:8080/factory/api/formateur';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Formateur[]> {
    return this.httpClient.get<Formateur[]>(this.url);
  }

  public getWithUtilisateur(): Observable<Formateur[]> {
    return this.httpClient.get<Formateur[]>(this.url + '/utilisateur');
  }

  public getWithDetails(): Observable<Formateur[]> {
    return this.httpClient.get<Formateur[]>(this.url + '/details');
  }

  public deleteSeul(id: number): Observable<void> {
    return this.httpClient.delete<void>(
      `http://localhost:8080/factory/api/formateur/${id}`
    );
  }

  public deleteAvecUtilisateur(id: number): Observable<void> {
    return this.httpClient.delete<void>(
      `http://localhost:8080/factory/api/formateur/avec-utilisateur/${id}`
    );
  }

  public nullIdUtilisateur(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/nullIdUtilisateur/${id}`);
  }

  public create(formateur: Formateur): Observable<Formateur> {
    return this.httpClient.post<Formateur>(this.url, formateur);
  }

  public getById(id: number): Observable<Formateur> {
    return this.httpClient.get<Formateur>(`${this.url}/${id}`);
  }

  public update(formateur: Formateur): Observable<Formateur> {
    return this.httpClient.put<Formateur>(
      `${this.url}/${formateur.id}`,
      formateur
    );
  }
}
