package factoryrest.dto.response;

import org.springframework.beans.BeanUtils;

import factory.model.Ordinateur;

public class OrdinateurResponse {
	private Integer id;
	private String marque;
	private int ram;

	public OrdinateurResponse() {

	}

	public OrdinateurResponse(Ordinateur ordinateur) {
		BeanUtils.copyProperties(ordinateur, this);
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
