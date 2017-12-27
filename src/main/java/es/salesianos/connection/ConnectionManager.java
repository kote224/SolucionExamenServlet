package es.salesianos.connection;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import es.salesianos.model.Empresa;
import es.salesianos.model.Videojuegos;
import es.salesianos.model.Consola;

public interface ConnectionManager {
	
	public Connection open(String jdbcUrl);
	
	public void close(Connection conn);

	void insertConsola(Consola consola);
	
	void insertEmpresa(Empresa empresa);
	
	void insertVideojuego(Videojuegos videojuego);
	
	Optional<Videojuegos> search(Videojuegos videojuego);

	Optional<Consola> search(Consola consola);
	
	Optional<Empresa> search(Empresa empresa);

	void update(Consola consola);
	
	void update(Empresa empresa);
	
	void update(Videojuegos videojuego);
	
	List<Videojuegos> listAllVideojuegos();

	List<Consola> listAllConsolas();
	
	List<Empresa> listAllEmpresas();

	List<Consola> listAllEmpresasConsola();

	List<Videojuegos> listAllVideojuegoMarcas();

	public List<Consola> listAllConsolasOrdenadass();

	

	

}
