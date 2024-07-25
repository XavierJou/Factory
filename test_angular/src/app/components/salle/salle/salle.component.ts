import { Component } from '@angular/core';
import { Salle } from '../../../models/salle';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { SalleService } from '../../../services/salle.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-salle',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe, FormsModule],
  templateUrl: './salle.component.html',
  styleUrl: './salle.component.css',
})
export class SalleComponent {
  salles: Salle[] = [];
  searchTerm: string = '';

  constructor(private salleSrv: SalleService) {
    this.initSalle();
  }

  delete(id: number) {
    this.salleSrv.delete(id).subscribe(() => {
      this.initSalle();
    });
  }

  initSalle() {
    this.salleSrv.getAll().subscribe((salle) => {
      this.salles = salle;
    });
  }

  searchSalles() {
    if (this.searchTerm.length > 0) {
      this.salleSrv.searchByNom(this.searchTerm).subscribe((salles) => {
        this.salles = salles;
      });
    } else {
      this.initSalle();
    }
  }

  clearSearch() {
    this.searchTerm = '';
    this.searchSalles();
  }

  trackById(index: number, salle: Salle) {
    return salle.id;
  }
}
