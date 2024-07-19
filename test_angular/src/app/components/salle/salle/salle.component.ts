import { Component } from '@angular/core';
import { Salle } from '../../../models/salle';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { SalleService } from '../../../services/salle.service';

@Component({
  selector: 'app-salle',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './salle.component.html',
  styleUrl: './salle.component.css',
})
export class SalleComponent {
  salles: Salle[] = [];

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
}
