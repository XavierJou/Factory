package formation.conceptdev.facto.dto.request;

import java.time.LocalDate;

public class DisponibiliteFormateurRequest {
	
	private Integer formateurId;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	
	public DisponibiliteFormateurRequest()
	{
		
	}

	public Integer getFormateurId() {
		return formateurId;
	}

	public void setFormateurId(Integer formateurId) {
		this.formateurId = formateurId;
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
