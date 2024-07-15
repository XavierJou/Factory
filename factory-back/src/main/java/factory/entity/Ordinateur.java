package factory.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordinateur")
public class Ordinateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ordinateur")
	private Integer id;

	private String nom;
	private String marque;
	private String os;
	@Column(name = "date_achat")
	private LocalDate dateAchat;

	@OneToMany(mappedBy = "ordinateur", fetch = FetchType.LAZY)
	private List<CoursOrdinateurs> cours;

	public Ordinateur() {
	}

	

	public Ordinateur(String nom, String marque, String os, LocalDate dateAchat, List<CoursOrdinateurs> cours) {
		super();
		this.nom = nom;
		this.marque = marque;
		this.os = os;
		this.dateAchat = dateAchat;
		this.cours = cours;
	}
	
	public Ordinateur(String nom, String marque, String os, LocalDate dateAchat) {
		super();
		this.nom = nom;
		this.marque = marque;
		this.os = os;
		this.dateAchat = dateAchat;		
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

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public LocalDate getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(LocalDate dateAchat) {
		this.dateAchat = dateAchat;
	}



	public List<CoursOrdinateurs> getCours() {
		return cours;
	}



	public void setCours(List<CoursOrdinateurs> cours) {
		this.cours = cours;
	}

	
}
