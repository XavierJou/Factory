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
@Table(name = "salle")
public class Salle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_salle")
	private Integer id;

	private String nom;
	private Integer capacite;


	@OneToMany(mappedBy="salle")
	private List<Cours> cours;


	
	public Salle( String nom, Integer capacite, List<Cours> cours) {
		
		this.nom = nom;
		this.capacite = capacite;
		this.cours = cours;
	}
	
	public Salle( String nom, Integer capacite) {
		
		this.nom = nom;
		this.capacite = capacite;	
	}
	
public Salle( ) {
		
		
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

	public Integer getCapacite() {
		return capacite;
	}

	public void setCapacite(Integer capacite) {
		this.capacite = capacite;
	}

	public List<Cours> getCours() {
		return cours;
	}

	public void setCours(List<Cours> cours) {
		this.cours = cours;
	}

	
}