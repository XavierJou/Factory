import { Component, OnInit } from '@angular/core';
import { Stagiaire } from '../../../models/stagiaire';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { StagiaireService } from '../../../services/stagiaire.service';
import { FormationService } from '../../../services/formation.service';
import { Formation } from '../../../models/formation';

@Component({
  selector: 'app-stagiaire-formation',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './stagiaire-formation.component.html',
  styleUrl: './stagiaire-formation.component.css',
})
export class StagiairesFormationComponent implements OnInit {
  stagiaires: Stagiaire[] = [];
  formation: Formation = new Formation();

  formationId: number = 0;

  constructor(
    private stagiaireSrv: StagiaireService,
    private formationSrv: FormationService,
    private activatedRoute: ActivatedRoute
  ) {}
  ngOnInit(): void {
    this.initStagiaire();
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
    this.stagiaireSrv.deletAvecUtilisateur(id).subscribe(() => {
      this.initStagiaire();
    });
  }

  initStagiaire() {
    this.stagiaireSrv.getWithDetails().subscribe((stagiaires) => {
      this.stagiaires = stagiaires;
    });
  }
}
