import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cours } from '../models/cours';

@Injectable({
  providedIn: 'root',
})
export class CoursService {
  url = 'http://localhost:8080/factory/api/cours';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Cours[]> {
    return this.httpClient.get<Cours[]>(this.url);
  }

  public getWithOutFormateur(id: number): Observable<Cours[]> {
    return this.httpClient.get<Cours[]>(`${this.url}/sans-formateur/${id}`);
  }

  public getAllFromFormation(): Observable<Cours[]> {
    return this.httpClient.get<Cours[]>(`${this.url}/formation`);
  }

  public getAllMatieresFromFormation(id: number): Observable<Cours[]> {
    return this.httpClient.get<Cours[]>(`${this.url}/formation/matieres/${id}`);
  }

  public create(cours: Cours): Observable<Cours> {
    return this.httpClient.post<Cours>(
      this.url,
      this.coursToCoursRequest(cours)
    );
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public getById(id: number): Observable<Cours> {
    return this.httpClient.get<Cours>(`${this.url}/${id}/details`);
  }

  public getCountFormateurById(id: number): Observable<number> {
    return this.httpClient.get<number>(`${this.url}/countFormateur/${id}`);
  }

  public update(cours: Cours): Observable<Cours> {
    return this.httpClient.put<Cours>(
      `${this.url}/${cours.id}`,
      this.coursToCoursRequest(cours)
    );
  }

  private coursToCoursRequest(cours: Cours): any {
    let obj = {
      titre: cours.titre,
      dateDebut: cours.dateDebut,
      besoinOrdiFormateur: cours.besoinOrdiFormateur,
      besoinOrdiStagiaire: cours.besoinOrdiStagiaire,
      besoinSalle: cours.besoinSalle,
      besoinVideoprojecteur: cours.besoinVideoprojecteur,
      idFormation: cours.formation?.id,
      idFormateur: cours.formateur?.id,
      idMatiere: cours.matiere?.id,
      idSalle: cours.salle?.id,
      idsOrdinateurs: cours.ordinateurs?.map((o) => o.id),
      idVideoprojecteur: cours.videoprojecteur?.id,
    };
    return obj;
  }
}
