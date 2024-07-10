package factory.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "disponibilite_formateur")
public class DisponibiliteFormateur {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_formateur")
	private int id;

    @Column(name = "date_debut")
	private LocalDate dateDebut;

    @Column(name = "date_fin")
	private LocalDate dateFin;

}
