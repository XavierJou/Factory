package formation.conceptdev.facto.dto.response;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.entities.DisponibiliteFormateur;




public class DisponibiliteFormateurResponse {

	
    @JsonView(CustomJsonViews.Common.class)
	private Integer id;

    
    
    
    @JsonView(CustomJsonViews.Common.class)
	private LocalDate dateDebut;

    @JsonView(CustomJsonViews.Common.class)
	private LocalDate dateFin;
    
    @JsonView(CustomJsonViews.DisponibiliteFormateurResponseWithDetails.class)
    private FormateurResponse formateur;
    
    public DisponibiliteFormateurResponse(DisponibiliteFormateur disponibiliteFormateurEntity)
    {
    	this(disponibiliteFormateurEntity,true);
    }
    
    public DisponibiliteFormateurResponse(DisponibiliteFormateur disponibiliteFormateurEntity, boolean bool)
    {
    	 BeanUtils.copyProperties(disponibiliteFormateurEntity, this, "formateur");

    	if (bool) {
			if (disponibiliteFormateurEntity.getFormateur() != null) {
				this.setFormateur(new FormateurResponse(disponibiliteFormateurEntity.getFormateur(),false,false,false,false));
			}
		}
    }
    
    public DisponibiliteFormateurResponse()
    {
    	
    }
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public FormateurResponse getFormateur() {
		return formateur;
	}

	public void setFormateur(FormateurResponse formateur) {
		this.formateur = formateur;
	}
    
    
	
	
}
