import { CompetenceFormateur } from './competence-formateur';
import { Cours } from './cours';
import { DisponibiliteFormateur } from './disponibilite-formateur';
import { Utilisateur } from './utilisateur';

export class Formateur {
  constructor(
    public id?: number,
    public utilisateur?: Utilisateur,
    public cours?: Cours[],
    public disponibiliteFormateur?: DisponibiliteFormateur[],
    public competenceFormateur?: CompetenceFormateur[]
  ) {}
}
