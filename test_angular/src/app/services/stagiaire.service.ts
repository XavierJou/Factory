import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Stagiaire } from '../models/stagiaire';

@Injectable({
  providedIn: 'root',
})
export class StagiaireService {
  url = 'http://localhost:8080/factory/api/stagiaire';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Stagiaire[]> {
    return this.httpClient.get<Stagiaire[]>(this.url);
  }

  public getWithDetails(): Observable<Stagiaire[]> {
    return this.httpClient.get<Stagiaire[]>(this.url + '/details');
  }

  // public getAllFromFormation(): Observable<Stagiaire[]> {
  //   return this.httpClient.get<Stagiaire[]>(`${this.url}/formation`);
  // }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public nullIdUtilisateur(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/nullIdUtilisateur/${id}`);
  }

  public create(stagiaire: Stagiaire): Observable<Stagiaire> {
    return this.httpClient.post<Stagiaire>(
      this.url,
      this.stagiaireToStagiaireRequest(stagiaire)
    );
  }

  public getById(id: number): Observable<Stagiaire> {
    return this.httpClient.get<Stagiaire>(`${this.url}/${id}`);
  }

  public update(stagiaire: Stagiaire): Observable<Stagiaire> {
    return this.httpClient.put<Stagiaire>(
      `${this.url}/${stagiaire.id}`,
      this.stagiaireToStagiaireRequest(stagiaire)
    );
  }

  private stagiaireToStagiaireRequest(stagiaire: Stagiaire): any {
    return {
      idFormation: stagiaire.formation?.id,
    };
  }
}
