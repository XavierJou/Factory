package formation.conceptdev.facto.dto.response;

import com.fasterxml.jackson.annotation.JsonView;

public class VideoprojecteurResponse {

	@JsonView(CustomJsonViews.Common.class)
	private Integer id;

	@JsonView(CustomJsonViews.Common.class)
	private String marque;

	@JsonView(CustomJsonViews.Common.class)
	private String modele;

	// Constructeur par défaut
	public VideoprojecteurResponse() {
	}

	// Constructeur pour initialiser à partir d'un objet VideoProjecteur
	public VideoprojecteurResponse(VideoProjecteur videoProjecteur) {
		this.id = videoProjecteur.getId();
		this.marque = videoProjecteur.getMarque();
		this.modele = videoProjecteur.getModele();
	}

	// Getters
	public Integer getId() {
		return id;
	}

	public String getMarque() {
		return marque;
	}

	public String getModele() {
		return modele;
	}

	// Setters
	public void setId(Integer id) {
		this.id = id;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}
}