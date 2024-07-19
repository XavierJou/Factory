package ordinateur.conceptdev.facto.dto.response;

import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import formation.conceptdev.facto.entities.Ordinateur;


public class OrdinateurResponse {
	private Integer id;
	private String marque;
	private int ram;

	public OrdinateurResponse() {

	}

	public OrdinateurResponse(Ordinateur ordinateur, boolean bool) {
		BeanUtils.copyProperties(ordinateur, this, "cours");
		if (bool) {
			if (ordinateur.getCours() != null) {
				this.setCours(ordinateur.getCours().stream()
						.map(entity -> new CoursResponse(entity, false))
						.collect(Collectors.toList()));
			}
		}
	}

	public OrdinateurResponse(Ordinateur ordinateur) {
		this(ordinateur, true);
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
