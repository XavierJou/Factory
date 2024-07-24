import { Formation } from './formation';
import { Utilisateur } from './utilisateur';

export class Stagiaire {
  constructor(
    public id?: number,
    public formation?: Formation,
    public utilisateur?: Utilisateur
  ) {}
}
