package formation.conceptdev.facto.dto.response;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.entities.CompetenceMatiere;



public class CompetenceMatiereResponse {
	
	   @JsonView(CustomJsonViews.Common.class)
	    private Integer id;
	    @JsonView(CustomJsonViews.CompetenceMatiereResponseWithCompetence.class)
	    private CompetenceResponse competence;
	    @JsonView(CustomJsonViews.CompetenceMatiereResponseWithMatiere.class)
	    private MatiereResponse matiere;

	    public CompetenceMatiereResponse() {
	    }
	    
	    public CompetenceMatiereResponse(CompetenceMatiere competenceMatiereEntity, boolean besoinCompetence, boolean besoinmatiere) {
//			this.setId(stagiaireEntity.getId());
//			this.setPrenom(stagiaireEntity.getPrenom());
//			this.setEmail(stagiaireEntity.getEmail());
//			this.setNom(stagiaireEntity.getNom());
			BeanUtils.copyProperties(competenceMatiereEntity, this, "competence","matiere");
			if (besoinCompetence) {
				if (competenceMatiereEntity.getCompetence() != null) {
					this.setCompetence(new CompetenceResponse(competenceMatiereEntity.getCompetence(),false,false));
				}
			}
			if (besoinmatiere) {
				if (competenceMatiereEntity.getCompetence() != null) {
					this.setMatiere(new MatiereResponse(competenceMatiereEntity.getMatiere(),false,false));
				}
			}
		}
	    
	    public CompetenceMatiereResponse(CompetenceMatiere competenceMatiereEntity) {
	    	this(competenceMatiereEntity,true,true);
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

		public MatiereResponse getMatiere() {
			return matiere;
		}

		public void setMatiere(MatiereResponse matiere) {
			this.matiere = matiere;
		}
	    
	    
	    
	    


}
