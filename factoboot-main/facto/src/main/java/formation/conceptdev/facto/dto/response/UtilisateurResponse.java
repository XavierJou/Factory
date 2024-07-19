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
	private String password;
	@JsonView(CustomJsonViews.UtilisateurResponseWithFormateur.class)
	private FormateurResponse formateur;
	@JsonView(CustomJsonViews.UtilisateurResponseWithStagiaire.class)
	private StagiaireResponse stagiaire;

	public UtilisateurResponse() {
	}

	public UtilisateurResponse(Utilisateur utilisateurEntity, boolean besoinFormateur, boolean besoinStagiaire) {
		BeanUtils.copyProperties(utilisateurEntity, this);
		
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

}
