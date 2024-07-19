import { Component } from '@angular/core';
import { FormationService } from '../../../services/formation.service';
import { Formation } from '../../../models/formation';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-formation',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './formation.component.html',
  styleUrl: './formation.component.css',
})
export class FormationComponent {
  formations: Formation[] = [];

  constructor(private formationSrv: FormationService) {
    this.initFormation();
  }

  delete(id: number) {
    this.formationSrv.delete(id).subscribe(() => {
      this.initFormation();
    });
  }

  initFormation() {
    this.formationSrv.getAll().subscribe((formations) => {
      this.formations = formations;
    });
  }
}
