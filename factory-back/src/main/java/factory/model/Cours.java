package factory.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cours")
public class Cours {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cours")
	private int id;
	@Column(name = "date_debut")
	private LocalDateTime dateDebut;
	private boolean videoprojecteur;
	private boolean ordiFormateur;
	private boolean ordiStagiaire;

	private Matiere matiere;

	@ManyToOne
	@JoinColumn(name = "formateur", nullable = false)
	private Formateur formateur;

	@ManyToOne
	@JoinColumn(name = "formation", nullable = false)
	private Formation formation;

	@OneToMany(mappedBy = "cours", fetch = FetchType.LAZY)
	private List<Ordinateur> ordinateurs;

	public Cours() {
	}

	public Cours(LocalDateTime dateDebut, boolean videoprojecteur, boolean ordiFormateur, boolean ordiStagiaire,
			Matiere matiere) {
		this.dateDebut = dateDebut;
		this.videoprojecteur = videoprojecteur;
		this.ordiFormateur = ordiFormateur;
		this.ordiStagiaire = ordiStagiaire;
		this.matiere = matiere;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}

	public boolean isVideoprojecteur() {
		return videoprojecteur;
	}

	public void setVideoprojecteur(boolean videoprojecteur) {
		this.videoprojecteur = videoprojecteur;
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

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

}
