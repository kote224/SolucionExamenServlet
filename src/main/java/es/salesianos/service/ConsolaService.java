package es.salesianos.service;

import javax.servlet.http.HttpServletRequest;

import es.salesianos.assembler.ConsolaAssembler;
import es.salesianos.connection.ConnectionH2;
import es.salesianos.connection.ConnectionManager;
import es.salesianos.model.Consola;

public class ConsolaService implements Service {

	ConsolaAssembler assembler = new ConsolaAssembler();
	private ConnectionManager manager = new ConnectionH2();

	public void createNewUserFromRequest(HttpServletRequest req) {
		Consola consola = assembler.createUserFromRequest(req);

		if (!getManager().search(consola).isPresent()) {
			getManager().insertConsola(consola);
		} else {
			getManager().update(consola);
		}
	}

	public ConnectionManager getManager() {
		return manager;
	}

	public void setManager(ConnectionManager manager) {
		this.manager = manager;
	}

}
