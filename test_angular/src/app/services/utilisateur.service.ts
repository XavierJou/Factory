import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Utilisateur } from '../models/utilisateur';

@Injectable({
  providedIn: 'root',
})
export class UtilisateurService {
  constructor(private http: HttpClient) {}

  public inscription(obj: any): Observable<Utilisateur> {
    return this.http.post(
      'http://localhost:8080/demo/api/utilisateur/inscription',
      obj
    );
  }
}
