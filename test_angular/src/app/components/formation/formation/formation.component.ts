import { Component, OnInit } from '@angular/core';
import { FormationService } from '../../../services/formation.service';
import { Formation } from '../../../models/formation';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { Utilisateur } from '../../../models/utilisateur';
import { Formateur } from '../../../models/formateur';
import { FormateurService } from '../../../services/formateur.service';

@Component({
  selector: 'app-formation',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './formation.component.html',
  styleUrl: './formation.component.css',
})
export class FormationComponent implements OnInit {
  formations: Formation[] = [];
  formationsFormateur: Formation[] = [];
  formateur: Formateur = new Formateur();

  constructor(
    private formationSrv: FormationService,
    private formateurSrv: FormateurService
  ) {}

  ngOnInit(): void {
    this.initFormation();
  }

  get role(): string {
    let u: Utilisateur = JSON.parse(localStorage.getItem('utilisateur')!);
    return u.role!;
  }

  get utilisateurId(): number {
    let u: Utilisateur = JSON.parse(localStorage.getItem('utilisateur')!);
    return u.id!;
  }

  delete(id: number) {
    this.formationSrv.delete(id).subscribe(() => {
      this.initFormation();
    });
  }

  initFormation() {
    this.formationSrv.getAll().subscribe((formations) => {
      this.formations = formations;
    });
    this.formateurSrv
      .getByUtilisateurId(this.utilisateurId)
      .subscribe((formateur) => {
        this.formateur = formateur;
        this.formationSrv
          .getAllByFormateurId(this.formateur.id!)
          .subscribe((formations) => {
            this.formationsFormateur = formations;
          });
      });
  }
}
