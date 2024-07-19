import { Component } from '@angular/core';
import { Cours } from '../../../models/cours';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { CoursService } from '../../../services/cours.service';

@Component({
  selector: 'app-cours',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './cours.component.html',
  styleUrl: './cours.component.css',
})
export class CoursComponent {
  courss: Cours[] = [];

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
}
