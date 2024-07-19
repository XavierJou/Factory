package formation.conceptdev.facto.dto.response;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.entities.Competence;


public class CompetenceResponse {

    @JsonView(CustomJsonViews.Common.class)
    private Integer id;
    @JsonView(CustomJsonViews.Common.class)
    private String nom;
    

    @JsonView(CustomJsonViews.CompetenceWithFormateurs.class)
    private List<CompetenceFormateurResponse> competenceFormateurs;
    @JsonView(CustomJsonViews.CompetenceWithMatieres.class)
    private List<CompetenceMatiereResponse> competenceMatieres;

    
    
    public CompetenceResponse() {
    }
    
    public CompetenceResponse(Competence competence) {
    	this(competence,true,true);
    }

    public CompetenceResponse(Competence competenceEntity, boolean besoinFormateur, boolean besoinMatiere) {
    	
        BeanUtils.copyProperties(competenceEntity, this, "competenceFormateurs", "competenceMatieres");
        if (besoinFormateur) {
            if (competenceEntity.getCompetenceFormateurs() != null) {
                this.competenceFormateurs = competenceEntity.getCompetenceFormateurs().stream()
                		.map(competence -> new CompetenceFormateurResponse(competence, false,false))
                        .collect(Collectors.toList());
            }
            
        }
        
        if (besoinMatiere) {
            if (competenceEntity.getCompetenceMatieres() != null) {
                this.competenceMatieres = competenceEntity.getCompetenceMatieres().stream()
                		.map(competence -> new CompetenceMatiereResponse(competence, false,false))
                        .collect(Collectors.toList());
            }
            
        }
    }

   
    
   

 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<CompetenceFormateurResponse> getCompetenceFormateurs() {
		return competenceFormateurs;
	}

	public void setCompetenceFormateurs(List<CompetenceFormateurResponse> competenceFormateurs) {
		this.competenceFormateurs = competenceFormateurs;
	}

	public List<CompetenceMatiereResponse> getCompetenceMatieres() {
		return competenceMatieres;
	}

	public void setCompetenceMatieres(List<CompetenceMatiereResponse> competenceMatieres) {
		this.competenceMatieres = competenceMatieres;
	}

	
    
    
   
}