export class Formation {
  constructor(
    public id?: number,
    public titre?: string,
    public duree?: number,
    public objectif?: string,
    public contenu?: string,
    public dateDebut?: Date,
    public capacite?: number
  ) {}
}
