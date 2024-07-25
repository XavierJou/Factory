import { Component } from '@angular/core';
import { UtilisateurService } from '../../services/utilisateur.service';
import { Utilisateur } from '../../models/utilisateur';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AsyncPipe } from '@angular/common';
import { StagiaireService } from '../../services/stagiaire.service';
import { CoursService } from '../../services/cours.service';
import { FormateurService } from '../../services/formateur.service';

@Component({
  selector: 'app-utilisateur',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, FormsModule, AsyncPipe],
  templateUrl: './utilisateur.component.html',
  styleUrl: './utilisateur.component.css',
})
export class UtilisateurComponent {
  utilisateurs: Utilisateur[] = [];

  filteredUtilisateurs: Utilisateur[] = [];

  searchTerm: string = ''; // recherche

  selectedRole: string = 'TOUS LES ROLES';

  valeurSelect: string[] = [
    'TOUS LES ROLES',
    'ROLE_ADMINISTRATEUR',
    'ROLE_GESTIONNAIRE',
    'ROLE_FORMATEUR',
    'ROLE_TECHNICIEN',
    'ROLE_STAGIAIRE',
    'ROLE_USER',
  ];

  messageID: number = 0;
  messageInfo: string = '';

  constructor(
    private utilisateurServ: UtilisateurService,
    private coursService: CoursService,
    private formateurSrv: FormateurService,
    private stagiaireSrv: StagiaireService
  ) {
    this.initUtilisateurs();
  }

  initUtilisateurs() {
    this.utilisateurServ.getWtihDetail().subscribe((utilisateurs) => {
      this.utilisateurs = utilisateurs;
      this.filterUtilisateurs();
    });
  }

  delete(idUtilisateur: number, formateur: any, stagiaire: any) {
    this.messageID = idUtilisateur;
    this.messageInfo = '';
    // controle formateur /et stagiaire
    if (formateur) {
      this.coursService
        .getCountFormateurById(formateur.id)
        .subscribe((nbcours) => {
          // on peut l'effacer
          if (nbcours > 0) {
            this.messageID = idUtilisateur;
            this.messageInfo = 'ce formateur est associé à un cours';
          } else {
            this.formateurSrv
              .deleteAvecUtilisateur(formateur.id)
              .subscribe(() => {
                this.initUtilisateurs();
              });
          }
        });
    } else {
      if (stagiaire) {
        // controle pas relié à une formation
        this.stagiaireSrv.getById(stagiaire.id).subscribe((stagiaireRecup) => {
          if (stagiaireRecup.formation) {
            this.messageID = idUtilisateur;
            this.messageInfo = 'ce stagiaire est associé à une formation';
          } else {
            console.log('debut traitement');

            this.stagiaireSrv
              .deletAvecUtilisateur(stagiaire.id)
              .subscribe(() => {
                this.initUtilisateurs();
              });
          }
        });
      } else {
        this.utilisateurServ.delete(idUtilisateur).subscribe(() => {
          this.initUtilisateurs();
        });
      }
    }
  }

  compareFn(f1: string, f2: string): boolean {
    //return f1 && f2 ? f1.id === f2.id : false;
    if (f1 && f2) {
      return f1 === f2;
    } else {
      return false;
    }
  }

  filterUtilisateurs() {
    if (this.selectedRole === 'TOUS LES ROLES') {
      this.filteredUtilisateurs = this.utilisateurs;
    } else {
      this.filteredUtilisateurs = this.utilisateurs.filter(
        (u) => u.role === this.selectedRole
      );
    }
  }

  sortBy(column: string, ascending: boolean) {
    if (column === 'login') {
      this.sortByLogin(ascending);
    } else if (column === 'nom') {
      this.sortByNom(ascending);
    } else if (column === 'prenom') {
      this.sortByPrenom(ascending);
    }
  }

  private sortByLogin(ascending: boolean) {
    this.filteredUtilisateurs.sort((a, b) => {
      if (a.login! < b.login!) {
        return ascending ? -1 : 1;
      } else if (a.login! > b.login!) {
        return ascending ? 1 : -1;
      } else {
        return 0;
      }
    });
  }

  private sortByNom(ascending: boolean) {
    this.filteredUtilisateurs.sort((a, b) => {
      if (a.nom! < b.nom!) {
        return ascending ? -1 : 1;
      } else if (a.nom! > b.nom!) {
        return ascending ? 1 : -1;
      } else {
        return 0;
      }
    });
  }

  private sortByPrenom(ascending: boolean) {
    this.filteredUtilisateurs.sort((a, b) => {
      if (a.prenom! < b.prenom!) {
        return ascending ? -1 : 1;
      } else if (a.prenom! > b.prenom!) {
        return ascending ? 1 : -1;
      } else {
        return 0;
      }
    });
  }

  searchUtilisateurs(): void {
    if (this.searchTerm) {
      this.utilisateurServ
        .searchByKeyword(this.searchTerm)
        .subscribe((data: Utilisateur[]) => {
          this.filteredUtilisateurs = data;
        });
    } else {
      this.filterUtilisateurs();
    }
  }
}
