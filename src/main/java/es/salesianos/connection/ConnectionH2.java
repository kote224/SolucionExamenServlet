package es.salesianos.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.salesianos.model.Videojuegos;
import es.salesianos.model.Company;
import es.salesianos.model.Consola;
import es.salesianos.model.Console;
import es.salesianos.model.Empresa;
import es.salesianos.model.Videogame;

public class ConnectionH2 implements ConnectionManager {

	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test;INIT=RUNSCRIPT FROM 'classpath:scripts/create.sql'";

	public void insertConsole(Console userFormulario) {
		Connection conn = open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO CONSOLA (id,nombre,idEmpresa)" + "VALUES (?, ?, ?)");
			preparedStatement.setString(1, userFormulario.getId());
			preparedStatement.setString(2, userFormulario.getName());
			preparedStatement.setString(3, userFormulario.getIdCompany());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
		}

		close(conn);
	}
	
	public void insertCompany(Company company) {
		Connection conn = open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO EMPRESA (id, nombre, fechaempresa)" + "VALUES (?,?,?)");
			preparedStatement.setString(1, company.getName());
			preparedStatement.setString(2, company.getId());
			preparedStatement.setDate(3, new java.sql.Date( company.getCompanyDate().getTime()));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
		}

		close(conn);
	}
	
	public void insertVideogame(Videogame videogame) {
		Connection conn = open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO Videojuego (id, titulo, lanzamiento,edad)" + "VALUES (?,?,?,?)");
			preparedStatement.setString(1, videogame.getId());
			preparedStatement.setString(2, videogame.getTittle());
			preparedStatement.setDate(3, (java.sql.Date) videogame.getLaunch());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
		}

		close(conn);
	}

	public Connection open(String jdbcUrl) {
		Connection conn = null;
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(jdbcUrl + ";INIT=RUNSCRIPT FROM 'classpath:scripts/create.sql'", "sa",
					"");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return conn;
	}

	public void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	private void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// TODO Auto-generated method stub
	private void close(PreparedStatement prepareStatement) {
		if (prepareStatement != null) {
			try {
				prepareStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	private void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
//----------------------------------------------------------------------
	public Optional<Console> search(Console console) {
		Console consolas = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection conn = null;

		try {
			conn = open(jdbcUrl);
			preparedStatement = conn.prepareStatement("SELECT * FROM CONSOLE WHERE id = ?");
			preparedStatement.setString(1, console.getId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				consolas = new Console();
				consolas.setId(resultSet.getString("id"));
				consolas.setName(resultSet.getString("name"));
				consolas.setIdCompany(resultSet.getString("idCompany"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			close(conn);
		}

		return Optional.ofNullable(consolas);

	}
	
	
	
	public Optional<Company> search(Company company) {
		Company empresas = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection conn = null;

		try {
			conn = open(jdbcUrl);
			preparedStatement = conn.prepareStatement("SELECT * FROM COMPANY WHERE id = ?");
			preparedStatement.setString(1, company.getId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				empresas = new Company();
				empresas.setId(resultSet.getString("id"));
				empresas.setName(resultSet.getString("name"));
				empresas.setCompanyDate(resultSet.getString("dateComp"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			close(conn);
		}

		return Optional.ofNullable(empresas);

	}
	
	public Optional<Videogame> search(Videogame videogames) {
		Videogame videogame = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection conn = null;

		try {
			conn = open(jdbcUrl);
			preparedStatement = conn.prepareStatement("SELECT * FROM videogames WHERE id = ?");
			preparedStatement.setString(1, videogames.getId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				videogame = new Videogame();
				videogame.setId(resultSet.getString("id"));
				videogame.setTittle(resultSet.getString("nombre"));
				videogame.setLanzamiento(resultSet.getString("fecEmpr"));
				videogame.setEdad(resultSet.getString("edad"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			close(conn);
		}

		return Optional.ofNullable(videogame);

	}
	

	public void update(Console console) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		try {
			conn = open(jdbcUrl);
			preparedStatement = conn.prepareStatement("UPDATE Company SET " + "name = ?, IdCompany = ? WHERE Id = ?");

			preparedStatement.setString(1, console.getName());
			preparedStatement.setString(2, console.getIdCompany());
			preparedStatement.setString(3, console.getId());
			preparedStatement.executeUpdate();


		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			close(conn);
		}
	}
	
	public void update(Empresa empresa) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		try {
			conn = open(jdbcUrl);
			preparedStatement = conn.prepareStatement("UPDATE EMPRESA SET " + "nombre = ?"+"fecEmpr = ?"+" WHERE id = ?");
			
			preparedStatement.setString(1, empresa.getId());
			preparedStatement.setString(2, empresa.getNombre());
			preparedStatement.setDate(3, new java.sql.Date(empresa.getFechaEmpresa().getTime()));
			preparedStatement.executeUpdate();

			

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			close(conn);
		}
	}
	
	public void update(Videojuegos videojuego) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		try {
			conn = open(jdbcUrl);
			preparedStatement = conn.prepareStatement("UPDATE Videojuego SET " + "titulo = ?"+"lanzamiento = ?"+"edad = ?"+" WHERE id = ?");
			
			preparedStatement.setString(1, videojuego.getId());
			preparedStatement.setString(2, videojuego.getTitulo());
			preparedStatement.setDate(3, new java.sql.Date(videojuego.getLanzamiento().getTime()));
			preparedStatement.setInt(4, videojuego.getEdad());
			preparedStatement.executeUpdate();

			

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			close(conn);
		}
	}
	

	public List<Consola> listAllConsolas() {
		List<Consola> consola = new ArrayList<Consola>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			conn = open(jdbcUrl);
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM CONSOLAS");

			while (resultSet.next()) {
				Consola consolas = new Consola();
				consolas.setId(resultSet.getString("id"));
				consolas.setNombre(resultSet.getString("nombre"));
				consolas.setIdEmpresa(resultSet.getString("IdEmpresa"));

				consola.add(consolas);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(statement);
			close(conn);
		}

		return consola;
	}
	
	
	

	@Override
	public List<Empresa> listAllEmpresas() {
		List<Empresa> empresa = new ArrayList<Empresa>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			conn = open(jdbcUrl);
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM empresa");

			while (resultSet.next()) {
				Empresa empresas = new Empresa();
				empresas.setId(resultSet.getString("id"));
				empresas.setNombre(resultSet.getString("nombre"));
				empresas.setFechaEmpresa(resultSet.getString("fecEmpr"));

				empresa.add(empresas);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(statement);
			close(conn);
		}

		return empresa;
	}
	
	@Override
	public List<Consola> listAllEmpresasConsola() {
		List<Consola> consola = new ArrayList<Consola>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			conn = open(jdbcUrl);
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM c.consola and e.empresa WHERE c.idEMpresa = e.Id");

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(statement);
			close(conn);
		}

		return consola;
	}
	
	@Override
	public List<Consola> listAllConsolasOrdenadas() {
		List<Consola> consola = new ArrayList<Consola>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			conn = open(jdbcUrl);
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM consola ORDER BY titulo");

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(statement);
			close(conn);
		}

		return consola;
	}
	
	@Override
	public List<Consola> listAllConsolasOrdenadasFec() {
		List<Consola> consola = new ArrayList<Consola>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			conn = open(jdbcUrl);
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT​​ ​ * ​ ​ FROM​​ ​ videojuego​ ​ ORDER​​ ​ BY​​ ​ fechaPublicacion​ ASC");

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(statement);
			close(conn);
		}

		return consola;
	}

	
	@Override
	public List<Videojuegos> listAllVideojuegoMarcas() {
		List<Videojuegos> videojuego = new ArrayList<Videojuegos>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			conn = open(jdbcUrl);
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM c.consola and v.videojuego WHERE c.idEMpresa = e.Id");

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(statement);
			close(conn);
		}

		return videojuego;
	}	
	
	@Override
	public List<Videojuegos> listAllVideojuegos() {
		List<Videojuegos> videojuegos = new ArrayList<Videojuegos>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			conn = open(jdbcUrl);
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM Videojuegos");

			while (resultSet.next()) {
				Videojuegos empresas = new Videojuegos();
				empresas.setId(resultSet.getString("id"));
				empresas.setTitulo(resultSet.getString("nombre"));
				empresas.setLanzamiento(resultSet.getString("lanzamiento"));
				empresas.setEdad(resultSet.getString("edad"));

				videojuegos.add(empresas);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(statement);
			close(conn);
		}

		return videojuegos;
	}

	@Override
	public void insertConsola(es.salesianos.connection.Consola consola) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<es.salesianos.connection.Consola> listAllConsolasOrdenadass() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	


}
