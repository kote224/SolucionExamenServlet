package es.salesianos.connection;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import es.salesianos.model.Empresa;
import es.salesianos.model.Videojuegos;
import es.salesianos.model.Consola;

@Component("dummy")
public class ConnectionDummy implements ConnectionManager {

	public Connection open(String jdbcUrl) {
		return null;
	}

	public void close(Connection conn) {

	}

	public void insertConsola(Consola consola) {
		System.out.println("insert ficticio");

	}
	
	public void insertEmpresa(Empresa empresa) {
		System.out.println("insert ficticio");

	}
	
	public void insertVideojuego(Videojuegos videojuego) {
		System.out.println("insert ficticio");

	}

	public Optional<Consola> search(Consola user) {
		return Optional.empty();
	}
	
	public Optional<Empresa> search(Empresa empresa) {
		return Optional.empty();
	}
	
	public Optional<Videojuegos> search(Videojuegos videojuego) {
		return Optional.empty();
	}

	public void update(Consola consola) {

	}
	public void update(Videojuegos videojuego) {

	}

	public List<Consola> listAllConsolas() {
		return Collections.EMPTY_LIST;
	}
	
	public List<Videojuegos> listAllVideojuegos() {
		return Collections.EMPTY_LIST;
	}
	

	@Override
	public void update(Empresa empresa) {
		
		
	}

	@Override
	public List<Empresa> listAllEmpresas() {
		return Collections.EMPTY_LIST;
	}


}
