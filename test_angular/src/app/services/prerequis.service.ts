import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Prerequis } from '../models/prerequis';

@Injectable({
  providedIn: 'root',
})
export class PrerequisService {
  url = 'http://localhost:8080/factory/api/prerequis';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Prerequis[]> {
    return this.httpClient.get<Prerequis[]>(this.url);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public create(prerequis: Prerequis): Observable<Prerequis> {
    return this.httpClient.post<Prerequis>(this.url, prerequis);
  }

  public getById(id: number): Observable<Prerequis> {
    return this.httpClient.get<Prerequis>(`${this.url}/${id}`);
  }

  public update(prerequis: Prerequis): Observable<Prerequis> {
    return this.httpClient.put<Prerequis>(
      `${this.url}/${prerequis.id}`,
      prerequis
    );
  }
}
