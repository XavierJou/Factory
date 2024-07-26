import { Component, OnInit } from '@angular/core';
import { Formateur } from '../../../models/formateur';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { AsyncPipe, DatePipe } from '@angular/common';
import { FormateurService } from '../../../services/formateur.service';
import { FormsModule } from '@angular/forms';
import { DisponibiliteFormateur } from '../../../models/disponibilite-formateur';
import { DisponibiliteFormateurService } from '../../../services/disponibilite-formateur.service';
import { Cours } from '../../../models/cours';
import { CoursService } from '../../../services/cours.service';
import { Competence } from '../../../models/competence';
import { CompetenceFormateur } from '../../../models/competence-formateur';
import { CompetenceFormateurService } from '../../../services/competence-formateur.service';
import { CompetenceService } from '../../../services/competence.service';

@Component({
  selector: 'app-formateur',
  standalone: true,
  imports: [FormsModule, RouterLink, RouterLinkActive, DatePipe, AsyncPipe],
  templateUrl: './formateur.component.html',
  styleUrl: './formateur.component.css',
})
export class FormateurComponent implements OnInit {
  formateurs: Formateur[] = [];

  listcours: Cours[] = [];
  listcomptence: Competence[] = [];

  messageDisponibliteId: number = -1;
  messageDisponibilite: string = '';

  messageCoursId: number = -1;
  messageCours: string = '';
  coursAjout: Cours = new Cours();

  competenceAjout: Competence = new Competence();

  indexAjoutDisponibilite: number = -1;
  indexAjoutCours: number = -1;
  indexAjoutComptence: number = -1;

  disponibiliteFormateur: DisponibiliteFormateur = new DisponibiliteFormateur();

  showInfo: boolean[][] = [];

  constructor(
    private formateurSrv: FormateurService,
    private disponibiliteFormateurSrv: DisponibiliteFormateurService,
    private competenceFormateurSrv: CompetenceFormateurService,
    private competenceServ: CompetenceService,
    private coursSrv: CoursService
  ) {}
  ngOnInit(): void {
    this.initFormateurComplet();
  }

  delete(id: number) {
    this.formateurSrv.deleteAvecUtilisateur(id).subscribe(() => {
      this.initFormateurComplet();
    });
  }

  initFormateurComplet() {
    this.formateurSrv.getWithDetails().subscribe((formateurs) => {
      this.formateurs = formateurs;
      this.showInfo = [];

      for (const formateur in this.formateurs) {
        this.showInfo.push([false, false, false]);
      }
    });
  }

  initFormateurMini() {
    this.formateurSrv.getWithDetails().subscribe((formateurs) => {
      this.formateurs = formateurs;
    });
  }

  inverse(i: number, j: number) {
    this.showInfo[i][j] = !this.showInfo[i][j];
  }

  affichageAjoutEdition(index: number) {
    if (this.indexAjoutDisponibilite < 0) {
      this.indexAjoutDisponibilite = index;
      this.indexAjoutCours = -1;
      this.indexAjoutComptence = -1;
    } else {
      this.indexAjoutDisponibilite = -1;
    }
    // this.indexAjoutDisponibilite = index;
  }

  affichageAjoutCours(index: number, idFormateur: number) {
    if (this.indexAjoutCours < 0) {
      this.indexAjoutDisponibilite = -1;
      this.indexAjoutComptence = -1;
      this.coursSrv.getWithOutFormateur(idFormateur).subscribe((cours) => {
        this.listcours = cours;

        this.indexAjoutCours = index;
      });
    } else {
      this.indexAjoutCours = -1;
    }
    // this.indexAjoutDisponibilite = index;
  }

  affichageAjoutCompetence(index: number, idFormateur: number) {
    if (this.indexAjoutCours < 0) {
      this.indexAjoutDisponibilite = -1;
      this.indexAjoutComptence = -1;
      this.competenceServ
        .getNotLinkedFormateur(idFormateur)
        .subscribe((competences) => {
          this.listcomptence = competences;

          this.indexAjoutComptence = index;
        });
    } else {
      this.indexAjoutCours = -1;
    }
    // this.indexAjoutDisponibilite = index;
  }

  AjoutDisponibilite(idFormateur: number) {
    this.messageDisponibilite = '';
    this.messageDisponibliteId = -1;
    let ajoutOk: boolean = true;

    if (!this.disponibiliteFormateur || !this.disponibiliteFormateur) {
      this.messageDisponibilite = ' info incomplete';
      this.messageDisponibliteId = idFormateur;
      ajoutOk = false;
    }

    console.log(this.disponibiliteFormateur.dateDebut);
    console.log(this.disponibiliteFormateur.dateFin);

    if (
      ajoutOk ||
      (this.disponibiliteFormateur.dateDebut &&
        this.disponibiliteFormateur.dateFin)
    ) {
      const db: Date = this.disponibiliteFormateur.dateDebut ?? new Date();
      const df: Date = this.disponibiliteFormateur.dateFin ?? new Date();

      if (db >= df) {
        this.messageDisponibilite = ' date incorrectes';
        this.messageDisponibliteId = idFormateur;
        ajoutOk = false;
      }
    }

    console.log('ici');
    if (ajoutOk) {
      let obj = {
        formateurId: idFormateur,
        dateDebut: this.disponibiliteFormateur.dateDebut,
        dateFin: this.disponibiliteFormateur.dateFin,
      };

      this.disponibiliteFormateurSrv
        .create(obj)
        .subscribe((disponibiliteFormateur_retour) => {
          this.indexAjoutDisponibilite = -1;
          this.indexAjoutCours = -1;
          this.indexAjoutComptence = -1;
          this.initFormateurMini();
        });
    }
  }

  AjoutCompetence(idFormateur: number) {
    let obj = {
      competenceId: this.competenceAjout.id,
      formateurId: idFormateur,
    };

    this.competenceFormateurSrv.create(obj).subscribe((competence_retour) => {
      this.indexAjoutDisponibilite = -1;
      this.indexAjoutCours = -1;
      this.indexAjoutComptence = -1;
      this.initFormateurMini();
    });
  }

  AjoutCours(idFormateur: number) {
    let formateurAAjouter: Formateur = new Formateur();
    formateurAAjouter.id = idFormateur;
    this.coursAjout.formateur = formateurAAjouter;

    this.coursSrv
      .update(this.coursAjout)
      .subscribe((disponibiliteFormateur_retour) => {
        this.indexAjoutDisponibilite = -1;
        this.indexAjoutCours = -1;
        this.indexAjoutComptence = -1;
        this.initFormateurMini();
      });
  }

  effaceDisponibilite(idDisponibilteFormateur: number) {
    this.disponibiliteFormateurSrv
      .delete(idDisponibilteFormateur)
      .subscribe(() => {
        this.initFormateurMini();
      });
  }

  effaceCompetenceFormateur(idCompetenceFormateur: number) {
    this.competenceFormateurSrv.delete(idCompetenceFormateur).subscribe(() => {
      this.initFormateurMini();
    });
  }

  effaceCours(idcours: number) {
    this.coursSrv.getById(idcours).subscribe((cours) => {
      cours.formateur = undefined;
      this.coursSrv.update(cours).subscribe((disponibiliteFormateur_retour) => {
        this.initFormateurMini();
      });
    });
  }
}
