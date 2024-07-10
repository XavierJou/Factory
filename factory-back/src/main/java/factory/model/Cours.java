package factory.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "cours")
public class Cours {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cours")
	private int id;
	@Column(name = "date_debut")
	private LocalDateTime dateDebut;
	private boolean videoproj;
	private boolean ordiFormateur;
	private boolean ordiStagiaire;
	
	@ManyToOne
	@JoinColumn(name="id_matiere")
	private Matiere matiere;

	@ManyToOne
	@JoinColumn(name = "formateur", nullable = false)
	private Formateur formateur;
	
	@ManyToOne
	@JoinColumn(name = "formation", nullable = false)
	private Formation formation;

	@OneToMany(mappedBy = "cours", fetch = FetchType.LAZY)
	private List<CoursOrdinateurs> ordinateurs;
	
	@ManyToOne
	@JoinColumn(name="id_videoprojecteur")
	private Videoprojecteur videoprojecteur;
	

	
	@ManyToOne
	@JoinColumn(name="id_salle")
	private Salle salle;

	public Cours() {
	}

	public Cours(LocalDateTime dateDebut, boolean videoproj, boolean ordiFormateur, boolean ordiStagiaire,
			Matiere matiere, Formateur formateur, Formation formation, List<CoursOrdinateurs> ordinateurs,
			Videoprojecteur videoprojecteur, Salle salle) {
		super();
		this.dateDebut = dateDebut;
		this.videoproj = videoproj;
		this.ordiFormateur = ordiFormateur;
		this.ordiStagiaire = ordiStagiaire;
		this.matiere = matiere;
		this.formateur = formateur;
		this.formation = formation;
		this.ordinateurs = ordinateurs;
		this.videoprojecteur = videoprojecteur;
		this.salle = salle;
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

	public List<CoursOrdinateurs> getCoursOrdinateurs() {
		return ordinateurs;
	}

	public void setCoursOrdinateurs(List<CoursOrdinateurs> ordinateurs) {
		this.ordinateurs = ordinateurs;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	
}
