package formation.conceptdev.facto.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.entities.Formation;


public class FormationResponse {
	@JsonView(CustomJsonViews.Common.class)
	private Integer id;
	@JsonView(CustomJsonViews.Common.class)
	private String titre;
	@JsonView(CustomJsonViews.Common.class)
	private Integer duree;
	@JsonView(CustomJsonViews.Common.class)
	private String objectif;
	@JsonView(CustomJsonViews.Common.class)
	private String contenu;
	@JsonView(CustomJsonViews.Common.class)
	private LocalDateTime dateDebut;

	@JsonView(CustomJsonViews.FormationWithStagiaire.class)
	private List<StagiaireResponse> stagiaires;
	
	@JsonView(CustomJsonViews.FormationWithCours.class)
	private List<CoursResponse> cours;
	
	@JsonView(CustomJsonViews.FormationWithPrerequis.class)
	private List<PrerequisResponse> prerequis;

	public FormationResponse() {
	}

	public FormationResponse(Formation formation, boolean bool) {
		BeanUtils.copyProperties(formation, this, "stagiaires", "cours");
		if (bool) {
			if (formation.getStagiaires() != null) {
				this.setStagiaires(formation.getStagiaires().stream()
						.map(entity -> new StagiaireResponse(entity, false))
						.collect(Collectors.toList()));
			}
			if (formation.getCours() != null) {
				this.setCours(formation.getCours().stream()
						.map(entity -> new CoursResponse(entity, false, false, false, false, false, false,false))
						.collect(Collectors.toList()));
			}
		}
	}
	
	public FormationResponse(Formation formation) {
		this(formation, true);
	}
	
	

	public List<CoursResponse> getCours() {
		return cours;
	}

	public void setCours(List<CoursResponse> cours) {
		this.cours = cours;
	}

	public List<PrerequisResponse> getPrerequis() {
		return prerequis;
	}

	public void setPrerequis(List<PrerequisResponse> prerequis) {
		this.prerequis = prerequis;
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

	public Integer getDuree() {
		return duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}

	public List<StagiaireResponse> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<StagiaireResponse> stagiaires) {
		this.stagiaires = stagiaires;
	}

}
