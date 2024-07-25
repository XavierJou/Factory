import { Component, OnInit } from '@angular/core';
import { Formateur } from '../../../models/formateur';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { FormateurService } from '../../../services/formateur.service';
import { FormsModule } from '@angular/forms';
import { DisponibiliteFormateur } from '../../../models/disponibilite-formateur';
import { DisponibiliteFormateurService } from '../../../services/disponibilite-formateur.service';

@Component({
  selector: 'app-formateur',
  standalone: true,
  imports: [FormsModule, RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './formateur.component.html',
  styleUrl: './formateur.component.css',
})
export class FormateurComponent implements OnInit {
  formateurs: Formateur[] = [];

  messageDisponibliteId: number = -1;
  messageDisponibilite: string = '';

  indexAjoutDisponibilite: number = -1;

  disponibiliteFormateur: DisponibiliteFormateur = new DisponibiliteFormateur();

  showInfo: boolean[][] = [];

  constructor(
    private formateurSrv: FormateurService,
    private DisponibiliteFormateurSrv: DisponibiliteFormateurService
  ) {}
  ngOnInit(): void {
    this.initFormateur();
  }

  delete(id: number) {
    this.formateurSrv.delete(id).subscribe(() => {
      this.initFormateur();
    });
  }

  initFormateur() {
    this.formateurSrv.getWithDetails().subscribe((formateurs) => {
      this.formateurs = formateurs;
      this.showInfo = [];

      for (const formateur in this.formateurs) {
        this.showInfo.push([false, false, false]);
      }
    });
  }

  inverse(i: number, j: number) {
    this.showInfo[i][j] = !this.showInfo[i][j];
  }

  affichageAjoutEdition(index: number) {
    if (this.indexAjoutDisponibilite < 0) {
      this.indexAjoutDisponibilite = index;
    } else {
      this.indexAjoutDisponibilite = -1;
    }
    // this.indexAjoutDisponibilite = index;
  }

  AjoutDisponibilite(idFormateur: number) {
    this.messageDisponibilite = '';
    this.messageDisponibliteId = -1;
    let ajoutOk: boolean = false;

    if (!this.disponibiliteFormateur || !this.disponibiliteFormateur) {
      this.messageDisponibilite = ' info incomplete';
      this.messageDisponibliteId = idFormateur;
      ajoutOk = false;
    }

    if (
      ajoutOk ||
      (this.disponibiliteFormateur.dateDebut ?? new Date()) <
        (this.disponibiliteFormateur.dateFin ?? new Date())
    ) {
      this.messageDisponibilite = ' date incorrectes';
      this.messageDisponibliteId = idFormateur;
      ajoutOk = false;
    }

    if (ajoutOk) {
      let obj = {
        formateurId: idFormateur,
        dateDebut: this.disponibiliteFormateur.dateDebut,
        dateFin: this.disponibiliteFormateur.dateFin,
      };

      this.DisponibiliteFormateurSrv.create(obj).subscribe(
        (disponibiliteFormateur_retour) => {
          this.indexAjoutDisponibilite = -1;
          this.initFormateur();
        }
      );
    }
  }
}
