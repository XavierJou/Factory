import { Component } from '@angular/core';
import { Ordinateur } from '../../../models/ordinateur';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { OrdinateurService } from '../../../services/ordinateur.service';

@Component({
  selector: 'app-ordinateur',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './ordinateur.component.html',
  styleUrl: './ordinateur.component.css',
})
export class OrdinateurComponent {
  ordinateurs: Ordinateur[] = [];

  constructor(private ordinateurSrv: OrdinateurService) {
    this.initOrdinateur();
  }

  delete(id: number) {
    this.ordinateurSrv.delete(id).subscribe(() => {
      this.initOrdinateur();
    });
  }

  edit(id: number) {}

  initOrdinateur() {
    this.ordinateurSrv.getAll().subscribe((ordinateurs) => {
      this.ordinateurs = ordinateurs;
    });
  }
}
