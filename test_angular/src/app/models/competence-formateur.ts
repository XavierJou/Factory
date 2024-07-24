import { Competence } from './competence';
import { Formateur } from './formateur';

export class CompetenceFormateur {
  constructor(
    public id?: number,
    public competence?: Competence,
    public formateur?: Formateur
  ) {}
}
