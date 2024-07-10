package factoryrest.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import factory.model.Filiere;

public class FiliereResponse {
	@JsonView(CustomJsonViews.Common.class)
	private Integer id;
	@JsonView(CustomJsonViews.Common.class)
	private String libelle;
	@JsonView(CustomJsonViews.Common.class)
	private LocalDate debut;
	@JsonView(CustomJsonViews.Common.class)
	private LocalDate fin;
	@JsonView(CustomJsonViews.FiliereWithStagiaire.class)
	private List<StagiaireResponse> stagiaires;

	public FiliereResponse() {
	}

	public FiliereResponse(Filiere filiere, boolean bool) {
		BeanUtils.copyProperties(filiere, this, "stagiaires");
		if (bool) {
			if (filiere.getStagiaires() != null) {
				this.setStagiaires(filiere.getStagiaires().stream()
						.map(stagiaireEntity -> new StagiaireResponse(stagiaireEntity, false))
						.collect(Collectors.toList()));
			}
		}
	}

	public FiliereResponse(Filiere filiere) {
		this(filiere, true);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public LocalDate getDebut() {
		return debut;
	}

	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

	public List<StagiaireResponse> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<StagiaireResponse> stagiaires) {
		this.stagiaires = stagiaires;
	}

}
