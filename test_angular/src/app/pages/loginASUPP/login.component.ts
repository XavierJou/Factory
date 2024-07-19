import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  loginObj: Login;

  constructor(private http: HttpClient, private router: Router) {
    this.loginObj = new Login();
  }

  onLogin() {
    //debugger;
    this.http
      .post('https://api.freeapi.app/api/v1/users/login', this.loginObj)
      .subscribe((res: any) => {
        if (res.result) {
          //alert('Login avec succès!');
          this.router.navigateByUrl('/dashboard');
        } else {
          alert(res.message + 'else not working ??');
        }
      });
  }
}

export class Login {
  email: string;
  password: string;

  constructor() {
    this.email = '';
    this.password = '';
  }
}
