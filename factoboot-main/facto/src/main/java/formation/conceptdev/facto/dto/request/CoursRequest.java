package formation.conceptdev.facto.dto.request;

import java.time.LocalDate;

public class CoursRequest {
	private String titre;
	private LocalDate dateDebut;
	private boolean besoinVideoprojecteur;
	private boolean besoinOrdiFormateur;
	private boolean besoinOrdiStagiaire;
	private boolean besoinSalle;
	
	public CoursRequest() {
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public boolean isBesoinVideoprojecteur() {
		return besoinVideoprojecteur;
	}

	public void setBesoinVideoprojecteur(boolean besoinVideoprojecteur) {
		this.besoinVideoprojecteur = besoinVideoprojecteur;
	}

	public boolean isBesoinOrdiFormateur() {
		return besoinOrdiFormateur;
	}

	public void setBesoinOrdiFormateur(boolean besoinOrdiFormateur) {
		this.besoinOrdiFormateur = besoinOrdiFormateur;
	}

	public boolean isBesoinOrdiStagiaire() {
		return besoinOrdiStagiaire;
	}

	public void setBesoinOrdiStagiaire(boolean besoinOrdiStagiaire) {
		this.besoinOrdiStagiaire = besoinOrdiStagiaire;
	}

	public boolean isBesoinSalle() {
		return besoinSalle;
	}

	public void setBesoinSalle(boolean besoinSalle) {
		this.besoinSalle = besoinSalle;
	}

	
	
}
