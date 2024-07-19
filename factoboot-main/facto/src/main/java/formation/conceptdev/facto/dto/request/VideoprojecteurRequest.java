package formation.conceptdev.facto.dto.request;

import java.time.LocalDate;
import java.util.List;

public class VideoprojecteurRequest {
    private String nom;
    private String marque;
    private LocalDate dateAchat;
    private List<Integer> coursIds; // Liste des IDs des cours utilisant ce vidéoprojecteur

    public VideoprojecteurRequest() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public LocalDate getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDate dateAchat) {
        this.dateAchat = dateAchat;
    }

    public List<Integer> getCoursIds() {
        return coursIds;
    }

    public void setCoursIds(List<Integer> coursIds) {
        this.coursIds = coursIds;
    }
}