import { Component, OnInit } from '@angular/core';
import { Formateur } from '../../../models/formateur';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { FormateurService } from '../../../services/formateur.service';

@Component({
  selector: 'app-formateur',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './formateur.component.html',
  styleUrl: './formateur.component.css',
})
export class FormateurComponent implements OnInit {
  formateurs: Formateur[] = [];

  showInfo: boolean[][] = [];

  constructor(private formateurSrv: FormateurService) {}
  ngOnInit(): void {
    this.initFormateur();
  }

  delete(id: number) {
    this.formateurSrv.delete(id).subscribe(() => {
      this.initFormateur();
    });
  }

  initFormateur() {
    this.formateurSrv.getWithDetails().subscribe((formateurs) => {
      this.formateurs = formateurs;
      this.showInfo = [];

      for (const formateur in this.formateurs) {
        this.showInfo.push([false, false, false]);
      }
    });
  }

  inverse(i: number, j: number) {
    this.showInfo[i][j] = !this.showInfo[i][j];
  }
}
