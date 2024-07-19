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
    private String nom;
    

    @JsonView(CustomJsonViews.CompetenceWithMatiere.class)
    private List<MatiereResponse> matieres;

    @JsonView(CustomJsonViews.CompetenceWithFormateur.class)
    private List<FormateurResponse> formateurs;

    

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
   
}