package formation.conceptdev.facto.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "coursOrdinateurs")
@Entity
public class CoursOrdinateurs {

	@Id
	@Column(name = "id_coursOrdinateurs")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_cours")
	private Cours cours;

	@ManyToOne
	@JoinColumn(name = "id_ordinateur")
	private Ordinateur ordinateur;

	public CoursOrdinateurs() {
	}



	public CoursOrdinateurs(Cours cours, Ordinateur ordinateur) {
		this.cours = cours;
		this.ordinateur = ordinateur;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

	public Ordinateur getOrdinateur() {
		return ordinateur;
	}

	public void setOrdinateur(Ordinateur ordinateur) {
		this.ordinateur = ordinateur;
	}

}
