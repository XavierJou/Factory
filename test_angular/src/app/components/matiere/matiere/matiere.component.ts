import { Component } from '@angular/core';
import { Matiere } from '../../../models/matiere';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { MatiereService } from '../../../services/matiere.service';

@Component({
  selector: 'app-matiere',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './matiere.component.html',
  styleUrl: './matiere.component.css',
})
export class MatiereComponent {
  matieres: Matiere[] = [];

  constructor(private matiereSrv: MatiereService) {
    this.initMatiere();
  }

  delete(id: number) {
    this.matiereSrv.delete(id).subscribe(() => {
      this.initMatiere();
    });
  }

  initMatiere() {
    this.matiereSrv.getAll().subscribe((matiere) => {
      this.matieres = matiere;
    });
  }
}
