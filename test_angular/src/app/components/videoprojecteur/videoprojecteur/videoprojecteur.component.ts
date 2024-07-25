import { Component } from '@angular/core';
import { Videoprojecteur } from '../../../models/videoprojecteur';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import VideoprojecteurService from '../../../services/videoprojecteur.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-videoprojecteur',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe, FormsModule],
  templateUrl: './videoprojecteur.component.html',
  styleUrl: './videoprojecteur.component.css',
})
export class VideoprojecteurComponent {
  videoprojecteurs: Videoprojecteur[] = [];
  searchTerm: string = '';

  constructor(private videoprojecteurSrv: VideoprojecteurService) {
    this.initVideoprojecteur();
  }

  delete(id: number) {
    this.videoprojecteurSrv.delete(id).subscribe(() => {
      this.initVideoprojecteur();
    });
  }

  initVideoprojecteur() {
    this.videoprojecteurSrv.getAll().subscribe((videoprojecteurs) => {
      this.videoprojecteurs = videoprojecteurs;
    });
  }

  searchOrdinateurs() {
    if (this.searchTerm.length > 0) {
      this.videoprojecteurSrv
        .searchByNom(this.searchTerm)
        .subscribe((videoprojecteur) => {
          this.videoprojecteurs = videoprojecteur;
        });
    } else {
      this.initVideoprojecteur();
    }
  }

  clearSearch() {
    this.searchTerm = '';
    this.searchOrdinateurs();
  }

  trackById(index: number, videoprojecteur: Videoprojecteur) {
    return videoprojecteur.id;
  }
}
