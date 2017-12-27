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
import es.salesianos.model.Consola;
import es.salesianos.model.Empresa;

public class ConnectionH2 implements ConnectionManager {

	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test;INIT=RUNSCRIPT FROM 'classpath:scripts/create.sql'";

	public void insertConsola(Consola userFormulario) {
		Connection conn = open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO CONSOLA (id,nombre,idEmpresa)" + "VALUES (?, ?, ?)");
			preparedStatement.setString(1, userFormulario.getId());
			preparedStatement.setString(2, userFormulario.getNombre());
			preparedStatement.setString(3, userFormulario.getIdEmpresa());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
		}

		close(conn);
	}
	
	public void insertEmpresa(Empresa empresa) {
		Connection conn = open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO EMPRESA (id, nombre, fechaempresa)" + "VALUES (?,?,?)");
			preparedStatement.setString(1, empresa.getNombre());
			preparedStatement.setString(2, empresa.getId());
			preparedStatement.setDate(3, new java.sql.Date( empresa.getFechaEmpresa().getTime()));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
		}

		close(conn);
	}
	
	public void insertVideojuego(Videojuegos videojuegos) {
		Connection conn = open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO Videojuego (id, titulo, lanzamiento,edad)" + "VALUES (?,?,?,?)");
			preparedStatement.setString(1, videojuegos.getId());
			preparedStatement.setString(2, videojuegos.getTitulo());
			preparedStatement.setDate(3, (java.sql.Date) videojuegos.getLanzamiento());
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
	public Optional<Consola> search(Consola consola) {
		Consola consolas = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection conn = null;

		try {
			conn = open(jdbcUrl);
			preparedStatement = conn.prepareStatement("SELECT * FROM CONSOLA WHERE id = ?");
			preparedStatement.setString(1, consola.getId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				consolas = new Consola();
				consolas.setId(resultSet.getString("id"));
				consolas.setNombre(resultSet.getString("nombre"));
				consolas.setIdEmpresa(resultSet.getString("idEmpresa"));
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
	
	
	
	public Optional<Empresa> search(Empresa empresa) {
		Empresa empresas = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection conn = null;

		try {
			conn = open(jdbcUrl);
			preparedStatement = conn.prepareStatement("SELECT * FROM EMPESA WHERE id = ?");
			preparedStatement.setString(1, empresa.getId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				empresas = new Empresa();
				empresas.setId(resultSet.getString("id"));
				empresas.setNombre(resultSet.getString("nombre"));
				empresas.setFechaEmpresa(resultSet.getString("fecEmpr"));
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
	
	public Optional<Videojuegos> search(Videojuegos videojuego) {
		Videojuegos videojuegos = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection conn = null;

		try {
			conn = open(jdbcUrl);
			preparedStatement = conn.prepareStatement("SELECT * FROM videojuegos WHERE id = ?");
			preparedStatement.setString(1, videojuego.getId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				videojuegos = new Videojuegos();
				videojuegos.setId(resultSet.getString("id"));
				videojuegos.setTitulo(resultSet.getString("nombre"));
				videojuegos.setLanzamiento(resultSet.getString("fecEmpr"));
				videojuegos.setEdad(resultSet.getString("edad"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			close(conn);
		}

		return Optional.ofNullable(videojuegos);

	}
	

	public void update(Consola consola) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		try {
			conn = open(jdbcUrl);
			preparedStatement = conn.prepareStatement("UPDATE EMPRESA SET " + "nombre = ?, IdEmpresa = ? WHERE Id = ?");

			preparedStatement.setString(1, consola.getNombre());
			preparedStatement.setString(2, consola.getIdEmpresa());
			preparedStatement.setString(3, consola.getId());
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
	
	
	


}
