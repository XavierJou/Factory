import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Cours } from '../../../models/cours';

import {
  Router,
  RouterLink,
  RouterLinkActive,
  ActivatedRoute,
} from '@angular/router';
import { CoursService } from '../../../services/cours.service';
import { AsyncPipe } from '@angular/common';
import { Observable } from 'rxjs';
import { Formation } from '../../../models/formation';
import { FormationService } from '../../../services/formation.service';

@Component({
  selector: 'app-edit-cours',
  standalone: true,
  imports: [FormsModule, RouterLink, RouterLinkActive, AsyncPipe],
  templateUrl: './edit-cours.component.html',
  styleUrl: './edit-cours.component.css',
})
export class EditCoursComponent implements OnInit {
  cours: Cours = new Cours();

  formationsObservable!: Observable<Formation[]>;

  constructor(
    private coursSrv: CoursService,
    private formationSrv: FormationService,
    private router: Router,
    private ActivatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.formationsObservable = this.formationSrv.getAll();
    this.ActivatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.coursSrv.getById(params['id']).subscribe((cours) => {
          this.cours = cours;
        });
      }
    });
  }

  save() {
    if (this.cours.id) {
      this.coursSrv.update(this.cours).subscribe((cours) => {
        this.router.navigateByUrl('/cours');
      });
    } else {
      this.coursSrv.create(this.cours).subscribe((cours) => {
        this.router.navigateByUrl('/cours');
      });
    }
  }

  compareFn(f1: Formation, f2: Formation): boolean {
    if (f1 && f2) {
      return f1.id === f2.id;
    } else {
      return false;
    }
  }
}
