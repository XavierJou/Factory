package formation.conceptdev.facto.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.entities.DisponibiliteFormateur;

public class FormateurPossiblePourCours {
	
	@JsonView(CustomJsonViews.Common.class)
	Integer id;
	@JsonView(CustomJsonViews.Common.class)
	Integer id_utilisateur;
	@JsonView(CustomJsonViews.Common.class)
	String nom;
	@JsonView(CustomJsonViews.Common.class)
	String prenom;
	@JsonView(CustomJsonViews.FormateurPossiblePourCoursWithDisponibiliteFormateur.class)
	List<DisponibiliteFormateur> disponibiliteFormateurs;
	
	FormateurPossiblePourCours()
	{
		
	}


	
	public FormateurPossiblePourCours( Integer id, Integer id_utilisateur, String nom, String prenom,List<DisponibiliteFormateur> disponibiliteFormateurs) {
		this.id = id;
		this.id_utilisateur = id_utilisateur;
		this.nom = nom;
		this.prenom = prenom;
		if (disponibiliteFormateurs!=null)
		{
			this.disponibiliteFormateurs= disponibiliteFormateurs;
		}
		
	
			
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_utilisateur() {
		return id_utilisateur;
	}

	public void setId_utilisateur(Integer id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<DisponibiliteFormateur> getDisponibiliteFormateurs() {
		return disponibiliteFormateurs;
	}

	public void setDisponibiliteFormateurs(List<DisponibiliteFormateur> disponibiliteFormateurs) {
		this.disponibiliteFormateurs = disponibiliteFormateurs;
	}
	
	
	

}
