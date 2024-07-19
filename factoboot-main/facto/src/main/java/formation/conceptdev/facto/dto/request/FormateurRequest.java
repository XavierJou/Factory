package formation.conceptdev.facto.dto.request;

import java.util.List;

public class FormateurRequest {
    private Integer idUtilisateur; // Correspond à l'ID de l'utilisateur associé au formateur
    private List<Integer> coursIds; // Liste des IDs des cours donnés par le formateur
    private List<Integer> competencesIds; // Liste des IDs des compétences du formateur
    private List<Integer> disposIds; // Liste des IDs des disponibilités du formateur

    public FormateurRequest() {
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public List<Integer> getCoursIds() {
        return coursIds;
    }

    public void setCoursIds(List<Integer> coursIds) {
        this.coursIds = coursIds;
    }

    public List<Integer> getCompetencesIds() {
        return competencesIds;
    }

    public void setCompetencesIds(List<Integer> competencesIds) {
        this.competencesIds = competencesIds;
    }

    public List<Integer> getDisposIds() {
        return disposIds;
    }

    public void setDisposIds(List<Integer> disposIds) {
        this.disposIds = disposIds;
    }
}