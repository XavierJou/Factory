package factory.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public Videoprojecteur() {
	}

	public Videoprojecteur(String nom, String marque, LocalDate dateAchat) {
		this.nom = nom;
		this.marque = marque;
		this.dateAchat = dateAchat;
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
	
	

}
