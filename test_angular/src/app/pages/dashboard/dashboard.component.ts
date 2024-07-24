import { Prerequis } from './../../../../../../factory-angular-sauv04/src/app/models/prerequis';
import { AsyncPipe, DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {
  Router,
  RouterLink,
  RouterLinkActive,
  ActivatedRoute,
} from '@angular/router';
import { Observable } from 'rxjs';
import { Formation } from '../../models/formation';
import { DashboardService } from '../../services/dashboard.service';

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
  }

  // nbPrerequis = this.formation.prerequis?.map((p) => p.id).length;

  onChangeSelect(optionsValue: any) {}

  compareFn(ent1: Formation, ent2: Formation): boolean {
    if (ent1 && ent2) {
      return ent1.id === ent2.id;
    } else {
      return false;
    }
  }
}
