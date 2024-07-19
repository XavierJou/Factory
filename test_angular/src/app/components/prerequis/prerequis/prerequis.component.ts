import { Component } from '@angular/core';
import { Prerequis } from '../../../models/prerequis';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { PrerequisService } from '../../../services/prerequis.service';

@Component({
  selector: 'app-prerequis',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './prerequis.component.html',
  styleUrl: './prerequis.component.css',
})
export class PrerequisComponent {
  prerequiss: Prerequis[] = [];

  constructor(private prerequisSrv: PrerequisService) {
    this.initPrerequis();
  }

  delete(id: number) {
    this.prerequisSrv.delete(id).subscribe(() => {
      this.initPrerequis();
    });
  }

  initPrerequis() {
    this.prerequisSrv.getAll().subscribe((prerequis) => {
      this.prerequiss = prerequis;
    });
  }
}
