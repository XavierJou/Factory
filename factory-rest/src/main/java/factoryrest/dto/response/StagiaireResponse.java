package factoryrest.dto.response;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import factory.entity.Stagiaire;

public class StagiaireResponse {
	@JsonView(CustomJsonViews.Common.class)
	private Integer id;

	@JsonView(CustomJsonViews.StagiaireWithFormation.class)
	private FormationResponse formation;

	public StagiaireResponse() {

	}

	public StagiaireResponse(Stagiaire stagiaire) {
		this(stagiaire, true);
	}

	public StagiaireResponse(Stagiaire stagiaireEntity, boolean bool) {
		BeanUtils.copyProperties(stagiaireEntity, this, "formation");
		if (bool) {
			if (stagiaireEntity.getFormation() != null) {
				this.setFormation(new FormationResponse(stagiaireEntity.getFormation(), false));
			}
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FormationResponse getFormation() {
		return formation;
	}

	public void setFormation(FormationResponse formation) {
		this.formation = formation;
	}

}
