package formation.conceptdev.facto.dto.request;

import java.util.List;

public class CompetenceRequest {
	private String nom;
	private List<Integer> competenceFormateursIds; // Liste des IDs des formateurs associés à cette compétence
	private List<Integer> competenceMatieresIds; // Liste des IDs des matières associées à cette compétence

	public CompetenceRequest() {
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Integer> getCompetenceFormateursIds() {
		return competenceFormateursIds;
	}

	public void setCompetenceFormateursIds(List<Integer> competenceFormateursIds) {
		this.competenceFormateursIds = competenceFormateursIds;
	}

	public List<Integer> getCompetenceMatieresIds() {
		return competenceMatieresIds;
	}

	public void setCompetenceMatieresIds(List<Integer> competenceMatieresIds) {
		this.competenceMatieresIds = competenceMatieresIds;
	}
}