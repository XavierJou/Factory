package formation.conceptdev.facto.dto.request;

import java.time.LocalDateTime;
import java.util.List;

import formation.conceptdev.facto.entities.CoursOrdinateurs;
import formation.conceptdev.facto.entities.Formateur;
import formation.conceptdev.facto.entities.Formation;
import formation.conceptdev.facto.entities.Matiere;
import formation.conceptdev.facto.entities.Salle;
import formation.conceptdev.facto.entities.Videoprojecteur;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class CoursRequest {
	private String titre;
	private LocalDateTime dateDebut;
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

	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDateTime dateDebut) {
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
