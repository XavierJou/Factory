import { Component } from '@angular/core';
import { Matiere } from '../../../models/matiere';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { MatiereService } from '../../../services/matiere.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-matiere',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe, FormsModule],
  templateUrl: './matiere.component.html',
  styleUrl: './matiere.component.css',
})
export class MatiereComponent {
  matieres: Matiere[] = [];
  searchTerm: string = '';

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

  searchMatieres() {
    if (this.searchTerm.length > 0) {
      this.matiereSrv.searchByTitre(this.searchTerm).subscribe((matieres) => {
        this.matieres = matieres;
      });
    } else {
      this.initMatiere();
    }
  }

  trackById(index: number, matiere: Matiere) {
    return matiere.id;
  }
}
