import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Salle } from '../../../models/salle';

import {
  Router,
  RouterLink,
  RouterLinkActive,
  ActivatedRoute,
} from '@angular/router';
import { SalleService } from '../../../services/salle.service';

@Component({
  selector: 'app-edit-salle',
  standalone: true,
  imports: [FormsModule, RouterLink, RouterLinkActive],
  templateUrl: './edit-salle.component.html',
  styleUrl: './edit-salle.component.css',
})
export class EditSalleComponent implements OnInit {
  salle: Salle = new Salle();

  constructor(
    private salleSrv: SalleService,
    private router: Router,
    private ActivatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.ActivatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.salleSrv.getById(params['id']).subscribe((salle) => {
          this.salle = salle;
        });
      }
    });
  }

  save() {
    if (this.salle.id) {
      this.salleSrv.update(this.salle).subscribe((salle) => {
        this.router.navigateByUrl('/salle');
      });
    } else {
      this.salleSrv.create(this.salle).subscribe((salle) => {
        this.router.navigateByUrl('/salle');
      });
    }
  }
}
