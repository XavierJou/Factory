import { Component } from '@angular/core';
import { Competence } from '../../../models/competence';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { CompetenceService } from '../../../services/competence.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-competence',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, FormsModule],
  templateUrl: './competence.component.html',
  styleUrl: './competence.component.css',
})
export class CompetenceComponent {
  competences: Competence[] = [];
  searchTerm: string = ''; // recherche

  constructor(private competenceSrv: CompetenceService) {
    this.initCompetence();
  }

  delete(id: number) {
    this.competenceSrv.delete(id).subscribe(() => {
      this.initCompetence();
    });
  }

  initCompetence() {
    this.competenceSrv.getAll().subscribe((competences) => {
      this.competences = competences;
    });
  }

  searchCompetences() {
    if (this.searchTerm.length > 0) {
      this.competenceSrv
        .searchByNom(this.searchTerm)
        .subscribe((competences) => {
          this.competences = competences;
        });
    } else {
      this.initCompetence();
    }
  }

  clearSearch() {
    this.searchTerm = '';
    this.searchCompetences();
  }

  trackById(index: number, competence: Competence) {
    return competence.id;
  }
}
