import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Utilisateur } from '../models/utilisateur';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private httpClient: HttpClient) {}

  public connexion(login: string, password: string): Observable<Utilisateur> {
    let auth = 'Basic ' + window.btoa(login + ':' + password);

    return this.httpClient.get<Utilisateur>(
      'http://localhost:8080/factory/api/auth',
      {
        headers: { Authorization: auth },
      }
    );
  }
}
