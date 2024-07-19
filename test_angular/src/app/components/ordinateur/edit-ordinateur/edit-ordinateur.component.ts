import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Ordinateur } from '../../../models/ordinateur';

import {
  Router,
  RouterLink,
  RouterLinkActive,
  ActivatedRoute,
} from '@angular/router';
import { OrdinateurService } from '../../../services/ordinateur.service';

@Component({
  selector: 'app-edit-ordinateur',
  standalone: true,
  imports: [FormsModule, RouterLink, RouterLinkActive],
  templateUrl: './edit-ordinateur.component.html',
  styleUrl: './edit-ordinateur.component.css',
})
export class EditOrdinateurComponent implements OnInit {
  ordinateur: Ordinateur = new Ordinateur();

  constructor(
    private ordinateurSrv: OrdinateurService,
    private router: Router,
    private ActivatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.ActivatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.ordinateurSrv.getById(params['id']).subscribe((ordinateur) => {
          this.ordinateur = ordinateur;
        });
      }
    });
  }

  save() {
    if (this.ordinateur.id) {
      this.ordinateurSrv.update(this.ordinateur).subscribe((ordinateur) => {
        this.router.navigateByUrl('/ordinateur');
      });
    } else {
      this.ordinateurSrv.create(this.ordinateur).subscribe((ordinateur) => {
        this.router.navigateByUrl('/ordinateur');
      });
    }
  }
}
