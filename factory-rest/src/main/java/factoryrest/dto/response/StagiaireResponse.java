package factoryrest.dto.response;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import factory.model.Stagiaire;

public class StagiaireResponse {
	@JsonView(CustomJsonViews.Common.class)
	private Integer id;
	@JsonView(CustomJsonViews.Common.class)
	private String prenom;
	@JsonView(CustomJsonViews.Common.class)
	private String nom;
	@JsonView({ CustomJsonViews.Common.class })
	private String email;
	@JsonView(CustomJsonViews.StagaireWithFiliere.class)
	private FiliereResponse filiere;

	public StagiaireResponse() {

	}

	public StagiaireResponse(Stagiaire stagiaire) {
		this(stagiaire, true);
	}

	public StagiaireResponse(Stagiaire stagiaireEntity, boolean bool) {
//		this.setId(stagiaireEntity.getId());
//		this.setPrenom(stagiaireEntity.getPrenom());
//		this.setEmail(stagiaireEntity.getEmail());
//		this.setNom(stagiaireEntity.getNom());
		BeanUtils.copyProperties(stagiaireEntity, this, "filiere");
		if (bool) {
			if (stagiaireEntity.getFiliere() != null) {
				this.setFiliere(new FiliereResponse(stagiaireEntity.getFiliere(),false));
			}
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public FiliereResponse getFiliere() {
		return filiere;
	}

	public void setFiliere(FiliereResponse filiere) {
		this.filiere = filiere;
	}

}
