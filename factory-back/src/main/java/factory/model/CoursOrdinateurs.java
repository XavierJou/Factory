package factory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name="coursOrdinateurs")
@Entity
public class CoursOrdinateurs {

	@Id
	@Column(name="id_coursOrdinateurs")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name="id_cours")
	private Cours cours;
	
	@ManyToOne
	@JoinColumn(name="id_ordinateur")
	private Ordinateur ordinateur;
}

