import { Component, OnInit } from '@angular/core';
import { Cours } from '../../../models/cours';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { CoursService } from '../../../services/cours.service';
import { FormationService } from '../../../services/formation.service';
import { Formation } from '../../../models/formation';

@Component({
  selector: 'app-formateur-formation',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './formateur-formation.component.html',
  styleUrl: './formateur-formation.component.css',
})
export class FormateursFormationComponent implements OnInit {
  courss: Cours[] = [];
  formation: Formation = new Formation();

  formationId: number = 0;

  constructor(
    private coursSrv: CoursService,
    private formationSrv: FormationService,
    private activatedRoute: ActivatedRoute
  ) {}
  ngOnInit(): void {
    this.initCours();
    this.initFormation();
  }

  initFormation() {
    this.activatedRoute.params.subscribe((params) => {
      this.formationSrv.getById(params['id']).subscribe((formation) => {
        this.formation = formation;
      });
    });
  }

  delete(id: number) {
    this.coursSrv.delete(id).subscribe(() => {
      this.initCours();
    });
  }

  initCours() {
    this.coursSrv.getAllFromFormation().subscribe((courss) => {
      this.courss = courss;
    });
  }
}
