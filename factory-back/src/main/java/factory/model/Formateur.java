package factory.model;

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
import jakarta.persistence.Transient;

@Entity
@Table(name = "formateur")
public class Formateur {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_formateur")
	private int id;

    
    @OneToMany(mappedBy = "formateur")
    private List<Cours> cours;
    
    
    @OneToMany(mappedBy = "formateur")
    private List<CompetenceFormateur> competences;
 
   
    @OneToMany(mappedBy = "formateur")
    private List<DisponibiliteFormateur> dispos;
    
    @OneToOne
    @JoinColumn(name="id_utilisateur")
    private Utilisateur utilisateur;
    
    
    public Formateur() {
    }

    

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public List<Cours> getCours() {
		return cours;
	}

	public void setCours(List<Cours> cours) {
		this.cours = cours;
	}

	public List<CompetenceFormateur> getCompetences() {
		return competences;
	}

	public void setCompetences(List<CompetenceFormateur> competences) {
		this.competences = competences;
	}

	public List<DisponibiliteFormateur> getDispos() {
		return dispos;
	}

	public void setDispos(List<DisponibiliteFormateur> dispos) {
		this.dispos = dispos;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
    
    

}
