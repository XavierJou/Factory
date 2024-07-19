import { Component } from '@angular/core';
import { Competence } from '../../../models/competence';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { CompetenceService } from '../../../services/competence.service';

@Component({
  selector: 'app-competence',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './competence.component.html',
  styleUrl: './competence.component.css',
})
export class CompetenceComponent {
  competences: Competence[] = [];

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
}
