import { Component, OnInit } from '@angular/core';
import { FormationService } from '../../../services/formation.service';
import { Formation } from '../../../models/formation';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { Formateur } from '../../../models/formateur';
import { MenuComponent } from '../../menu/menu.component';

@Component({
  selector: 'app-nos-formation',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe, MenuComponent],
  templateUrl: './nos-formation.component.html',
  styleUrl: './nos-formation.component.css',
})
export class NosFormationComponent implements OnInit {
  formations: Formation[] = [];
  formationsFormateur: Formation[] = [];
  formateur: Formateur = new Formateur();

  constructor(private formationSrv: FormationService) {}

  ngOnInit(): void {
    this.initFormation();
  }

  initFormation() {
    this.formationSrv.getAll().subscribe((formations) => {
      this.formations = formations;
    });
  }
}
