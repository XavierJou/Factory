package formation.conceptdev.facto.dto.request;

public class CoursOrdinateursRequest {

	private Integer coursId; 
	private Integer ordinateuriId;
	
	public CoursOrdinateursRequest()
	{
		
	}

	public Integer getCoursId() {
		return coursId;
	}

	public void setCoursId(Integer coursId) {
		this.coursId = coursId;
	}

	public Integer getOrdinateuriId() {
		return ordinateuriId;
	}

	public void setOrdinateuriId(Integer ordinateuriId) {
		this.ordinateuriId = ordinateuriId;
	}
	
	
}
