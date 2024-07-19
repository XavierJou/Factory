package formation.conceptdev.facto.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "matiere")
public class Matiere {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_matiere")
	private Integer id;

	private String titre;
	private Integer duree;
	private String objectif;
	private String contenu;
	
	@OneToMany(mappedBy="matiere")
	private List<CompetenceMatiere> compMatiere;
	
	
	@OneToMany(mappedBy="matiere")
	private List<Cours> cours;
	

	public Matiere() {
	}

	
	public Matiere(String titre, Integer duree, String objectif, String contenu, List<CompetenceMatiere> compMatiere,
			List<Cours> cours) {
		this.titre = titre;
		this.duree = duree;
		this.objectif = objectif;
		this.contenu = contenu;
		this.compMatiere = compMatiere;
		this.cours = cours;
	}
	
	public Matiere(String titre, Integer duree, String objectif, String contenu) {
		this.titre = titre;
		this.duree = duree;
		this.objectif = objectif;
		this.contenu = contenu;		
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

	public List<CompetenceMatiere> getCompMatiere() {
		return compMatiere;
	}

	public void setCompMatiere(List<CompetenceMatiere> compMatiere) {
		this.compMatiere = compMatiere;
	}

	public List<Cours> getCours() {
		return cours;
	}

	public void setCours(List<Cours> cours) {
		this.cours = cours;
	}
	
	

}
