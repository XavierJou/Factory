package formation.conceptdev.facto.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="competenceMatiere")
public class CompetenceMatiere {

	@Id
	@Column(name="id_competenceMatiere")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="id_competence")
	private Competence competence;
	@ManyToOne
	@JoinColumn(name="id_matiere")
	private	Matiere matiere;
	
	
	public CompetenceMatiere(Competence competence, Matiere matiere) {
		this.competence = competence;
		this.matiere = matiere;
	}


	public CompetenceMatiere() {
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Competence getCompetence() {
		return competence;
	}


	public void setCompetence(Competence competence) {
		this.competence = competence;
	}


	public Matiere getMatiere() {
		return matiere;
	}


	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	
	
	
	
}
