import { Videoprojecteur } from './videoprojecteur';
import { Formateur } from './formateur';
import { Formation } from './formation';
import { Matiere } from './matiere';
import { Salle } from './salle';

export class Cours {
  constructor(
    public id?: number,
    public titre?: string,
    public dateDebut?: Date,
    public matiere?: Matiere,
    public formation?: Formation,
    public salle?: Salle,
    public formateur?: Formateur,
    public videoprojecteur?: Videoprojecteur
  ) {}
}
