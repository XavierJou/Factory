export class PlanifSalle {
  constructor(
    public id?: number,
    public nom?: string,
    public debut?: Date,
    public fin?: Date,
    public couleur?: string,
    public texte?: string,
    public estDragable?: boolean,
    public duree?: number
  ) {}
}
