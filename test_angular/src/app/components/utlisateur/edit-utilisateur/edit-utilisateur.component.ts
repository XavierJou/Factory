import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {
  Router,
  RouterLink,
  RouterLinkActive,
  ActivatedRoute,
} from '@angular/router';
import { UtilisateurService } from '../../../services/utilisateur.service';
import { FormateurService } from '../../../services/formateur.service';
import { StagiaireService } from '../../../services/stagiaire.service';
import { Utilisateur } from '../../../models/utilisateur';
import { Stagiaire } from '../../../models/stagiaire';
import { CoursService } from '../../../services/cours.service';
import { Formateur } from '../../../models/formateur';

@Component({
  selector: 'app-edit-utilisateur',
  standalone: true,
  imports: [FormsModule, RouterLink, RouterLinkActive],
  templateUrl: './edit-utilisateur.component.html',
  styleUrl: './edit-utilisateur.component.css',
})
export class EditUtilisateurComponent {
  utilisateur: Utilisateur = new Utilisateur();
  valeurSelect: string[] = [
    'ROLE_ADMINISTRATEUR',
    'ROLE_GESTIONNAIRE',
    'ROLE_FORMATEUR',
    'ROLE_TECHNICIEN',
    'ROLE_STAGIAIRE',
    'ROLE_USER',
  ];

  roleAvant!: string;

  messageInfo: string = '';

  constructor(
    private utilisateurSrv: UtilisateurService,
    private formateurSrv: FormateurService,
    private stagiaireSrv: StagiaireService,
    private router: Router,
    private coursService: CoursService,
    private ActivatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.ActivatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.utilisateurSrv.getById(params['id']).subscribe((utilisateur) => {
          this.utilisateur = utilisateur;
          this.roleAvant != this.utilisateur.role;
        });
      }
    });
  }

  compareFn(f1: string, f2: string): boolean {
    //return f1 && f2 ? f1.id === f2.id : false;
    if (f1 && f2) {
      return f1 === f2;
    } else {
      return false;
    }
  }
  save() {
    this.messageInfo = '';

    if (this.utilisateur.id) {
      if (
        this.roleAvant == 'ROLE_STAGIARE' &&
        this.roleAvant != this.utilisateur.role
      ) {
        let stagiaire = this.utilisateur.stagiaire ?? new Stagiaire();
        let id_stagiaire = stagiaire.id ?? 0;

        this.stagiaireSrv.getById(id_stagiaire).subscribe((stagiaireRecup) => {
          if (stagiaireRecup.formation) {
            this.messageInfo = 'ce stagiaire est associé à une formation';
            return;
          } else {
            // annulation id stagiaire dans utilisateur
            this.utilisateurSrv
              .nullIdStagiaire(this.utilisateur.id ?? 0)
              .subscribe(() => {
                if (id_stagiaire > 0) {
                  // annulation id utilisateur dans stagiare
                  this.stagiaireSrv
                    .nullIdUtilisateur(id_stagiaire)
                    .subscribe(() => {
                      // suppression stagiaire
                      this.stagiaireSrv.delete(id_stagiaire).subscribe(() => {
                        this.miseAJour();
                        return;
                      });
                    });
                }
              });
          }
        });
      } else {
        if (
          this.roleAvant == 'ROLE_FORMATEUR' &&
          this.roleAvant != this.utilisateur.role
        ) {
          let formateur = this.utilisateur.formateur ?? new Formateur();
          let id_formateur = formateur.id ?? 0;
          if (id_formateur > 0) {
            this.coursService
              .getCountFormateurById(id_formateur)
              .subscribe((nbcours) => {
                // on peut l'effacer le formateur ?
                if (nbcours > 0) {
                  this.messageInfo = 'ce formateur est associé à un cours';
                } else {
                  // effacement id formateur
                  this.utilisateurSrv
                    .nullIdFormateur(this.utilisateur.id ?? 0)
                    .subscribe(() => {
                      console.log('effacement id formateur');
                      // effacement id utilisateur
                      this.formateurSrv
                        .nullIdUtilisateur(id_formateur)
                        .subscribe(() => {
                          // effacement formateur
                          this.formateurSrv
                            .delete(id_formateur)
                            .subscribe(() => {
                              console.log('effacement formateur');
                              this.miseAJour();
                              return;
                            });
                        });
                    });
                }
              });
          }
        } else {
          this.miseAJour();
        }
      }
    }
  }

  miseAJour() {
    if (
      this.utilisateur.role == 'ROLE_FORMATEUR' &&
      this.roleAvant != this.utilisateur.role
    ) {
      // ajout formateur
      this.utilisateur.stagiaire = undefined;
      this.utilisateur.formateur = new Formateur();

      // affectation formateur utilisateur

      this.sauveUtilisateur();
    } else {
      if (
        this.utilisateur.role == 'ROLE_STAGIAIRE' &&
        this.roleAvant != this.utilisateur.role
      ) {
        // ajout stagiaire

        // affectation stagiaire utilisateur

        this.utilisateur.stagiaire = new Stagiaire();
        this.utilisateur.formateur = undefined;

        this.sauveUtilisateur();
      } else {
        this.utilisateur.stagiaire = undefined;
        this.utilisateur.formateur = undefined;
        this.sauveUtilisateur();
      }
    }
  }

  sauveUtilisateur() {
    this.utilisateurSrv.update(this.utilisateur).subscribe((utilisateur) => {
      this.router.navigateByUrl('/utilisateur?q=update&id=' + utilisateur.id);
    });
  }
}
