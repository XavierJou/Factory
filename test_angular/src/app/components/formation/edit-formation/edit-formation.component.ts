import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Formation } from '../../../models/formation';
import { FormationService } from '../../../services/formation.service';
import {
  Router,
  RouterLink,
  RouterLinkActive,
  ActivatedRoute,
} from '@angular/router';

@Component({
  selector: 'app-edit-formation',
  standalone: true,
  imports: [FormsModule, RouterLink, RouterLinkActive],
  templateUrl: './edit-formation.component.html',
  styleUrl: './edit-formation.component.css',
})
export class EditFormationComponent implements OnInit {
  formation: Formation = new Formation();

  constructor(
    private formationSrv: FormationService,
    private router: Router,
    private ActivatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.ActivatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.formationSrv.getById(params['id']).subscribe((formation) => {
          this.formation = formation;
        });
      }
    });
  }

  save() {
    if (this.formation.id) {
      this.formationSrv.update(this.formation).subscribe((formation) => {
        this.router.navigateByUrl('/formations');
      });
    } else {
      this.formationSrv.create(this.formation).subscribe((formation) => {
        this.router.navigateByUrl('/formations');
      });
    }
  }
}
