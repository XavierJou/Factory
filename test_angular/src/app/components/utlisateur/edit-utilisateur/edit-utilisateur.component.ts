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
  constructor(
    private utilisateurSrv: UtilisateurService,
    private formateurSrv: FormateurService,
    private stagiaireSrv: StagiaireService,
    private router: Router,
    private ActivatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.ActivatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.utilisateurSrv.getById(params['id']).subscribe((utilisateur) => {
          this.utilisateur = utilisateur;
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
    if (this.utilisateur.id) {
      this.utilisateurSrv.update(this.utilisateur).subscribe((utilisateur) => {
        this.router.navigateByUrl('/utilisateur?q=update&id=' + utilisateur.id);
      });
    }
  }
}
