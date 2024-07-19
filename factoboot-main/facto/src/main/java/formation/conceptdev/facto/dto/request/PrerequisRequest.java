package formation.conceptdev.facto.dto.request;

public class PrerequisRequest {
    private String nom;
    private Integer idFormation; // ID de la formation associée à ce prérequis

    public PrerequisRequest() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(Integer idFormation) {
        this.idFormation = idFormation;
    }
}