import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  login: string = '';
  password: string = '';
  error: boolean = false;

  constructor(private authSrv: AuthService, private router: Router) {}

  connexion() {
    this.authSrv.connexion(this.login, this.password).subscribe({
      next: (utilisateur) => {
        localStorage.setItem(
          'token',
          window.btoa(this.login + ':' + this.password)
        );
        localStorage.setItem('utilisateur', JSON.stringify(utilisateur));
        this.error = false;
        this.router.navigateByUrl('/home');
      },
      error: (err) => {
        this.error = true;
        console.debug(err);
      },
    });
  }
}
