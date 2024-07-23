import { AsyncPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Form, FormsModule } from '@angular/forms';
import {
  Router,
  RouterLink,
  RouterLinkActive,
  ActivatedRoute,
} from '@angular/router';
import { Observable } from 'rxjs';
import { Formation } from '../../models/formation';
import { FormationService } from '../../services/formation.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [FormsModule, RouterLink, RouterLinkActive, AsyncPipe],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent implements OnInit {
  formation: Formation = new Formation();

  formationsObservable!: Observable<Formation[]>;

  constructor(
    private formationSrv: FormationService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.formationsObservable = this.formationSrv.getAll();
  }

  onChangeSelect(optionsValue: any) {}

  compareFn(ent1: Formation, ent2: Formation): boolean {
    if (ent1 && ent2) {
      return ent1.id === ent2.id;
    } else {
      return false;
    }
  }
}
