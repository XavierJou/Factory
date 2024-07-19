import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Competence } from '../../../models/competence';

import {
  Router,
  RouterLink,
  RouterLinkActive,
  ActivatedRoute,
} from '@angular/router';
import { CompetenceService } from '../../../services/competence.service';

@Component({
  selector: 'app-edit-competence',
  standalone: true,
  imports: [FormsModule, RouterLink, RouterLinkActive],
  templateUrl: './edit-competence.component.html',
  styleUrl: './edit-competence.component.css',
})
export class EditCompetenceComponent implements OnInit {
  competence: Competence = new Competence();

  constructor(
    private competenceSrv: CompetenceService,
    private router: Router,
    private ActivatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.ActivatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.competenceSrv.getById(params['id']).subscribe((competence) => {
          this.competence = competence;
        });
      }
    });
  }

  save() {
    if (this.competence.id) {
      this.competenceSrv.update(this.competence).subscribe((competence) => {
        this.router.navigateByUrl('/competence');
      });
    } else {
      this.competenceSrv.create(this.competence).subscribe((competence) => {
        this.router.navigateByUrl('/competence');
      });
    }
  }
}
