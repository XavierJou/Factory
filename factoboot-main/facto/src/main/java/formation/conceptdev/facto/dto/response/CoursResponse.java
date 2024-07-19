package formation.conceptdev.facto.dto.response;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.entities.Cours;

public class CoursResponse {
	@JsonView(CustomJsonViews.Common.class)
	private Integer id;
	@JsonView(CustomJsonViews.Common.class)
	private String titre;
	@JsonView(CustomJsonViews.Common.class)
	private LocalDateTime dateDebut;
	@JsonView(CustomJsonViews.Common.class)
	private boolean videoproj;
	@JsonView(CustomJsonViews.Common.class)
	private boolean ordiFormateur;
	@JsonView(CustomJsonViews.Common.class)
	private boolean ordiCours;

	@JsonView(CustomJsonViews.CoursWithFormation.class)
	private FormationResponse formation;

	public CoursResponse() {
	}

	public CoursResponse(Cours entity, boolean bool) {
		BeanUtils.copyProperties(entity, this, "formation");
		if (bool) {
			if (entity.getFormation() != null) {
				this.setFormation(new FormationResponse(entity.getFormation(), false));
			}
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}

	public boolean isVideoproj() {
		return videoproj;
	}

	public void setVideoproj(boolean videoproj) {
		this.videoproj = videoproj;
	}

	public boolean isOrdiFormateur() {
		return ordiFormateur;
	}

	public void setOrdiFormateur(boolean ordiFormateur) {
		this.ordiFormateur = ordiFormateur;
	}

	public boolean isOrdiCours() {
		return ordiCours;
	}

	public void setOrdiCours(boolean ordiCours) {
		this.ordiCours = ordiCours;
	}

	public FormationResponse getFormation() {
		return formation;
	}

	public void setFormation(FormationResponse formation) {
		this.formation = formation;
	}

}
