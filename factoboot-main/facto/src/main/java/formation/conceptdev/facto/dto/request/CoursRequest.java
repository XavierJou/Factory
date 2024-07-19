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

public class CoursResquest {
	private String titre;
	private LocalDateTime dateDebut;
	private boolean videoproj;
	private boolean ordiFormateur;
	private boolean ordiStagiaire;
	
	public CoursResquest() {
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

	public boolean isVideoproj() {
		return videoproj;
	}

	public void setVideoproj(boolean videoproj) {
		this.videoproj = videoproj;
	}

	public boolean isOrdiFormateur() {
		return ordiFormateur;
	}

	public void setOrdiFormateur(boolean ordiFormateur) {
		this.ordiFormateur = ordiFormateur;
	}

	public boolean isOrdiStagiaire() {
		return ordiStagiaire;
	}

	public void setOrdiStagiaire(boolean ordiStagiaire) {
		this.ordiStagiaire = ordiStagiaire;
	}
	
}
