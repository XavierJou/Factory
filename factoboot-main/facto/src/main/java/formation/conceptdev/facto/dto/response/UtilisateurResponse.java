package formation.conceptdev.facto.dto.response;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.entities.Formateur;
import formation.conceptdev.facto.entities.Stagiaire;
import formation.conceptdev.facto.entities.Utilisateur;


public class UtilisateurResponse {
	@JsonView(CustomJsonViews.Common.class)
	private Integer id;
	@JsonView(CustomJsonViews.Common.class)
	private String login;
	@JsonView(CustomJsonViews.Common.class)
	private String nom;
	@JsonView(CustomJsonViews.Common.class)
	private String prenom;
	@JsonView(CustomJsonViews.Common.class)
	private String email;
	@JsonView(CustomJsonViews.Common.class)
	private String role;
	@JsonView(CustomJsonViews.UtilisateurResponseWithFormateur.class)
	private FormateurResponse formateur;
	@JsonView(CustomJsonViews.UtilisateurResponseWithStagiaire.class)
	private StagiaireResponse stagiaire;

	public UtilisateurResponse() {
	}

	public UtilisateurResponse(Utilisateur utilisateurEntity, boolean besoinFormateur, boolean besoinStagiaire) {
		
		
		BeanUtils.copyProperties(utilisateurEntity, this, "role","formateur","stagiaire");
		this.role= utilisateurEntity.getRole().toString();
		
		if (besoinFormateur) {
			if (utilisateurEntity.getFormateur() != null) {
				this.setFormateur(new FormateurResponse(utilisateurEntity.getFormateur(),false,false,false,false));
			}
		}
		
		if (besoinStagiaire) {
			if (utilisateurEntity.getStagiaire() != null) {
				this.setStagiaire(new StagiaireResponse(utilisateurEntity.getStagiaire(),false));
			}
		}
	}
	
	public UtilisateurResponse(Utilisateur utilisateurEntity) {
		this(utilisateurEntity,true,true);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public FormateurResponse getFormateur() {
		return formateur;
	}

	public void setFormateur(FormateurResponse formateur) {
		this.formateur = formateur;
	}

	public StagiaireResponse getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(StagiaireResponse stagiaire) {
		this.stagiaire = stagiaire;
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



}
