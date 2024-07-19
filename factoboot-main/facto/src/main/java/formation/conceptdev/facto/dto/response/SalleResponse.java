package formation.conceptdev.facto.dto.response;

import com.fasterxml.jackson.annotation.JsonView;

public class SalleResponse {

	@JsonView(CustomJsonViews.Common.class)
	private Integer id;

	@JsonView(CustomJsonViews.Common.class)
	private String nom;

	@JsonView(CustomJsonViews.Common.class)
	private Integer capacite;

	// Constructeur par défaut
	public SalleResponse() {
	}

	// Constructeur pour initialiser à partir d'un objet Salle
	public SalleResponse(Salle salle) {
		this.id = salle.getId();
		this.nom = salle.getNom();
		this.capacite = salle.getCapacite();
	}

	// Getters
	public Integer getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public Integer getCapacite() {
		return capacite;
	}

	// Setters
	public void setId(Integer id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setCapacite(Integer capacite) {
		this.capacite = capacite;
	}
}