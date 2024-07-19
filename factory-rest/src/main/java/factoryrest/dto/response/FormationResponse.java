package factoryrest.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import factory.entity.Formation;



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

	public FormationResponse() {
	}

	public FormationResponse(Formation formation, boolean bool) {
		BeanUtils.copyProperties(formation, this, "stagiaires");
		if (bool) {
			if (formation.getStagiaires() != null) {
				this.setStagiaires(formation.getStagiaires().stream()
						.map(stagiaireEntity -> new StagiaireResponse(stagiaireEntity, false))
						.collect(Collectors.toList()));
			}
		}
	}

	public FormationResponse(Formation formation) {
		this(formation, true);
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
