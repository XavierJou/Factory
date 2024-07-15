package factory.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "competence")
public class Competence {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_competence")
	private Integer id;

    @Column(name = "nom_competence", nullable = false, unique = true)
    private String nom;
    
    @OneToMany(mappedBy="competence")
    private List<CompetenceFormateur> competenceFormateurs;
   
    @OneToMany(mappedBy="competence")
    private List<CompetenceMatiere> competenceMatieres;

    public Competence() {
    }

   



	public Competence(String nom, List<CompetenceFormateur> competenceFormateurs,
			List<CompetenceMatiere> competenceMatieres) {
				this.nom = nom;
		this.competenceFormateurs = competenceFormateurs;
		this.competenceMatieres = competenceMatieres;
	}
	
	public Competence(String nom) {
				this.nom = nom;
		
	}





	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	public List<CompetenceFormateur> getCompetenceFormateurs() {
		return competenceFormateurs;
	}

	public void setCompetenceFormateurs(List<CompetenceFormateur> competenceFormateurs) {
		this.competenceFormateurs = competenceFormateurs;
	}





	public List<CompetenceMatiere> getCompetenceMatieres() {
		return competenceMatieres;
	}





	public void setCompetenceMatieres(List<CompetenceMatiere> competenceMatieres) {
		this.competenceMatieres = competenceMatieres;
	}

	

}
