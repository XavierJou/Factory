import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Formation } from '../models/formation';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  url = 'http://localhost:8080/factory/api/dashboard';

  constructor(private httpClient: HttpClient) {}

  public getAllFormations(): Observable<Formation[]> {
    return this.httpClient.get<Formation[]>(this.url);
  }

  public getById(id: number): Observable<Formation> {
    return this.httpClient.get<Formation>(`${this.url}/${id}`);
  }
}
