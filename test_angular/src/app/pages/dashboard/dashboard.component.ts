import { AsyncPipe, DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { Observable } from 'rxjs';
import { Formation } from '../../models/formation';
import { DashboardService } from '../../services/dashboard.service';
import { Utilisateur } from '../../models/utilisateur';
import { PlanificationComponent } from '../../components/planification/planification.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink,
    RouterLinkActive,
    AsyncPipe,
    DatePipe,
    PlanificationComponent,
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent implements OnInit {
  formation: Formation = new Formation();
  formationsObservable!: Observable<Formation[]>;

  constructor(private dashboardSrv: DashboardService) {}

  get role(): string {
    let u: Utilisateur = JSON.parse(localStorage.getItem('utilisateur')!);
    return u.role!;
  }
  public servedCountFormateurs = 0;
  public servedCountMatieres = 0;

  ngOnInit(): void {
    this.formationsObservable = this.dashboardSrv.getAllFormations();
    this.servedCountFormateurs = this.getServedCountFormateurs(this.formation);
    this.servedCountMatieres = this.getServedCountMatieres(this.formation);
  }

  private getServedCountFormateurs(formation: Formation): number {
    let count = 0;
    formation.cours?.forEach((c) => {
      if (c.formateur !== null) {
        count++;
      }
    });
    return count;
  }

  private getServedCountMatieres(formation: Formation): number {
    let count = 0;
    formation.cours?.forEach((c) => {
      if (c.matiere !== null) {
        count++;
      }
    });
    return count;
  }

  onChangeSelect(optionsValue: any) {
    this.servedCountFormateurs = this.getServedCountFormateurs(optionsValue);
    this.servedCountMatieres = this.getServedCountMatieres(optionsValue);
  }

  compareFn(ent1: Formation, ent2: Formation): boolean {
    if (ent1 && ent2) {
      return ent1.id === ent2.id;
    } else {
      return false;
    }
  }
}
