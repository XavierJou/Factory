package factory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prerequis")
public class Prerequis {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prerequis")
	private int id;

	private String nom;
	
	@ManyToOne
	@JoinColumn(name="id_formation")
	private Formation formation;
	
	public Prerequis() {
	}

	public Prerequis(String nom, Formation formation) {
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	
	
}