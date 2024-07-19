package formation.conceptdev.facto.dto.request;

import jakarta.validation.constraints.NotNull;

public class StagiaireRequest {

	@NotNull
	private Integer idFormation;

	public StagiaireRequest() {

	}

	public Integer getIdFormation() {
		return idFormation;
	}

	public void setIdFormation(Integer idFormation) {
		this.idFormation = idFormation;
	}

}
