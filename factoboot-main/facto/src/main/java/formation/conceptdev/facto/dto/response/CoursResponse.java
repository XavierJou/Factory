package formation.conceptdev.facto.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.entities.Cours;
import formation.conceptdev.facto.entities.CoursOrdinateurs;
import formation.conceptdev.facto.entities.Salle;
import formation.conceptdev.facto.entities.Videoprojecteur;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class CoursResponse {
	
	@JsonView(CustomJsonViews.Common.class)
	private Integer id;
	@JsonView(CustomJsonViews.Common.class)
	private String titre;
	@JsonView(CustomJsonViews.Common.class)
	private LocalDateTime dateDebut;
	@JsonView(CustomJsonViews.Common.class)
	private boolean besoinVideoprojecteur;
	@JsonView(CustomJsonViews.Common.class)
	private boolean besoinOrdiFormateur;
	@JsonView(CustomJsonViews.Common.class)
	private boolean besoinOrdiStagiaire;
	@JsonView(CustomJsonViews.Common.class)
	private boolean besoinSalle;

	
	@JsonView(CustomJsonViews.CoursResponseWithMatiere.class)
	private MatiereResponse matiere;
	
	@JsonView(CustomJsonViews.CoursResponseWithFormateur.class)
	private FormateurResponse formateur;
	
	@JsonView(CustomJsonViews.CoursResponseWithFormation.class)
	private FormationResponse formation;


	@JsonView(CustomJsonViews.CoursResponseWithCoursOrdinateurs.class)
	private List<CoursOrdinateursResponse> coursOrdinateurs;
	
	@JsonView(CustomJsonViews.CoursResponseWithVideoprojecteur.class)
	private VideoprojecteurResponse videoprojecteur;
	
	@JsonView(CustomJsonViews.CoursResponseWithSalle.class)
	private SalleResponse salle;
	

	public CoursResponse() {
	}
	
	public CoursResponse(Cours entity) {
		
		this(entity,true,true,true,true,true,true);
	}

	public CoursResponse(Cours coursEntity, boolean besoinMatiere, boolean besoinFormateur
			, boolean besoinFormation, boolean besoinOrdinateurs
			, boolean besoinVideoprojecteur, boolean besoinSalle) {
		
		BeanUtils.copyProperties(coursEntity, this, "formation", "formateur", "matiere","salle","coursOrdinateurs","videoprojecteur");
		
		if (besoinMatiere) {
			if (coursEntity.getMatiere() != null) {
				this.setMatiere(new MatiereResponse(coursEntity.getMatiere(), false, false));
			}
		}
		
		if (besoinFormateur) {
			if (coursEntity.getFormateur() != null) {
				this.setFormateur(new FormateurResponse(coursEntity.getFormateur(), false, false, false, false));
			}
		}
		
		if (besoinFormation) {
			if (coursEntity.getFormation() != null) {
				this.setFormation(new FormationResponse(coursEntity.getFormation(), false));
			}
		}
		
		
		if (besoinOrdinateurs) {
			if (coursEntity.getCoursOrdinateurs() != null) {
                this.coursOrdinateurs = coursEntity.getCoursOrdinateurs().stream()
                		.map(coursOrdinateurs -> new CoursOrdinateursResponse(coursOrdinateurs, false,false))
                        .collect(Collectors.toList());
            }
		}
		
		
		if (besoinVideoprojecteur) {
			if (coursEntity.getVideoprojecteur() != null) {
				this.setVideoprojecteur(new VideoprojecteurResponse(coursEntity.getVideoprojecteur(), false));
			}
		}
		
		if (besoinSalle) {
			if (coursEntity.getSalle() != null) {
				this.setSalle(new SalleResponse(coursEntity.getSalle(), false));
			}
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}

	public boolean isBesoinVideoprojecteur() {
		return besoinVideoprojecteur;
	}

	public void setBesoinVideoprojecteur(boolean besoinVideoprojecteur) {
		this.besoinVideoprojecteur = besoinVideoprojecteur;
	}

	public boolean isBesoinOrdiFormateur() {
		return besoinOrdiFormateur;
	}

	public void setBesoinOrdiFormateur(boolean besoinOrdiFormateur) {
		this.besoinOrdiFormateur = besoinOrdiFormateur;
	}

	public boolean isBesoinOrdiStagiaire() {
		return besoinOrdiStagiaire;
	}

	public void setBesoinOrdiStagiaire(boolean besoinOrdiStagiaire) {
		this.besoinOrdiStagiaire = besoinOrdiStagiaire;
	}

	public boolean isBesoinSalle() {
		return besoinSalle;
	}

	public void setBesoinSalle(boolean besoinSalle) {
		this.besoinSalle = besoinSalle;
	}

	public MatiereResponse getMatiere() {
		return matiere;
	}

	public void setMatiere(MatiereResponse matiere) {
		this.matiere = matiere;
	}

	public FormateurResponse getFormateur() {
		return formateur;
	}

	public void setFormateur(FormateurResponse formateur) {
		this.formateur = formateur;
	}

	public FormationResponse getFormation() {
		return formation;
	}

	public void setFormation(FormationResponse formation) {
		this.formation = formation;
	}

	public List<CoursOrdinateursResponse> getCoursOrdinateurs() {
		return coursOrdinateurs;
	}

	public void setCoursOrdinateurs(List<CoursOrdinateursResponse> coursOrdinateurs) {
		this.coursOrdinateurs = coursOrdinateurs;
	}

	public VideoprojecteurResponse getVideoprojecteur() {
		return videoprojecteur;
	}

	public void setVideoprojecteur(VideoprojecteurResponse videoprojecteur) {
		this.videoprojecteur = videoprojecteur;
	}

	public SalleResponse getSalle() {
		return salle;
	}

	public void setSalle(SalleResponse salle) {
		this.salle = salle;
	}
	
	

	
	

}
