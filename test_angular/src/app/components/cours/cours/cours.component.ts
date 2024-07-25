import { Component } from '@angular/core';
import { Cours } from '../../../models/cours';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { CoursService } from '../../../services/cours.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-cours',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe, FormsModule],
  templateUrl: './cours.component.html',
  styleUrl: './cours.component.css',
})
export class CoursComponent {
  courss: Cours[] = [];
  searchTerm: string = '';

  constructor(private coursSrv: CoursService) {
    this.initCours();
  }

  delete(id: number) {
    this.coursSrv.delete(id).subscribe(() => {
      this.initCours();
    });
  }

  initCours() {
    this.coursSrv.getAll().subscribe((courss) => {
      this.courss = courss;
    });
  }

  searchCourss() {
    if (this.searchTerm.length > 0) {
      this.coursSrv.searchByNom(this.searchTerm).subscribe((cours) => {
        this.courss = cours;
      });
    } else {
      this.initCours();
    }
  }

  clearSearch() {
    this.searchTerm = '';
    this.searchCourss();
  }

  trackById(index: number, cours: Cours) {
    return cours.id;
  }
}
