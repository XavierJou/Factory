import { Component, OnInit } from '@angular/core';
import { Prerequis } from '../../../models/prerequis';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { PrerequisService } from '../../../services/prerequis.service';
import { FormationService } from '../../../services/formation.service';
import { Formation } from '../../../models/formation';

@Component({
  selector: 'app-prerequis',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './prerequis-formation.component.html',
  styleUrl: './prerequis-formation.component.css',
})
export class PrerequisFormationComponent implements OnInit{
  prerequiss: Prerequis[] = [];

  formation: Formation = new Formation();

  formationId: number = 0;

  constructor(
    private prerequisSrv: PrerequisService,
    private formationSrv: FormationService,
    private activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.initPrerequis();
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
    this.prerequisSrv.delete(id).subscribe(() => {
      this.initPrerequis();
    });
  }

  initPrerequis() {
    this.prerequisSrv.getAll().subscribe((prerequis) => {
      this.prerequiss = prerequis;
    });
  }
}
