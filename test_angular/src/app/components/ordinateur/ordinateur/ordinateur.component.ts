import { Component } from '@angular/core';
import { Ordinateur } from '../../../models/ordinateur';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { OrdinateurService } from '../../../services/ordinateur.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-ordinateur',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe, FormsModule],
  templateUrl: './ordinateur.component.html',
  styleUrl: './ordinateur.component.css',
})
export class OrdinateurComponent {
  ordinateurs: Ordinateur[] = [];
  searchTerm: string = '';

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

  searchOrdinateurs() {
    if (this.searchTerm.length > 0) {
      this.ordinateurSrv
        .searchByNom(this.searchTerm)
        .subscribe((ordinateurs) => {
          this.ordinateurs = ordinateurs;
        });
    } else {
      this.initOrdinateur();
    }
  }

  clearSearch() {
    this.searchTerm = '';
    this.searchOrdinateurs();
  }

  trackById(index: number, ordinateur: Ordinateur) {
    return ordinateur.id;
  }
}
