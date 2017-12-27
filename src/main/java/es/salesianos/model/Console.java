package es.salesianos.model;

import javax.servlet.http.HttpServletRequest;

public class Console {

	String id;
	String name;
	String idCompany;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(String IdEmpresa) {
		this.idCompany = IdEmpresa;
	}

}
