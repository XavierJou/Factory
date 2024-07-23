package formation.conceptdev.facto.dto.response;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.entities.CoursOrdinateurs;





public class CoursOrdinateursResponse {
	
	   @JsonView(CustomJsonViews.Common.class)
	    private Integer id;
	    @JsonView(CustomJsonViews.CoursOrdinateursWithCours.class)
	    private CoursResponse cours;
	    @JsonView(CustomJsonViews.CoursOrdinateursWithOrdinateur.class)
	    private OrdinateurResponse ordinateur;
	    
	
	    
	    public CoursOrdinateursResponse(CoursOrdinateurs coursOrdinateurs) {
			this(coursOrdinateurs, true, true);
		}

		public CoursOrdinateursResponse(CoursOrdinateurs coursOrdinateursEntity, boolean besoinCours, boolean besoinOrdinateur) {

			BeanUtils.copyProperties(coursOrdinateursEntity, this, "cours","ordinateur");
			if (besoinCours) {
				if (coursOrdinateursEntity.getCours() != null) {
					this.setCours(new CoursResponse(coursOrdinateursEntity.getCours(),false,false,false,false,false,false,false));
				}
			}
			
			if (besoinOrdinateur) {
				if (coursOrdinateursEntity.getOrdinateur() != null) {
					this.setOrdinateur(new OrdinateurResponse(coursOrdinateursEntity.getOrdinateur(),false));
				}
			}
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public CoursResponse getCours() {
			return cours;
		}

		public void setCours(CoursResponse cours) {
			this.cours = cours;
		}

		public OrdinateurResponse getOrdinateur() {
			return ordinateur;
		}

		public void setOrdinateur(OrdinateurResponse ordinateur) {
			this.ordinateur = ordinateur;
		}

		
		
		

}
