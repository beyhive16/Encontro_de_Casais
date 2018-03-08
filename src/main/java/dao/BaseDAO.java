package dao;

import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import entity.Casal;
import entity.Encontro;
import entity.Endereco;
import entity.Equipe;
import entity.Pessoa;
import entity.TipoEquipe;
import entity.Usuario;

public class BaseDAO {
	private final static String DATABASE_URL = "jdbc:sqlite:EncontroCasais.db";

	private ConnectionSource connection;

	public BaseDAO() throws SQLException {
		this.connection = new JdbcConnectionSource(DATABASE_URL);

	}

	public ConnectionSource getConnection() {
		return connection;
	}

	public void setConnection(ConnectionSource connection) {
		this.connection = connection;
	}

	public void dropTables() throws SQLException
	{
		TableUtils.dropTable(connection, Pessoa.class, true);
		TableUtils.dropTable(connection, Endereco.class, true);
		TableUtils.dropTable(connection, Casal.class, true);
		TableUtils.dropTable(connection, TipoEquipe.class, true);
		TableUtils.dropTable(connection, Equipe.class, true);
		TableUtils.dropTable(connection, Encontro.class, true);
		TableUtils.dropTable(connection, Usuario.class, true);
	}
	
	public void createTables() throws SQLException {
		try {			
			TableUtils.createTableIfNotExists(connection, Pessoa.class);
			TableUtils.createTableIfNotExists(connection, Endereco.class);
			TableUtils.createTableIfNotExists(connection, Casal.class);
			TableUtils.createTableIfNotExists(connection, TipoEquipe.class);
			TableUtils.createTableIfNotExists(connection, Equipe.class);
			TableUtils.createTableIfNotExists(connection, Encontro.class);
			TableUtils.createTableIfNotExists(connection, Usuario.class);
		} catch (Exception e) {
			this.dropTables();
			this.createTables();
		}
	}
}
