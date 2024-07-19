import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { StagiaireService } from '../../../services/stagiaire.service';
import {
  Router,
  RouterLink,
  RouterLinkActive,
  ActivatedRoute,
} from '@angular/router';

import { Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { FormationService } from '../../../services/formation.service';
import { Stagiaire } from '../../../models/stagiaire';
import { Formation } from '../../../models/formation';

@Component({
  selector: 'app-edit-stagiaire',
  standalone: true,
  imports: [FormsModule, RouterLink, RouterLinkActive, AsyncPipe],
  templateUrl: './edit-stagiaire.component.html',
  styleUrl: './edit-stagiaire.component.css',
})
export class EditStagiaireComponent implements OnInit {
  stagiaire: Stagiaire = new Stagiaire();
  formationsObservable!: Observable<Formation[]>;

  constructor(
    private stagiaireSrv: StagiaireService,
    private formationSrv: FormationService,
    private router: Router,
    private ActivatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.formationsObservable = this.formationSrv.getAll();

    this.ActivatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.stagiaireSrv.getById(params['id']).subscribe((stagiaire) => {
          this.stagiaire = stagiaire;
        });
      }
    });
  }

  save() {
    if (this.stagiaire.id) {
      this.stagiaireSrv.update(this.stagiaire).subscribe((stagiaire) => {
        this.router.navigateByUrl('/stagiaire?q=update&id=' + stagiaire.id);
      });
    } else {
      this.stagiaireSrv.create(this.stagiaire).subscribe((stagiaire) => {
        this.router.navigateByUrl('/stagiaire?q=create&id=' + stagiaire.id);
      });
    }
  }
  // select input formations du stagiaire
  compareFn(f1: Formation, f2: Formation): boolean {
    return f1 && f2 ? f1.id === f2.id : false;
  }
}
