import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Matiere } from '../../../models/matiere';

import {
  Router,
  RouterLink,
  RouterLinkActive,
  ActivatedRoute,
} from '@angular/router';
import { MatiereService } from '../../../services/matiere.service';

@Component({
  selector: 'app-edit-matiere',
  standalone: true,
  imports: [FormsModule, RouterLink, RouterLinkActive],
  templateUrl: './edit-matiere.component.html',
  styleUrl: './edit-matiere.component.css',
})
export class EditMatiereComponent implements OnInit {
  matiere: Matiere = new Matiere();

  constructor(
    private matiereSrv: MatiereService,
    private router: Router,
    private ActivatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.ActivatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.matiereSrv.getById(params['id']).subscribe((matiere) => {
          this.matiere = matiere;
        });
      }
    });
  }

  save() {
    if (this.matiere.id) {
      this.matiereSrv.update(this.matiere).subscribe((matiere) => {
        this.router.navigateByUrl('/matieres');
      });
    } else {
      this.matiereSrv.create(this.matiere).subscribe((matiere) => {
        this.router.navigateByUrl('/matieres');
      });
    }
  }
}
