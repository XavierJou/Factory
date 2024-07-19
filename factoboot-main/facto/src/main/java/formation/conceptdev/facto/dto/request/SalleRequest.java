package formation.conceptdev.facto.dto.request;

import java.util.List;

public class SalleRequest {
    private String nom;
    private Integer capacite;
    private List<Integer> coursIds; // Liste des IDs des cours donnés dans cette salle

    public SalleRequest() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getCapacite() {
        return capacite;
    }

    public void setCapacite(Integer capacite) {
        this.capacite = capacite;
    }

    public List<Integer> getCoursIds() {
        return coursIds;
    }

    public void setCoursIds(List<Integer> coursIds) {
        this.coursIds = coursIds;
    }
}