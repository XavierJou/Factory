package factory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_utilisateur")
	private int id;

    private String username;
    private String email;
    private String nom;
    private String prenom;
   
    @OneToOne(mappedBy="utilisateur")
    private Formateur formateur;
   
    @OneToOne(mappedBy="utilisateur")
    private Stagiaire stagiaire;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String username, String email, Formateur formateur, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.username= username;
        this.email=email;
        this.formateur = formateur;
    }
    
    public Utilisateur(String nom, String prenom, String username, String email, Stagiaire stagiaire, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.username= username;
        this.email=email;
        this.stagiaire = stagiaire;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
    
    

}
