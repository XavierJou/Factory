package formation.conceptdev.facto.dto.request;

public class CompetenceMatiereRequest {
	
	 private Integer competenceId;
	    private Integer matiereId;

	    
	    public CompetenceMatiereRequest()
	    {
	    	
	    }
	    // Getters and Setters
	    public Integer getCompetenceId() {
	        return competenceId;
	    }

	    public void setCompetenceId(Integer competenceId) {
	        this.competenceId = competenceId;
	    }

	    public Integer getMatiereId() {
	        return matiereId;
	    }

	    public void setMatiereId(Integer matiereId) {
	        this.matiereId = matiereId;
	    }

}
