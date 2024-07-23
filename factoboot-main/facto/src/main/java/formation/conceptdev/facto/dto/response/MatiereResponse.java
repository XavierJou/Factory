package formation.conceptdev.facto.dto.response;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

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

    @JsonView(CustomJsonViews.MatiereWithCompetenceMatiere.class)
    private List<CompetenceMatiereResponse> competenceMatiereResponses;

    @JsonView(CustomJsonViews.MatiereWithCours.class)
    private List<CoursResponse> cours;
    
    
    
    
    public MatiereResponse() {
    	
    }
    public MatiereResponse(Matiere matiereEntity,boolean besoinCompetenceMatiereResponse,boolean  besoinCoursResponse) {
    	BeanUtils.copyProperties(matiereEntity, this, "competenceMatiereResponses", "cours");
        
    	if (besoinCompetenceMatiereResponse) {
            if (matiereEntity.getCompetenceMatieres() != null) {
                this.competenceMatiereResponses = matiereEntity.getCompetenceMatieres().stream()
                		.map(matiere -> new CompetenceMatiereResponse(matiere, false,false))
                        .collect(Collectors.toList());
            }
            
        }
        
        if (besoinCoursResponse) {
            if (matiereEntity.getCompetenceMatieres() != null) {
                this.cours = matiereEntity.getCours().stream()
                		.map(matiere -> new CoursResponse(matiere, false, false, false, false, false, false))
                        .collect(Collectors.toList());
            }
            
        }
    }
    
    public MatiereResponse(Matiere matiereEntity) {
    	this(matiereEntity,true,true);
    }
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public List<CompetenceMatiereResponse> getCompetenceMatiereResponses() {
		return competenceMatiereResponses;
	}
	public void setCompetenceMatiereResponses(List<CompetenceMatiereResponse> competenceMatiereResponses) {
		this.competenceMatiereResponses = competenceMatiereResponses;
	}
	public List<CoursResponse> getCours() {
		return cours;
	}
	public void setCours(List<CoursResponse> cours) {
		this.cours = cours;
	}

    

}