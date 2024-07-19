package formation.conceptdev.facto.entities;

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

@Entity
@Table(name = "cours")
public class Cours {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cours")
	private Integer id;
	private String titre;
	@Column(name = "date_debut")
	private LocalDateTime dateDebut;
	private boolean videoprojecteur;
	private boolean ordiFormateur;
	private boolean ordiStagiaire;

	@ManyToOne
	@JoinColumn(name = "id_matiere")
	private Matiere matiere;

	@ManyToOne
	@JoinColumn(name = "id_formateur", nullable = false)
	private Formateur formateur;

	@ManyToOne
	@JoinColumn(name = "id_formation", nullable = false)
	private Formation formation;

	@OneToMany(mappedBy = "cours", fetch = FetchType.LAZY)
	private List<CoursOrdinateurs> ordinateurs;

	@ManyToOne
	@JoinColumn(name = "id_videoprojecteurecteur")
	private Videoprojecteur videoprojecteurecteur;

	@ManyToOne
	@JoinColumn(name = "id_salle")
	private Salle salle;

	public Cours() {
	}

	public Cours(LocalDateTime dateDebut, String titre, boolean videoprojecteur, boolean ordiFormateur, boolean ordiStagiaire,
			Matiere matiere, Formateur formateur, Formation formation, List<CoursOrdinateurs> ordinateurs,
			Videoprojecteur videoprojecteurecteur, Salle salle) {

		this.dateDebut = dateDebut;
		this.titre = titre;
		this.videoprojecteur = videoprojecteur;
		this.ordiFormateur = ordiFormateur;
		this.ordiStagiaire = ordiStagiaire;
		this.matiere = matiere;
		this.formateur = formateur;
		this.formation = formation;
		this.ordinateurs = ordinateurs;
		this.videoprojecteurecteur = videoprojecteurecteur;
		this.salle = salle;
	}

	public Cours(LocalDateTime dateDebut, String titre, boolean videoprojecteur, boolean ordiFormateur,
			boolean ordiStagiaire) {

		this.dateDebut = dateDebut;
		this.titre = titre;
		this.videoprojecteur = videoprojecteur;
		this.ordiFormateur = ordiFormateur;
		this.ordiStagiaire = ordiStagiaire;

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

	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}

	public boolean isVideoproj() {
		return videoprojecteur;
	}

	public void setVideoproj(boolean videoprojecteur) {
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
