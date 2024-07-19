package formation.conceptdev.facto.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.entities.Ordinateur;


public class OrdinateurResponse {
	
	@JsonView(CustomJsonViews.Common.class)
	private Integer id;
	@JsonView(CustomJsonViews.Common.class)
	private String marque;
	@JsonView(CustomJsonViews.Common.class)
	private int ram;
	@JsonView(CustomJsonViews.Common.class)
	private String os;
	
	@JsonView(CustomJsonViews.OrdinateurWithCoursOrdinateurs.class)
	private List<CoursOrdinateursResponse> coursOrdinateurs;

	public OrdinateurResponse() {

	}
	
	

	public OrdinateurResponse(Ordinateur ordinateur, boolean bool) {
		BeanUtils.copyProperties(ordinateur, this, "coursOrdinateurs");
		if (bool) {
			if (ordinateur.getCoursOrdinateurs() != null) {
				this.setCoursOrdinateurs(ordinateur.getCoursOrdinateurs().stream()
						.map(entity -> new CoursOrdinateursResponse(entity, false,false))
						.collect(Collectors.toList()));
			}
		}
	}

	public OrdinateurResponse(Ordinateur ordinateur) {
		this(ordinateur, true);
	}

	
	
	public String getOs() {
		return os;
	}



	public void setOs(String os) {
		this.os = os;
	}



	public List<CoursOrdinateursResponse> getCoursOrdinateurs() {
		return coursOrdinateurs;
	}



	public void setCoursOrdinateurs(List<CoursOrdinateursResponse> coursOrdinateurs) {
		this.coursOrdinateurs = coursOrdinateurs;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

}
