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

@Component({
  selector: 'app-inscription',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './inscription.component.html',
  styleUrl: './inscription.component.css',
})
export class InscriptionComponent {
  form!: FormGroup;
  constructor() {
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

  submit() {
    let obj = {
      login: this.form.get('login')?.value,
      nom: this.form.get('nom')?.value,
      prenom: this.form.get('prenom')?.value,
      email: this.form.get('email')?.value,
      password: this.form.get('passwordGroup.password')?.value,
      role: 'ROLE_USER', // A VERIFIER <----------------------------------------------------------------------------------------------------------------
    };
    console.log(obj);
  }

  passwordEtConfirmationEgaux(
    control: AbstractControl
  ): null | ValidationErrors {
    return control.get('password')?.value == control.get('confirmation')?.value
      ? null
      : { passwordEtConfirmationNotEquals: true };
  }
}
