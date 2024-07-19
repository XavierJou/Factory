import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Videoprojecteur } from '../../../models/videoprojecteur';

import {
  Router,
  RouterLink,
  RouterLinkActive,
  ActivatedRoute,
} from '@angular/router';
import VideoprojecteurService from '../../../services/videoprojecteur.service';

@Component({
  selector: 'app-edit-videoprojecteur',
  standalone: true,
  imports: [FormsModule, RouterLink, RouterLinkActive],
  templateUrl: './edit-videoprojecteur.component.html',
  styleUrl: './edit-videoprojecteur.component.css',
})
export class EditVideoprojecteurComponent implements OnInit {
  videoprojecteur: Videoprojecteur = new Videoprojecteur();

  constructor(
    private videoprojecteurSrv: VideoprojecteurService,
    private router: Router,
    private ActivatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.ActivatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.videoprojecteurSrv
          .getById(params['id'])
          .subscribe((videoprojecteur) => {
            this.videoprojecteur = videoprojecteur;
          });
      }
    });
  }

  save() {
    if (this.videoprojecteur.id) {
      this.videoprojecteurSrv
        .update(this.videoprojecteur)
        .subscribe((videoprojecteur) => {
          this.router.navigateByUrl('/videoprojecteur');
        });
    } else {
      this.videoprojecteurSrv
        .create(this.videoprojecteur)
        .subscribe((videoprojecteur) => {
          this.router.navigateByUrl('/videoprojecteur');
        });
    }
  }
}
