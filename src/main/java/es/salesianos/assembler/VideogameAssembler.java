package es.salesianos.assembler;

import javax.servlet.http.HttpServletRequest;

import es.salesianos.model.Videojuegos;

public class VideogameAssembler {

	public Videojuegos createUserFromRequest(HttpServletRequest request) {

		Videojuegos videojuego = new Videojuegos();
		videojuego.setId(request.getParameter("id"));
		videojuego.setTitulo(request.getParameter("nombre"));
		videojuego.setEdad(request.getParameter("edad"));
		videojuego.setLanzamiento(request.getParameter("lanzamiento"));
		videojuego.setConsola(request.getParameter("consola"));
		return videojuego;
	}

}
