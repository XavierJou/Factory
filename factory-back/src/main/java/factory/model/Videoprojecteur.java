package factory.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "videoprojecteur")
public class Videoprojecteur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_videoprojecteur")
	private int id;

	private String nom;
	private String marque;
	@Column(name = "date_achat")
	private LocalDate dateAchat;
	@OneToMany(mappedBy="videoprojecteur")
	private List<Cours> cours;
	

	public Videoprojecteur() {
	}

	
	public Videoprojecteur(String nom, String marque, LocalDate dateAchat, List<Cours> cours) {
		super();
		this.nom = nom;
		this.marque = marque;
		this.dateAchat = dateAchat;
		this.cours = cours;
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

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public LocalDate getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(LocalDate dateAchat) {
		this.dateAchat = dateAchat;
	}


	public List<Cours> getCours() {
		return cours;
	}


	public void setCours(List<Cours> cours) {
		this.cours = cours;
	}
	
	

}
