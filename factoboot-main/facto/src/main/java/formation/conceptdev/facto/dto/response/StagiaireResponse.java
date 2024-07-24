package formation.conceptdev.facto.dto.response;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.entities.Stagiaire;

public class StagiaireResponse {
	@JsonView(CustomJsonViews.Common.class)
	private Integer id;

	@JsonView(CustomJsonViews.StagiaireWithFormation.class)
	private FormationResponse formation;
	
	@JsonView(CustomJsonViews.StagiaireWithUtilisateur.class)
	private UtilisateurResponse utilisateur;

	public StagiaireResponse() {

	}

	public StagiaireResponse(Stagiaire stagiaire) {
		this(stagiaire, true,true);
	}

	public StagiaireResponse(Stagiaire stagiaireEntity, boolean besoinFormation, boolean besoinUtilisateur) {
		BeanUtils.copyProperties(stagiaireEntity, this, "formation","utilisateur");
		if (besoinFormation) {
			if (stagiaireEntity.getFormation() != null) {
				this.setFormation(new FormationResponse(stagiaireEntity.getFormation(), false, false, false));
			}
		}
		if (besoinUtilisateur) {
			if (stagiaireEntity.getUtilisateur() != null) {
				this.setUtilisateur(new UtilisateurResponse(stagiaireEntity.getUtilisateur(), false, false));
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

	public UtilisateurResponse getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(UtilisateurResponse utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	

}
