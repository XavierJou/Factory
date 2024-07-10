package factory.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordinateur")
public class Ordinateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ordinateur")
	private int id;

	private String nom;
	private String marque;
	private String os;
	@Column(name = "date_achat")
	private LocalDate dateAchat;

	@ManyToOne
	@JoinColumn(name = "cours", nullable = false)
	private Cours cours;

	public Ordinateur() {
	}

	public Ordinateur(String nom, String marque, String os, LocalDate dateAchat) {
		this.nom = nom;
		this.marque = marque;
		this.os = os;
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

}
