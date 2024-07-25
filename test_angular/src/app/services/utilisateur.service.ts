import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Utilisateur } from '../models/utilisateur';
import { UtilisateurInscription } from '../models/utilisateur-inscription';
import { Formateur } from '../models/formateur';

@Injectable({
  providedIn: 'root',
})
export class UtilisateurService {
  url = 'http://localhost:8080/factory/api/utilisateur';

  constructor(private httpClient: HttpClient) {}

  public inscription(
    utilisateurInscription: UtilisateurInscription
  ): Observable<Utilisateur> {
    console.log(utilisateurInscription);
    return this.httpClient
      .post<UtilisateurInscription>(
        this.url + '/inscription',
        utilisateurInscription
      )
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

  public getWtihDetail(): Observable<Utilisateur[]> {
    return this.httpClient.get<Utilisateur[]>(this.url + '/details');
  }

  public getById(id: number): Observable<Utilisateur> {
    return this.httpClient.get<Utilisateur>(`${this.url}/${id}`);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public nullIdFormateur(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/nullIdFormateur/${id}`);
  }

  public nullIdStagiaire(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/nullIdStagiaire/${id}`);
  }

  private utilsateurToUtilisateurRequest(utilisateur: Utilisateur): any {
    let ajoutFormateur = false;
    let ajoutStagiaire = false;

    if (utilisateur.formateur) {
      ajoutFormateur = true;
    }

    if (utilisateur.stagiaire) {
      ajoutStagiaire = true;
    }

    let obj = {
      id: utilisateur.id,
      login: utilisateur.login,
      prenom: utilisateur.prenom,
      nom: utilisateur.nom,
      email: utilisateur.email,
      role: utilisateur.role,
      ajoutFormateur: ajoutFormateur,
      ajoutStagiaire: ajoutStagiaire,
    };
    return obj;
  }

  public update(utilisateur: Utilisateur): Observable<Utilisateur> {
    return this.httpClient.put<Utilisateur>(
      `${this.url}/${utilisateur.id}`,
      this.utilsateurToUtilisateurRequest(utilisateur)
    );
  }
}
