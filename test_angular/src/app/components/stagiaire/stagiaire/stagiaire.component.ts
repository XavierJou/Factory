import { Component } from '@angular/core';
import { StagiaireService } from '../../../services/stagiaire.service';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { Stagiaire } from '../../../models/stagiaire';

@Component({
  selector: 'app-stagiaire',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './stagiaire.component.html',
  styleUrl: './stagiaire.component.css',
})
export class StagiaireComponent {
  stagiaires: Stagiaire[] = [];

  constructor(private stagiaireSrv: StagiaireService) {
    this.initStagiaire();
  }

  delete(id: number) {
    this.stagiaireSrv.delete(id).subscribe(() => {
      this.initStagiaire();
    });
  }

  initStagiaire() {
    this.stagiaireSrv.getAll().subscribe((stagiaires) => {
      this.stagiaires = stagiaires;
    });
  }
}
