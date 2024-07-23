package formation.conceptdev.facto.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;


import formation.conceptdev.facto.entities.Videoprojecteur;

public class VideoprojecteurResponse {

	@JsonView(CustomJsonViews.Common.class)
	private Integer id;

	@JsonView(CustomJsonViews.Common.class)
	private String marque;

	@JsonView(CustomJsonViews.Common.class)
	private String nom;
	
	@JsonView(CustomJsonViews.VideoprojecteurResponse.class)
	private List<CoursResponse> cours;

	// Constructeur par défaut
	public VideoprojecteurResponse() {
	}

	public VideoprojecteurResponse(Videoprojecteur videoprojecteurEntity) {
		this(videoprojecteurEntity,true);
	}

	// Constructeur pour initialiser à partir d'un objet Salle
	public VideoprojecteurResponse(Videoprojecteur videoprojecteurEntity, boolean bool) {
		
		BeanUtils.copyProperties(videoprojecteurEntity, this, "cours");
		
		if (bool) {
			if (videoprojecteurEntity.getCours() != null) {
                this.cours = videoprojecteurEntity.getCours().stream()
                		.map(cours -> new CoursResponse(cours, false,false, false,false, false,false,false))
                        .collect(Collectors.toList());
            }
		}
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<CoursResponse> getCours() {
		return cours;
	}

	public void setCours(List<CoursResponse> cours) {
		this.cours = cours;
	}
	
}