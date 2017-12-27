package es.salesianos.assembler;

import javax.servlet.http.HttpServletRequest;

import es.salesianos.model.Consola;

public class ConsoleAssembler {

	public Consola createUserFromRequest(HttpServletRequest request) {

		Consola consola = new Consola();
		consola.setId(request.getParameter("id"));
		consola.setNombre(request.getParameter("nombre"));
		consola.setIdEmpresa(request.getParameter("idEMpresa"));
		return consola;
	}

}
