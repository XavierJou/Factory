package formation.conceptdev.facto.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.entities.Salle;

public class SalleResponse {

	@JsonView(CustomJsonViews.Common.class)
	private Integer id;

	@JsonView(CustomJsonViews.Common.class)
	private String nom;

	@JsonView(CustomJsonViews.Common.class)
	private Integer capacite;
	
	@JsonView(CustomJsonViews.SalleResponseWithCours.class)
	private List<CoursResponse> cours;

	// Constructeur par défaut
	public SalleResponse() {
	}
	
	public SalleResponse(Salle salleEntity) {
		this(salleEntity,true);
	}

	// Constructeur pour initialiser à partir d'un objet Salle
	public SalleResponse(Salle salleEntity, boolean bool) {
		this.id = salleEntity.getId();
		this.nom = salleEntity.getNom();
		this.capacite = salleEntity.getCapacite();
		
		if (bool) {
			if (salleEntity.getCours() != null) {
                this.cours = salleEntity.getCours().stream()
                		.map(cours -> new CoursResponse(cours, false,false, false,false, false,false))
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getCapacite() {
		return capacite;
	}

	public void setCapacite(Integer capacite) {
		this.capacite = capacite;
	}

	public List<CoursResponse> getCours() {
		return cours;
	}

	public void setCours(List<CoursResponse> cours) {
		this.cours = cours;
	}
	
	

	
	
	
}