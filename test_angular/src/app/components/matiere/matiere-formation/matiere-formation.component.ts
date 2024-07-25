import { Component, OnInit } from '@angular/core';
import { Matiere } from '../../../models/matiere';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { MatiereService } from '../../../services/matiere.service';
import { FormationService } from '../../../services/formation.service';
import { Formation } from '../../../models/formation';
import { CoursService } from '../../../services/cours.service';
import { Cours } from '../../../models/cours';

@Component({
  selector: 'app-matiere-formation',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './matiere-formation.component.html',
  styleUrl: './matiere-formation.component.css',
})
export class MatieresFormationComponent implements OnInit {
  matieres: Matiere[] = [];
  courss: Cours[] = [];
  formation: Formation = new Formation();

  formationId: number = 0;

  constructor(
    private matiereSrv: MatiereService,
    private formationSrv: FormationService,
    private coursSrv: CoursService,
    private activatedRoute: ActivatedRoute
  ) {}
  ngOnInit(): void {
    this.formationId = this.activatedRoute.snapshot.params['id'];
    this.initMatiere();
  }

  delete(id: number) {
    this.matiereSrv.delete(id).subscribe(() => {
      this.initMatiere();
    });
  }

  initMatiere() {
    this.activatedRoute.params.subscribe((params) => {
      this.formationSrv.getById(params['id']).subscribe((formation) => {
        this.formation = formation;
      });
    });
    this.matiereSrv.getAll().subscribe((matieres) => {
      this.matieres = matieres;
    });
    this.coursSrv
      .getAllMatieresFromFormation(this.formationId)
      .subscribe((cours) => {
        this.courss = cours;
      });
  }
}
