import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Utilisateur } from '../models/utilisateur';

@Injectable({
  providedIn: 'root',
})
export class UtilisateurService {
  constructor(private http: HttpClient) {}

  public inscription(obj: any): Observable<Utilisateur> {
    return this.http
      .post('http://localhost:8080/factory/api/utilisateur/inscription', obj)
      .pipe(catchError(this.gestionErreur));
  }

  private gestionErreur(error: HttpErrorResponse) {
    if (error.status === 409) {
      // Conflict error, login already exists
      return throwError(() => new Error('Ce login existe dèjà'));
    } else {
      return throwError(() => new Error('Une erreur est survenue'));
    }
  }
}
