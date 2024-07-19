package formation.conceptdev.facto.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import formation.conceptdev.facto.entities.CompetenceFormateur;
import formation.conceptdev.facto.entities.Cours;
import formation.conceptdev.facto.entities.DisponibiliteFormateur;
import formation.conceptdev.facto.entities.Formateur;
import formation.conceptdev.facto.entities.Utilisateur;

public class FormateurResponse {
    @JsonView(CustomJsonViews.Common.class)
    private Integer id;
    @JsonView(CustomJsonViews.Common.class)
    private List<CoursResponse> cours;
    @JsonView(CustomJsonViews.Common.class)
    private List<CompetenceFormateurResponse>
    @JsonView(CustomJsonViews.Common.class) competences;
    private List<DisponibiliteFormateurResponse>
    @JsonView(CustomJsonViews.Common.class) dispos;
    private UtilisateurResponse utilisateur;

    public FormateurResponse(Formateur formateur) {
        this.id = formateur.getId();
        this.cours = formateur.getCours().stream().map(c -> new CoursResponse(c)).collect(Collectors.toList());
        this.competences = formateur.getCompetences().stream().map(cf -> new CompetenceFormateurResponse(cf)).collect(Collectors.toList());
        this.dispos = formateur.getDispos().stream().map(df -> new DisponibiliteFormateurResponse(df)).collect(Collectors.toList());
        this.utilisateur = new UtilisateurResponse(formateur.getUtilisateur());
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public List<CoursResponse> getCours() {
        return cours;
    }

    public List<CompetenceFormateurResponse> getCompetences() {
        return competences;
    }

    public List<DisponibiliteFormateurResponse> getDispos() {
        return dispos;
    }

    public UtilisateurResponse getUtilisateur() {
        return utilisateur;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setCours(List<CoursResponse> cours) {
        this.cours = cours;
    }

    public void setCompetences(List<CompetenceFormateurResponse> competences) {
        this.competences = competences;
    }

    public void setDispos(List<DisponibiliteFormateurResponse> dispos) {
        this.dispos = dispos;
    }

    public void setUtilisateur(UtilisateurResponse utilisateur) {
        this.utilisateur = utilisateur;
    }
}