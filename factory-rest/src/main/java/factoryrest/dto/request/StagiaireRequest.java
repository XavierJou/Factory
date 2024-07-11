package factoryrest.dto.request;

import jakarta.validation.constraints.NotNull;

public class StagiaireRequest {
	private Integer id;

	@NotNull
	private Integer idFormation;

	public StagiaireRequest() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdFormation() {
		return idFormation;
	}

	public void setIdFormation(Integer idFormation) {
		this.idFormation = idFormation;
	}

}
