package es.salesianos.service;

import javax.servlet.http.HttpServletRequest;

import es.salesianos.assembler.EmpresaAssembler;
import es.salesianos.assembler.VideojuegoAssembler;
import es.salesianos.assembler.ConsolaAssembler;
import es.salesianos.connection.ConnectionH2;
import es.salesianos.connection.ConnectionManager;
import es.salesianos.model.Empresa;
import es.salesianos.model.Videojuegos;
import es.salesianos.model.Consola;

public class VideogameService implements Service {

	VideojuegoAssembler assembler = new VideojuegoAssembler();
	private ConnectionManager manager = new ConnectionH2();

	public void createNewUserFromRequest(HttpServletRequest req) {
		Videojuegos videojuego = assembler.createUserFromRequest(req);

		if (!getManager().search(videojuego).isPresent()) {
			getManager().insertVideojuego(videojuego);
		} else {
			getManager().update(videojuego);
		}
	}

	public ConnectionManager getManager() {
		return manager;
	}

	public void setManager(ConnectionManager manager) {
		this.manager = manager;
	}

}
