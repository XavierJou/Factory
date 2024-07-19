package formation.conceptdev.facto.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "formateur")
public class Formateur {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_formateur")
	private Integer id;

    
    @OneToMany(mappedBy = "formateur")
    private List<Cours> cours;
    
    
    @OneToMany(mappedBy = "formateur")
    private List<CompetenceFormateur> competenceFormateurs;
 
   
    @OneToMany(mappedBy = "formateur")
    private List<DisponibiliteFormateur> disponibiliteFormateurs;
    
    @OneToOne
    @JoinColumn(name="id_utilisateur")
    private Utilisateur utilisateur;
    
    
    public Formateur() {
    }

    
    

	public Formateur(List<Cours> cours, List<CompetenceFormateur> competenceFormateurs,
			List<DisponibiliteFormateur> disponibiliteFormateurs, Utilisateur utilisateur) {
		
		this.cours = cours;
		this.competenceFormateurs = competenceFormateurs;
		this.disponibiliteFormateurs = disponibiliteFormateurs;
		this.utilisateur = utilisateur;
	}




	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public List<Cours> getCours() {
		return cours;
	}


	public void setCours(List<Cours> cours) {
		this.cours = cours;
	}


	public List<CompetenceFormateur> getCompetenceFormateurs() {
		return competenceFormateurs;
	}


	public void setCompetenceFormateurs(List<CompetenceFormateur> competenceFormateurs) {
		this.competenceFormateurs = competenceFormateurs;
	}


	public List<DisponibiliteFormateur> getDisponibiliteFormateurs() {
		return disponibiliteFormateurs;
	}


	public void setDisponibiliteFormateurs(List<DisponibiliteFormateur> disponibiliteFormateurs) {
		this.disponibiliteFormateurs = disponibiliteFormateurs;
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

    

    

}
