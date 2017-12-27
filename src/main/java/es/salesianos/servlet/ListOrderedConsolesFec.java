package es.salesianos.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.salesianos.connection.ConnectionH2;
import es.salesianos.connection.ConnectionManager;
import es.salesianos.model.Consola;

public class ListOrderedConsolesFec extends HttpServlet {

	ConnectionManager manager = new ConnectionH2();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Consola> listAllConsolasOrdenadas = manager.listAllConsolasOrdenadass();
		req.getSession().setAttribute("consolas", listAllConsolasOrdenadas);
		redirect(req, resp);
	}

	protected void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listadoConsolasOrdenadas.jsp");
		dispatcher.forward(req, resp);
	}
}
