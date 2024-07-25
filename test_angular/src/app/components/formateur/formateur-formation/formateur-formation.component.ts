import { Component, OnInit } from '@angular/core';
import { Formateur } from '../../../models/formateur';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { FormateurService } from '../../../services/formateur.service';
import { FormationService } from '../../../services/formation.service';
import { Formation } from '../../../models/formation';
import { Cours } from '../../../models/cours';
import { CoursService } from '../../../services/cours.service';

@Component({
  selector: 'app-formateur-formation',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './formateur-formation.component.html',
  styleUrl: './formateur-formation.component.css',
})
export class FormateursFormationComponent implements OnInit {
  formateurs: Formateur[] = [];
  courss: Cours[] = [];
  formation: Formation = new Formation();

  formationId: number = 0;

  constructor(
    private formateurSrv: FormateurService,
    private formationSrv: FormationService,
    private coursSrv: CoursService,
    private activatedRoute: ActivatedRoute
  ) {}
  ngOnInit(): void {
    this.formationId = this.activatedRoute.snapshot.params['id'];
    this.initFormateur();
  }

  delete(id: number) {
    this.formateurSrv.delete(id).subscribe(() => {
      this.initFormateur();
    });
  }

  initFormateur() {
    this.activatedRoute.params.subscribe((params) => {
      this.formationSrv.getById(params['id']).subscribe((formation) => {
        this.formation = formation;
      });
    });
    this.coursSrv
      .getAllFormateursFromFormation(this.formationId)
      .subscribe((cours) => {
        this.courss = cours;
      });
  }
}
