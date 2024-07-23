import { Formateur } from './formateur';
import { Stagiaire } from './stagiaire';
export class Utilisateur {
  constructor(
    public id?: number,
    public login?: string,
    public nom?: string,
    public prenom?: string,
    public email?: string,
    public role?: string,
    public formateur?: Formateur,
    public stagiaire?: Stagiaire
  ) {}
}
