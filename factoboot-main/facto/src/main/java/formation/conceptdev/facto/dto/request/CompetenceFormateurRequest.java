package formation.conceptdev.facto.dto.request;

public class CompetenceFormateurRequest {
	private Integer competenceId;
    private Integer formateurId;
    
    

    public CompetenceFormateurRequest() {
	}

	// Getters and Setters
    public Integer getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(Integer competenceId) {
        this.competenceId = competenceId;
    }

    public Integer getFormateurId() {
        return formateurId;
    }

    public void setFormateurId(Integer formateurId) {
        this.formateurId = formateurId;
    }
}
