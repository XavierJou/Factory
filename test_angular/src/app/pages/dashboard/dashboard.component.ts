import { AsyncPipe, DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { Observable } from 'rxjs';
import { Formation } from '../../models/formation';
import { DashboardService } from '../../services/dashboard.service';
import { Utilisateur } from '../../models/utilisateur';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [FormsModule, RouterLink, RouterLinkActive, AsyncPipe, DatePipe],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent implements OnInit {
  formation: Formation = new Formation();

  formationsObservable!: Observable<Formation[]>;

  constructor(private dashboardSrv: DashboardService) {}

  ngOnInit(): void {
    this.formationsObservable = this.dashboardSrv.getAllFormations();
    this.servedCount = this.getServedCount(this.formation);
  }

  public servedCount = 0;

  private getServedCount(formation: Formation): number {
    let count = 0;
    formation.cours?.forEach((c) => {
      if (c.formateur !== null) {
        count++;
      }
    });
    return count;
  }

  onChangeSelect(optionsValue: any) {
    this.servedCount = this.getServedCount(optionsValue);
  }

  compareFn(ent1: Formation, ent2: Formation): boolean {
    if (ent1 && ent2) {
      return ent1.id === ent2.id;
    } else {
      return false;
    }
  }
}
