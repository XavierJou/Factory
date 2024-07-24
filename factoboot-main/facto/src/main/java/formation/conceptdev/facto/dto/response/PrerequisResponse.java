package formation.conceptdev.facto.dto.response;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.entities.Prerequis;


public class PrerequisResponse {

    @JsonView(CustomJsonViews.Common.class)
    private Integer id;

  
    @JsonView(CustomJsonViews.Common.class)
    private String nom;
    
 
    @JsonView(CustomJsonViews.PreRequisWithFormation.class)
    private FormationResponse formation;
    
    
    public PrerequisResponse(Prerequis prerequis) {
		this(prerequis, true);
	}

	public PrerequisResponse(Prerequis prerequisEntity, boolean bool) {
//		this.setId(stagiaireEntity.getId());
//		this.setPrenom(stagiaireEntity.getPrenom());
//		this.setEmail(stagiaireEntity.getEmail());
//		this.setNom(stagiaireEntity.getNom());
		BeanUtils.copyProperties(prerequisEntity, this, "formation");
		if (bool) {
			if (prerequisEntity.getFormation() != null) {
				this.setFormation(new FormationResponse(prerequisEntity.getFormation(),false, false, false));
			}
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public FormationResponse getFormation() {
		return formation;
	}

	public void setFormation(FormationResponse formation) {
		this.formation = formation;
	}

	

    
  
}