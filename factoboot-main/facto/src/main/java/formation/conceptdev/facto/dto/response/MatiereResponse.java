package formation.conceptdev.facto.dto.response;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import java.util.stream.Collectors;

import formation.conceptdev.facto.entities.Cours;
import formation.conceptdev.facto.entities.Matiere;
import formation.conceptdev.facto.entities.CompetenceMatiere;

public class MatiereResponse {

    @JsonView(CustomJsonViews.Common.class)
    private Integer id;

    @JsonView(CustomJsonViews.Common.class)
    private String titre;

    @JsonView(CustomJsonViews.Common.class)
    private Integer duree;

    @JsonView(CustomJsonViews.Common.class)
    private String objectif;

    @JsonView(CustomJsonViews.Common.class)
    private String contenu;

    @JsonView(CustomJsonViews.Common.class)
    private List<CompetenceMatiereResponse> compMatiere;

    @JsonView(CustomJsonViews.Common.class)
    private List<CoursResponse> cours;

    public MatiereResponse(Matiere matiere) {
        this.id = matiere.getId();
        this.titre = matiere.getTitre();
        this.duree = matiere.getDuree();
        this.objectif = matiere.getObjectif();
        this.contenu = matiere.getContenu();
        this.compMatiere = matiere.getCompMatiere().stream().map(CompetenceMatiereResponse::new).collect(Collectors.toList());
        this.cours = matiere.getCours().stream().map(CoursResponse::new).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public Integer getDuree() {
        return duree;
    }

    public String getObjectif() {
        return objectif;
    }

    public String getContenu() {
        return contenu;
    }

    public List<CompetenceMatiereResponse> getCompMatiere() {
        return compMatiere;
    }

    public List<CoursResponse> getCours() {
        return cours;
    }
}