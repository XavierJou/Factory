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
import { MatiereService } from '../../../services/matiere.service';
import { Matiere } from '../../../models/matiere';
import { Formateur } from '../../../models/formateur';
import { FormateurService } from '../../../services/formateur.service';
import { Salle } from '../../../models/salle';
import { Videoprojecteur } from '../../../models/videoprojecteur';
import { SalleService } from '../../../services/salle.service';
import VideoprojecteurService from '../../../services/videoprojecteur.service';
import { Ordinateur } from '../../../models/ordinateur';
import { OrdinateurService } from '../../../services/ordinateur.service';

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
  formateursObservable!: Observable<Formateur[]>;
  matieresObservable!: Observable<Matiere[]>;
  sallesObservable!: Observable<Salle[]>;
  videoprojecteursObservable!: Observable<Videoprojecteur[]>;
  ordinateursObservable!: Observable<Ordinateur[]>;

  constructor(
    private coursSrv: CoursService,
    private formationSrv: FormationService,
    private formateurSrv: FormateurService,
    private matiereSrv: MatiereService,
    private salleSrv: SalleService,
    private videoprojecteurSrv: VideoprojecteurService,
    private ordinateurSrv: OrdinateurService,
    private router: Router,
    private ActivatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.formationsObservable = this.formationSrv.getAll();
    this.formateursObservable = this.formateurSrv.getAll();
    this.matieresObservable = this.matiereSrv.getAll();
    this.sallesObservable = this.salleSrv.getAll();
    this.videoprojecteursObservable = this.videoprojecteurSrv.getAll();
    this.ordinateursObservable = this.ordinateurSrv.getAll();

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
      //console.log(this.cours);
      this.coursSrv.create(this.cours).subscribe((cours) => {
        this.router.navigateByUrl('/cours');
      });
    }
  }

  compareFnFormation(ent1: Formation, ent2: Formation): boolean {
    if (ent1 && ent2) {
      return ent1.id === ent2.id;
    } else {
      return false;
    }
  }
  compareFnFormateur(ent1: Formateur, ent2: Formateur): boolean {
    if (ent1 && ent2) {
      return ent1.id === ent2.id;
    } else {
      return false;
    }
  }
  compareFnSalle(ent1: Salle, ent2: Salle): boolean {
    if (ent1 && ent2) {
      return ent1.id === ent2.id;
    } else {
      return false;
    }
  }
  compareFnMatiere(ent1: Matiere, ent2: Matiere): boolean {
    if (ent1 && ent2) {
      return ent1.id === ent2.id;
    } else {
      return false;
    }
  }
  compareFnVideoprojecteur(
    ent1: Videoprojecteur,
    ent2: Videoprojecteur
  ): boolean {
    if (ent1 && ent2) {
      return ent1.id === ent2.id;
    } else {
      return false;
    }
  }
  compareFnOrdinateur(ent1: Ordinateur, ent2: Ordinateur): boolean {
    if (ent1 && ent2) {
      return ent1.id === ent2.id;
    } else {
      return false;
    }
  }
}
