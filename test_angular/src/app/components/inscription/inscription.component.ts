import { Component } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import { UtilisateurService } from '../../services/utilisateur.service';
import { Router } from '@angular/router';
import { UtilisateurInscription } from '../../models/utilisateur-inscription';
import { utcFridays } from 'd3';

@Component({
  selector: 'app-inscription',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './inscription.component.html',
  styleUrl: './inscription.component.css',
})
export class InscriptionComponent {
  form!: FormGroup;

  constructor(
    private utilisateurSrv: UtilisateurService,
    private router: Router
  ) {
    this.form = new FormGroup({
      login: new FormControl('', Validators.required),
      nom: new FormControl(''),
      prenom: new FormControl(''),
      email: new FormControl('', Validators.required),
      passwordGroup: new FormGroup(
        {
          password: new FormControl('', Validators.required),
          confirmation: new FormControl(''),
        },
        this.passwordEtConfirmationEgaux
      ),
    });
  }

  messageErreur!: string;

  inscription() {
    let obj = {
      login: this.form.get('login')?.value,
      nom: this.form.get('nom')?.value,
      prenom: this.form.get('prenom')?.value,
      email: this.form.get('email')?.value,
      password: this.form.get('passwordGroup.password')?.value,
      id_satgiaire: null,
      id_formateur: null,
      role: 'ROLE_USER',
    };
    let utilisateurInscription: UtilisateurInscription = obj;

    this.utilisateurSrv.inscription(utilisateurInscription).subscribe({
      next: (data) => {
        console.log('controle ok');
        this.router.navigateByUrl('/login');
      },
      error: (error) => {
        this.messageErreur = error.message;
      },
    });
  }

  passwordEtConfirmationEgaux(
    control: AbstractControl
  ): null | ValidationErrors {
    return control.get('password')?.value == control.get('confirmation')?.value
      ? null
      : { passwordEtConfirmationNotEquals: true };
  }
}
