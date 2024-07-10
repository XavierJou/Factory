package factory.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
