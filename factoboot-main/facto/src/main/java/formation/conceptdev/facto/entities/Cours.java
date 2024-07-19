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
	private boolean besoinVideoprojecteur;
	private boolean besoinOrdiFormateur;
	private boolean besoinOrdiStagiaire;
	private boolean besoinSalle;

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
	private List<CoursOrdinateurs> coursOrdinateurs;

	@ManyToOne
	@JoinColumn(name = "id_videoprojecteur")
	private Videoprojecteur videoprojecteur;

	@ManyToOne
	@JoinColumn(name = "id_salle")
	private Salle salle;

	public Cours() {
	}

	public Cours(LocalDateTime dateDebut, String titre, boolean besoinVideoprojecteur, boolean besoinOrdiFormateur, boolean besoinOrdiStagiaire,
			boolean besoinSalle,
			Matiere matiere, Formateur formateur, Formation formation, List<CoursOrdinateurs> ordinateurs,
			Videoprojecteur videoprojecteurecteur, Salle salle) {

		this.dateDebut = dateDebut;
		this.titre = titre;
		this.besoinVideoprojecteur = besoinVideoprojecteur;
		this.besoinOrdiFormateur = besoinOrdiFormateur;
		this.besoinOrdiStagiaire = besoinOrdiStagiaire;
		this.besoinSalle = besoinSalle;
		this.matiere = matiere;
		this.formateur = formateur;
		this.formation = formation;
		this.coursOrdinateurs = ordinateurs;
		this.videoprojecteur = videoprojecteurecteur;
		this.salle = salle;
	}

	public Cours(LocalDateTime dateDebut, String titre, boolean besoinVideoprojecteur, boolean besoinOrdiFormateur, boolean besoinOrdiStagiaire,
			boolean besoinSalle) {

		this.dateDebut = dateDebut;
		this.titre = titre;
		this.besoinVideoprojecteur = besoinVideoprojecteur;
		this.besoinOrdiFormateur = besoinOrdiFormateur;
		this.besoinOrdiStagiaire = besoinOrdiStagiaire;
		this.besoinSalle = besoinSalle;
		
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

	

	

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Videoprojecteur getVideoprojecteur() {
		return videoprojecteur;
	}

	public void setVideoprojecteur(Videoprojecteur videoprojecteur) {
		this.videoprojecteur = videoprojecteur;
	}

	public List<CoursOrdinateurs> getCoursOrdinateurs() {
		return coursOrdinateurs;
	}

	public void setCoursOrdinateurs(List<CoursOrdinateurs> coursOrdinateurs) {
		this.coursOrdinateurs = coursOrdinateurs;
	}

	
	
	

}
