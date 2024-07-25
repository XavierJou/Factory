import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CompetenceFormateur } from '../models/competence-formateur';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CompetenceFormateurService {
  url = 'http://localhost:8080/factory/api/competencesFormateur';
  constructor(private httpClient: HttpClient) {}

  public create(obj: any): Observable<CompetenceFormateur> {
    console.log(obj);
    return this.httpClient.post<CompetenceFormateur>(this.url, obj);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }
}
