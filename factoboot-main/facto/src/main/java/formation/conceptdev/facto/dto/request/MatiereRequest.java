package formation.conceptdev.facto.dto.request;

import java.util.List;

public class MatiereRequest {
    private String titre;
    private Integer duree;
    private String objectif;
    private String contenu;
    private List<Integer> compMatiereIds; // Liste des IDs des compétences matières associées à cette matière
    private List<Integer> coursIds; // Liste des IDs des cours associés à cette matière

    public MatiereRequest() {
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public List<Integer> getCompMatiereIds() {
        return compMatiereIds;
    }

    public void setCompMatiereIds(List<Integer> compMatiereIds) {
        this.compMatiereIds = compMatiereIds;
    }

    public List<Integer> getCoursIds() {
        return coursIds;
    }

    public void setCoursIds(List<Integer> coursIds) {
        this.coursIds = coursIds;
    }
}