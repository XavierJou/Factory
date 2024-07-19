import { Component } from '@angular/core';
import { Videoprojecteur } from '../../../models/videoprojecteur';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import VideoprojecteurService from '../../../services/videoprojecteur.service';

@Component({
  selector: 'app-videoprojecteur',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './videoprojecteur.component.html',
  styleUrl: './videoprojecteur.component.css',
})
export class VideoprojecteurComponent {
  videoprojecteurs: Videoprojecteur[] = [];

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
}
