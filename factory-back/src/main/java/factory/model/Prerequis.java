package factory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prerequis")
public class Prerequis {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prerequis")
	private int id;

	private String nom;

	public Prerequis() {
	}

	public Prerequis(String nom) {
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
	
	
}