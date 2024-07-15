package factory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name="competenceFormateur")
@Entity
public class CompetenceFormateur {
	
	@Id
	@Column(name="id_competenceFormateur")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_competence")
	private Competence competence;
	
	@ManyToOne
	@JoinColumn(name="id_formateur")
	private Formateur formateur;

	public CompetenceFormateur(Integer id, Competence competence, Formateur formateur) {
		this.id = id;
		this.competence = competence;
		this.formateur = formateur;
	}
	
	public CompetenceFormateur( Competence competence, Formateur formateur) {
			this.competence = competence;
		this.formateur = formateur;
	}

	public CompetenceFormateur() {
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

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}
	
	
	
}
