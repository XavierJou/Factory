package formation.conceptdev.facto.dto.response;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.entities.CompetenceFormateur;


public class CompetenceFormateurResponse {
	
	 @JsonView(CustomJsonViews.Common.class)
	    private Integer id;
	    @JsonView(CustomJsonViews.CompetenceFormateurResponseWithDetail.CompetenceFormateurResponseWithCompetence.class)
	    private CompetenceResponse competence;
	    @JsonView(CustomJsonViews.CompetenceFormateurResponseWithDetail.CompetenceFormateurResponseWithFormateur.class)
	    private FormateurResponse formateur;

	    public CompetenceFormateurResponse() {
	    }

	    public CompetenceFormateurResponse(CompetenceFormateur competenceFormateur,boolean besoinCompetence, boolean besoinMatiere) {
	    	BeanUtils.copyProperties(competenceFormateur, this, "formateur","competence");
	    	
			if (besoinCompetence) {
				if (competenceFormateur.getCompetence() != null) {
					this.setCompetence(new CompetenceResponse(competenceFormateur.getCompetence(),false,false));
				}
			}
			
			if (besoinMatiere) {
				if (competenceFormateur.getFormateur() != null) {
					this.setFormateur(new FormateurResponse(competenceFormateur.getFormateur(),false,false,false,false));
				}
			}
	    }

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public CompetenceResponse getCompetence() {
			return competence;
		}

		public void setCompetence(CompetenceResponse competence) {
			this.competence = competence;
		}

		public FormateurResponse getFormateur() {
			return formateur;
		}

		public void setFormateur(FormateurResponse formateur) {
			this.formateur = formateur;
		}
	    
	    

}
