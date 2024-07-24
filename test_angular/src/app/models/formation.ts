import { Cours } from './cours';
import { Formateur } from './formateur';
import { Prerequis } from './prerequis';
import { Stagiaire } from './stagiaire';

export class Formation {
  constructor(
    public id?: number,
    public titre?: string,
    public duree?: number,
    public objectif?: string,
    public contenu?: string,
    public dateDebut?: Date,
    public capacite?: number,
    public prerequis?: Prerequis[],
    public cours?: Cours[],
    public stagiaires?: Stagiaire[]
  ) {}
}
