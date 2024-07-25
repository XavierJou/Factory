import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DisponibiliteFormateur } from '../models/disponibilite-formateur';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DisponibiliteFormateurService {
  url = 'http://localhost:8080/factory/api/disponibiliteFormateur';
  constructor(private httpClient: HttpClient) {}

  public create(obj: any): Observable<DisponibiliteFormateur> {
    console.log(obj);
    return this.httpClient.post<DisponibiliteFormateur>(this.url, obj);
  }
}
