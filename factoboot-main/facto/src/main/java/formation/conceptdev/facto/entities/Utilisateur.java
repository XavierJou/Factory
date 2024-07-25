package formation.conceptdev.facto.entities;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_utilisateur")
	private Integer id;
	@Column(name = "login_utilisateur", nullable = false, unique = true, length = 150)
	private String login;
	@Column(name = "password_utilisateur", nullable = false, length = 255)
	private String password;
	@Column(name = "nom_utilisateur")
	private String nom;
	@Column(name = "prenom_utilisateur")
	private String prenom;
	@Column(name = "email_utilisateur")
	private String email;
	@Enumerated(EnumType.STRING)
	@Column(name = "role_utilisateur", nullable = false, length = 50)
	private Role role;
	@OneToOne
    @JoinColumn(name="id_formateur")
    private Formateur formateur;
   
    @OneToOne
    @JoinColumn(name="id_stagiaire")
    private Stagiaire stagiaire;

	public Utilisateur() {
	}


	public Utilisateur(String login, String password, String nom, String prenom, String email, Role role) {
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.role = role;
	}
	
	 public Utilisateur(String login, String password, String nom, String prenom, String email, Formateur formateur, Role role) {
		 this.login = login;
			this.password = password;
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
			this.role = role;
	        this.formateur = formateur;
	        
	    }
	    
	    public Utilisateur(String login, String password, String nom, String prenom, String email, Stagiaire stagiaire, Role role) {
	    	this.login = login;
			this.password = password;
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
			this.role = role;
	        this.stagiaire = stagiaire;
	       
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


	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(role.toString()));
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other = (Utilisateur) obj;
		return Objects.equals(id, other.id);
	}


	public Formateur getFormateur() {
		return formateur;
	}


	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}


	public Stagiaire getStagiaire() {
		return stagiaire;
	}


	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}


	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", role=" + role + "]";
	}
	
	

}
