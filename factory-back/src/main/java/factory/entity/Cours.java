package factory.entity;

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
	
	@Column( nullable = false, unique = true)
	private String titre;
	
	@Column(name = "date_debut")
	private LocalDateTime dateDebut;
	
	@Column(name = "besoin_videoprojecteur")
	private Boolean besoinVideoprojecteur;
	
	@Column(name = "besoin_ordi_formateur")
	private Boolean besoinOrdiFormateur;
	
	@Column(name = "besoin_ordi_stagiare")
	private Boolean besoinOrdiStagiaire;
	
	@ManyToOne
	@JoinColumn(name="id_matiere")
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
	@JoinColumn(name="id_videoprojecteur")
	private Videoprojecteur videoprojecteur;
	

	
	@ManyToOne
	@JoinColumn(name="id_salle")
	private Salle salle;

	public Cours() {
	}

	public Cours(LocalDateTime dateDebut,String titre, Boolean besoinVideoprojecteur, Boolean besoinOrdiFormateur, Boolean besoinOrdiStagiaire,
			Matiere matiere, Formateur formateur, Formation formation, List<CoursOrdinateurs> ordinateurs,
			Videoprojecteur videoprojecteur, Salle salle) {
		
		this.dateDebut = dateDebut;
		this.titre=titre;
		this.besoinVideoprojecteur = besoinVideoprojecteur;
		this.besoinOrdiFormateur = besoinOrdiFormateur;
		this.besoinOrdiStagiaire = besoinOrdiStagiaire;
		this.matiere = matiere;
		this.formateur = formateur;
		this.formation = formation;
		this.ordinateurs = ordinateurs;
		this.videoprojecteur = videoprojecteur;
		this.salle = salle;
	}
	
	public Cours(LocalDateTime dateDebut,String titre, Boolean besoinVideoprojecteur, Boolean besoinOrdiFormateur, Boolean besoinOrdiStagiaire) {
		
		this.dateDebut = dateDebut;
		this.titre=titre;
		this.besoinVideoprojecteur = besoinVideoprojecteur;
		this.besoinOrdiFormateur = besoinOrdiFormateur;
		this.besoinOrdiStagiaire = besoinOrdiStagiaire;
		
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

	public Boolean getBesoinVideoprojecteur() {
		return besoinVideoprojecteur;
	}

	public void setBesoinVideoprojecteur(Boolean besoinVideoprojecteur) {
		this.besoinVideoprojecteur = besoinVideoprojecteur;
	}

	public Boolean getBesoinOrdiFormateur() {
		return besoinOrdiFormateur;
	}

	public void setBesoinOrdiFormateur(Boolean besoinOrdiFormateur) {
		this.besoinOrdiFormateur = besoinOrdiFormateur;
	}

	public Boolean getBesoinOrdiStagiaire() {
		return besoinOrdiStagiaire;
	}

	public void setBesoinOrdiStagiaire(Boolean besoinOrdiStagiaire) {
		this.besoinOrdiStagiaire = besoinOrdiStagiaire;
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

	public List<CoursOrdinateurs> getOrdinateurs() {
		return ordinateurs;
	}

	public void setOrdinateurs(List<CoursOrdinateurs> ordinateurs) {
		this.ordinateurs = ordinateurs;
	}

	public Videoprojecteur getVideoprojecteur() {
		return videoprojecteur;
	}

	public void setVideoprojecteur(Videoprojecteur videoprojecteur) {
		this.videoprojecteur = videoprojecteur;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}


	

	

	
}
