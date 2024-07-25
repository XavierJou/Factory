import { Component, OnInit } from '@angular/core';
import { FormationService } from '../../../services/formation.service';
import { Formation } from '../../../models/formation';
import { RouterLink, RouterLinkActive, ActivatedRoute } from '@angular/router';
import { DatePipe } from '@angular/common';
import { Utilisateur } from '../../../models/utilisateur';
import { Formateur } from '../../../models/formateur';
import { FormateurService } from '../../../services/formateur.service';

@Component({
  selector: 'app-formation-detail',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './formation-detail.component.html',
  styleUrl: './formation-detail.component.css',
})
export class FormationDetailComponent implements OnInit {
  formation: Formation = new Formation();

  //formationsFormateur: Formation[] = [];
  //formateur: Formateur = new Formateur();

  constructor(
    private formationSrv: FormationService,
    private activatedRoute: ActivatedRoute //private formateurSrv: FormateurService
  ) {}

  ngOnInit(): void {
    this.initFormation();
  }

  get role(): string {
    let u: Utilisateur = JSON.parse(localStorage.getItem('utilisateur')!);
    return u.role!;
  }

  // get utilisateurId(): number {
  //   let u: Utilisateur = JSON.parse(localStorage.getItem('utilisateur')!);
  //   return u.id!;
  // }

  initFormation() {
    this.activatedRoute.params.subscribe((params) => {
      this.formationSrv.getById(params['id']).subscribe((formation) => {
        this.formation = formation;
      });
    });
    // this.formateurSrv
    //   .getByUtilisateurId(this.utilisateurId)
    //   .subscribe((formateur) => {
    //     this.formateur = formateur;
    //     this.formationSrv
    //       .getAllByFormateurId(this.formateur.id!)
    //       .subscribe((formations) => {
    //         this.formationsFormateur = formations;
    //       });
    //   });
  }
}
