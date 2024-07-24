import { Formation } from './../../../../../../../factory-angular-sauv04/src/app/models/formation';
import { Component, OnInit } from '@angular/core';
import { Cours } from '../../../models/cours';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { CoursService } from '../../../services/cours.service';
import { FormationService } from '../../../services/formation.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-cours',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './cours-formation.component.html',
  styleUrl: './cours-formation.component.css',
})
export class CoursFormationComponent implements OnInit {
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
