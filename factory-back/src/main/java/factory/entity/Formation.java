package factory.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "formation")
public class Formation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_formation")
	private Integer id;

	private String titre;
	private Integer duree;
	private String objectif;
	private String contenu;
	@Column(name = "date_debut")
	private LocalDateTime dateDebut;
	private Integer capacite;
	
	@OneToMany(mappedBy = "formation", fetch = FetchType.LAZY)
	private List<Cours> cours;
	
	
	@OneToMany(mappedBy = "formation", fetch = FetchType.LAZY)
	private List<Prerequis> prerequis;
	
	
	@OneToMany(mappedBy = "formation", fetch = FetchType.LAZY)
	private List<Stagiaire> stagiaires;

	public Formation() {
	}

	public Formation(String titre, Integer duree, String objectif, String contenu, LocalDateTime dateDebut, Integer capacite) {
		this.titre = titre;
		this.duree = duree;
		this.objectif = objectif;
		this.contenu = contenu;
		this.dateDebut = dateDebut;
		this.capacite = capacite;
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Integer getDuree() {
		return duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Integer getCapacite() {
		return capacite;
	}

	public void setCapacite(Integer capacite) {
		this.capacite = capacite;
	}

	public List<Cours> getCours() {
		return cours;
	}

	public void setCours(List<Cours> cours) {
		this.cours = cours;
	}

	public List<Prerequis> getPrerequis() {
		return prerequis;
	}

	public void setPrerequis(List<Prerequis> prerequis) {
		this.prerequis = prerequis;
	}

	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}
	
	

}
