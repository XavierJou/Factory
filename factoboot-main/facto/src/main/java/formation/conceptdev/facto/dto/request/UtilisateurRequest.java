package formation.conceptdev.facto.dto.request;

import jakarta.validation.constraints.NotNull;

public class UtilisateurRequest {
	
	 Integer id;
	@NotNull
	private String login;
	@NotNull
	private String nom;
	@NotNull
	private String prenom;
	@NotNull
	private String email;
	@NotNull
	private String role;


	public UtilisateurRequest() {
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}




	

	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}

	

}
