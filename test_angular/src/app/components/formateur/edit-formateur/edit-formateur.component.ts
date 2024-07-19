import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Formateur } from '../../../models/formateur';

import {
  Router,
  RouterLink,
  RouterLinkActive,
  ActivatedRoute,
} from '@angular/router';
import { FormateurService } from '../../../services/formateur.service';

@Component({
  selector: 'app-edit-formateur',
  standalone: true,
  imports: [FormsModule, RouterLink, RouterLinkActive],
  templateUrl: './edit-formateur.component.html',
  styleUrl: './edit-formateur.component.css',
})
export class EditFormateurComponent implements OnInit {
  formateur: Formateur = new Formateur();

  constructor(
    private formateurSrv: FormateurService,
    private router: Router,
    private ActivatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.ActivatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.formateurSrv.getById(params['id']).subscribe((formateur) => {
          this.formateur = formateur;
        });
      }
    });
  }

  save() {
    if (this.formateur.id) {
      this.formateurSrv.update(this.formateur).subscribe((formateur) => {
        this.router.navigateByUrl('/formateur');
      });
    } else {
      this.formateurSrv.create(this.formateur).subscribe((formateur) => {
        this.router.navigateByUrl('/formateur');
      });
    }
  }
}
