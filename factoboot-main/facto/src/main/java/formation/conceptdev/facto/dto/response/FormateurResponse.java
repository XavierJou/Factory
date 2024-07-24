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
    private List<CompetenceFormateurResponse> competenceFormateur;
    
    @JsonView(CustomJsonViews.FormateurResponseWithDisponibiliteFormateur.class) 
    private List<DisponibiliteFormateurResponse> disponibiliteFormateur;
    
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
				this.setCompetenceFormateur(formateurEntity.getCompetenceFormateurs().stream()
						.map(formateur -> new CompetenceFormateurResponse(formateur, true,false))
						.collect(Collectors.toList()));
			}
		}
	 	
	 	if (besoinDisponibiliteFormateurResponse) {
			if (formateurEntity.getDisponibiliteFormateurs() != null) {
				this .setDisponibiliteFormateur(formateurEntity.getDisponibiliteFormateurs().stream()
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



public UtilisateurResponse getUtilisateur() {
	return utilisateur;
}

public void setUtilisateur(UtilisateurResponse utilisateur) {
	this.utilisateur = utilisateur;
}

public List<CompetenceFormateurResponse> getCompetenceFormateur() {
	return competenceFormateur;
}

public void setCompetenceFormateur(List<CompetenceFormateurResponse> competenceFormateur) {
	this.competenceFormateur = competenceFormateur;
}

public List<DisponibiliteFormateurResponse> getDisponibiliteFormateur() {
	return disponibiliteFormateur;
}

public void setDisponibiliteFormateur(List<DisponibiliteFormateurResponse> disponibiliteFormateur) {
	this.disponibiliteFormateur = disponibiliteFormateur;
}
 
 
 
 
 
 
 

    
}