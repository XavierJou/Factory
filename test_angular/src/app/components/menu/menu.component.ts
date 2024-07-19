import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { Utilisateur } from '../../models/utilisateur';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css',
})
export class MenuComponent {
  constructor(private router: Router) {}

  get login(): string {
    let u: Utilisateur = JSON.parse(localStorage.getItem('utilisateur')!);
    return u.login!;
  }

  logout() {
    localStorage.clear();
    this.router.navigateByUrl('/home');
  }

  get logged(): boolean {
    return localStorage.getItem('token') != null;
  }
}
