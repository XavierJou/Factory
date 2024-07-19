package formation.conceptdev.facto.dto.request;

import java.time.LocalDate;

public class DisponibiliteFormateurRequest {
	
	private Integer formateurId;
	private LocalDate debut;
	private LocalDate fin;
	
	public DisponibiliteFormateurRequest()
	{
		
	}

	public Integer getFormateurId() {
		return formateurId;
	}

	public void setFormateurId(Integer formateurId) {
		this.formateurId = formateurId;
	}

	public LocalDate getDebut() {
		return debut;
	}

	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}
	
	

}
