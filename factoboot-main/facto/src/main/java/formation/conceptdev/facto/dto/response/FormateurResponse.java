package formation.conceptdev.facto.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.entities.Formateur;


public class FormateurResponse {
	
    @JsonView(CustomJsonViews.Common.class)
    private Integer id;
    
    @JsonView(CustomJsonViews.FormateurResponseWithCours.class)
    private List<CoursResponse> cours;
    
    @JsonView(CustomJsonViews.FormateurResponseWithCompetenceFormateur.class)
    private List<CompetenceFormateurResponse> competenceFormateurResponse;
    
    @JsonView(CustomJsonViews.FormateurResponseWithDisponibiliteFormateur.class) 
    private List<DisponibiliteFormateurResponse> disponibiliteFormateurResponse;
    
    @JsonView(CustomJsonViews.FormateurResponseWithUtilisateur.class) 
    private UtilisateurResponse utilisateur;

    public FormateurResponse() {
        
    }
    
 public FormateurResponse(Formateur formateurEntity) {
        this(formateurEntity,true,true,true,true);
    }
 
 public FormateurResponse(Formateur formateurEntity, 
		 boolean besoincours, 
		 boolean besoinCompetenceFormateurResponses,
		 boolean besoinDisponibiliteFormateurResponse,
		 boolean besoinUtlisateur) {
	 
	 BeanUtils.copyProperties(formateurEntity, this, "cours", "competenceFormateurResponse", "disponibiliteFormateurResponse", "utilisateur");
		
	 	if (besoincours) {
			if (formateurEntity.getCours() != null) {
				this.setCours(formateurEntity.getCours().stream()
						.map(formateur -> new CoursResponse(formateur, false, false, false, false, false, false,false))
						.collect(Collectors.toList()));
			}
		}
	 	
	 	if (besoinCompetenceFormateurResponses) {
			if (formateurEntity.getCompetenceFormateurs() != null) {
				this.setCompetenceFormateurResponse(formateurEntity.getCompetenceFormateurs().stream()
						.map(formateur -> new CompetenceFormateurResponse(formateur, false,false))
						.collect(Collectors.toList()));
			}
		}
	 	
	 	if (besoinDisponibiliteFormateurResponse) {
			if (formateurEntity.getDisponibiliteFormateurs() != null) {
				this.setDisponibiliteFormateurResponse(formateurEntity.getDisponibiliteFormateurs().stream()
						.map(formateur -> new DisponibiliteFormateurResponse(formateur, false))
						.collect(Collectors.toList()));
			}
		}
	 	
	 	if (besoinUtlisateur) {
			if (formateurEntity.getUtilisateur() != null) {
				this.setUtilisateur(new UtilisateurResponse(formateurEntity.getUtilisateur(),false,false));
			}
		}
     
 }

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public List<CoursResponse> getCours() {
	return cours;
}

public void setCours(List<CoursResponse> cours) {
	this.cours = cours;
}

public List<CompetenceFormateurResponse> getCompetenceFormateurResponse() {
	return competenceFormateurResponse;
}

public void setCompetenceFormateurResponse(List<CompetenceFormateurResponse> competenceFormateurResponse) {
	this.competenceFormateurResponse = competenceFormateurResponse;
}

public List<DisponibiliteFormateurResponse> getDisponibiliteFormateurResponse() {
	return disponibiliteFormateurResponse;
}

public void setDisponibiliteFormateurResponse(List<DisponibiliteFormateurResponse> disponibiliteFormateurResponse) {
	this.disponibiliteFormateurResponse = disponibiliteFormateurResponse;
}

public UtilisateurResponse getUtilisateur() {
	return utilisateur;
}

public void setUtilisateur(UtilisateurResponse utilisateur) {
	this.utilisateur = utilisateur;
}
 
 
 
 
 
 
 

    
}