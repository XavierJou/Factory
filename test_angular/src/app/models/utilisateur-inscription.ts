export class UtilisateurInscription {
  constructor(
    public id?: number,
    public login?: string,
    public nom?: string,
    public prenom?: string,
    public email?: string,
    public password?: string,
    public id_satgiaire?: any,
    public id_formateur?: any,
    public role?: string
  ) {}
}
