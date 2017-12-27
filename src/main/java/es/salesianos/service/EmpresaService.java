package es.salesianos.service;

import javax.servlet.http.HttpServletRequest;

import es.salesianos.assembler.EmpresaAssembler;
import es.salesianos.assembler.ConsolaAssembler;
import es.salesianos.connection.ConnectionH2;
import es.salesianos.connection.ConnectionManager;
import es.salesianos.model.Empresa;
import es.salesianos.model.Consola;

public class EmpresaService implements Service {

	EmpresaAssembler assembler = new EmpresaAssembler();
	private ConnectionManager manager = new ConnectionH2();

	public void createNewUserFromRequest(HttpServletRequest req) {
		Empresa empresa = assembler.createUserFromRequest(req);

		if (!getManager().search(empresa).isPresent()) {
			getManager().insertEmpresa(empresa);
		} else {
			getManager().update(empresa);
		}
	}

	public ConnectionManager getManager() {
		return manager;
	}

	public void setManager(ConnectionManager manager) {
		this.manager = manager;
	}

}
