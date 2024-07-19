package formation.conceptdev.facto.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.entities.Prerequis;

public class PrerequisResponse {

    @JsonView(CustomJsonViews.Common.class)
    private Integer id;

    @JsonView(CustomJsonViews.Common.class)
    private String titre;

    @JsonView(CustomJsonViews.Common.class)
    private String description;

    // Constructeur par défaut
    public PrerequisResponse() {
    }

    // Constructeur pour initialiser à partir d'un objet Prerequis
    public PrerequisResponse(Prerequis prerequis) {
        this.id = prerequis.getId();
        this.titre = prerequis.getTitre();
        this.description = prerequis.getDescription();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}