package formation.conceptdev.facto.dto.request;

import java.time.LocalDate;
import java.util.List;

import formation.conceptdev.facto.entities.Ordinateur;

public class CoursRequest {
	private String titre;
	private LocalDate dateDebut;
	private boolean besoinVideoprojecteur;
	private boolean besoinOrdiFormateur;
	private boolean besoinOrdiStagiaire;
	private boolean besoinSalle;
	private Integer idFormation;
	private Integer idFormateur;
	private Integer idMatiere;
	private Integer idSalle;
	private List<Integer> idsOrdinateurs;
	private Integer idVideoprojecteur;
	
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

	public Integer getIdFormation() {
		return idFormation;
	}

	public void setIdFormation(Integer idFormation) {
		this.idFormation = idFormation;
	}

	public Integer getIdFormateur() {
		return idFormateur;
	}

	public void setIdFormateur(Integer idFormateur) {
		this.idFormateur = idFormateur;
	}

	public Integer getIdMatiere() {
		return idMatiere;
	}

	public void setIdMatiere(Integer idMatiere) {
		this.idMatiere = idMatiere;
	}

	public Integer getIdSalle() {
		return idSalle;
	}

	public void setIdSalle(Integer idSalle) {
		this.idSalle = idSalle;
	}
	
	public List<Integer> getIdsOrdinateurs() {
		return idsOrdinateurs;
	}

	public void setIdsOrdinateurs(List<Integer> idsOrdinateurs) {
		this.idsOrdinateurs = idsOrdinateurs;
	}

	public Integer getIdVideoprojecteur() {
		return idVideoprojecteur;
	}

	public void setIdVideoprojecteur(Integer idVideoprojecteur) {
		this.idVideoprojecteur = idVideoprojecteur;
	}
	
}
