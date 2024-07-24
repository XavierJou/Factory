import { Formateur } from './formateur';

export class DisponibiliteFormateur {
  constructor(
    public id?: number,
    public dateDebut?: Date,
    public dateFin?: Date,
    public formateur?: Formateur
  ) {}
}
