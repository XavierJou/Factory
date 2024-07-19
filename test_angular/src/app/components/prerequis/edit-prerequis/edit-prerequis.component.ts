import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Prerequis } from '../../../models/prerequis';

import {
  Router,
  RouterLink,
  RouterLinkActive,
  ActivatedRoute,
} from '@angular/router';
import { PrerequisService } from '../../../services/prerequis.service';

@Component({
  selector: 'app-edit-prerequis',
  standalone: true,
  imports: [FormsModule, RouterLink, RouterLinkActive],
  templateUrl: './edit-prerequis.component.html',
  styleUrl: './edit-prerequis.component.css',
})
export class EditPrerequisComponent implements OnInit {
  prerequis: Prerequis = new Prerequis();

  constructor(
    private prerequisSrv: PrerequisService,
    private router: Router,
    private ActivatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.ActivatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.prerequisSrv.getById(params['id']).subscribe((prerequis) => {
          this.prerequis = prerequis;
        });
      }
    });
  }

  save() {
    if (this.prerequis.id) {
      this.prerequisSrv.update(this.prerequis).subscribe((prerequis) => {
        this.router.navigateByUrl('/prerequis');
      });
    } else {
      this.prerequisSrv.create(this.prerequis).subscribe((prerequis) => {
        this.router.navigateByUrl('/prerequis');
      });
    }
  }
}
