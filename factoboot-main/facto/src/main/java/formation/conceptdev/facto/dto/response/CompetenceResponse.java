package formation.conceptdev.facto.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.entities.Competence;

public class CompetenceResponse {

    @JsonView(CustomJsonViews.Common.class)
    private Integer id;
    @JsonView(CustomJsonViews.Common.class)
    private String titre;
    @JsonView(CustomJsonViews.Common.class)
    private LocalDateTime dateDebut;
    @JsonView(CustomJsonViews.Common.class)
    private boolean videoproj;
    @JsonView(CustomJsonViews.Common.class)
    private boolean ordiFormateur;
    @JsonView(CustomJsonViews.Common.class)
    private boolean ordiStagiaire;

    @JsonView(CustomJsonViews.CompetenceWithMatiere.class)
    private MatiereResponse matiere;

    @JsonView(CustomJsonViews.CompetenceWithFormateur.class)
    private FormateurResponse formateur;

    @JsonView(CustomJsonViews.CompetenceWithFormation.class)
    private FormationResponse formation;

    @JsonView(CustomJsonViews.CompetenceWithCoursOrdinateurs.class)
    private List<CoursOrdinateursResponse> ordinateurs;

    @JsonView(CustomJsonViews.CompetenceWithVideoprojecteur.class)
    private VideoprojecteurResponse videoprojecteur;

    @JsonView(CustomJsonViews.CompetenceWithSalle.class)
    private SalleResponse salle;

    public CompetenceResponse() {
    }

    public CompetenceResponse(Competence entity) {
        BeanUtils.copyProperties(entity, this);
        if (entity.getMatiere() != null) {
            this.matiere = new MatiereResponse(entity.getMatiere());
        }
        if (entity.getFormateur() != null) {
            this.formateur = new FormateurResponse(entity.getFormateur());
        }
        if (entity.getFormation() != null) {
            this.formation = new FormationResponse(entity.getFormation());
        }
        if (entity.getOrdinateurs() != null) {
            this.ordinateurs = entity.getOrdinateurs().stream().map(CoursOrdinateursResponse::new).collect(Collectors.toList());
        }
        if (entity.getVideoprojecteur() != null) {
            this.videoprojecteur = new VideoprojecteurResponse(entity.getVideoprojecteur());
        }
        if (entity.getSalle() != null) {
            this.salle = new SalleResponse(entity.getSalle());
        }
    }
    public boolean isOrdiCours() {
        return ordiCours;
    }
    
    public void setOrdiCours(boolean ordiCours) {
        this.ordiCours = ordiCours;
    }
    
    public FormationResponse getFormation() {
        return formation;
    }
    
    public void setFormation(FormationResponse formation) {
        this.formation = formation;
    }
}