import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Cours } from '../../../models/cours';

import {
  Router,
  RouterLink,
  RouterLinkActive,
  ActivatedRoute,
} from '@angular/router';
import { CoursService } from '../../../services/cours.service';

@Component({
  selector: 'app-edit-cours',
  standalone: true,
  imports: [FormsModule, RouterLink, RouterLinkActive],
  templateUrl: './edit-cours.component.html',
  styleUrl: './edit-cours.component.css',
})
export class EditCoursComponent implements OnInit {
  cours: Cours = new Cours();

  constructor(
    private coursSrv: CoursService,
    private router: Router,
    private ActivatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.ActivatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.coursSrv.getById(params['id']).subscribe((cours) => {
          this.cours = cours;
        });
      }
    });
  }

  save() {
    if (this.cours.id) {
      this.coursSrv.update(this.cours).subscribe((cours) => {
        this.router.navigateByUrl('/cours');
      });
    } else {
      this.coursSrv.create(this.cours).subscribe((cours) => {
        this.router.navigateByUrl('/cours');
      });
    }
  }
}
