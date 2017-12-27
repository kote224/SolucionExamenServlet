package es.salesianos.service;

import javax.servlet.http.HttpServletRequest;

import es.salesianos.assembler.ConsolaAssembler;
import es.salesianos.connection.ConnectionH2;
import es.salesianos.connection.ConnectionManager;
import es.salesianos.model.Consola;
import es.salesianos.model.Console;

public class ConsoleService implements Service {

	ConsoleService assembler = new ConsoleService();
	private ConnectionManager manager = new ConnectionH2();

	public void createNewUserFromRequest(HttpServletRequest req) {
		Console console = assembler.createUserFromRequest(req);

		if (!getManager().search(console).isPresent()) {
			getManager().insertConsole(console);
		} else {
			getManager().update(console);
		}
	}

	public ConnectionManager getManager() {
		return manager;
	}

	public void setManager(ConnectionManager manager) {
		this.manager = manager;
	}

}
