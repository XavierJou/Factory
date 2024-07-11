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
@Table(name = "disponibilite_formateur")
public class DisponibiliteFormateur {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_disponibilite_formateur")
	private Integer id;

    @ManyToOne
    @JoinColumn(name="id_formateur")
    private Formateur formateur;
    
    
    @Column(name = "date_debut")
	private LocalDate dateDebut;

    @Column(name = "date_fin")
	private LocalDate dateFin;
    
    

	public DisponibiliteFormateur() {
		super();
	}
	

	
	public DisponibiliteFormateur(Formateur formateur, LocalDate dateDebut, LocalDate dateFin) {
		this.formateur = formateur;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}
	
	public DisponibiliteFormateur( LocalDate dateDebut, LocalDate dateFin) {
		
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

    
}
