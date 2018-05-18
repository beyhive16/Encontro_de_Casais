package dao;

import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import entidade.Casal;
import entidade.Encontro;
import entidade.Equipe;
import entidade.HistoricoEquipe;
import entidade.OrientadorEspiritual;
import entidade.TipoEquipe;
import entidade.Usuario;


public class BaseDAO {
	private final static String DATABASE_URL = "jdbc:sqlite:EncontroCasais.db";

	private ConnectionSource connection;

	
	public static void main(String[] args) throws SQLException
	{
		BaseDAO baseDAO = new BaseDAO();
		baseDAO.createTables();
	}
	
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
		TableUtils.dropTable(getConnection(), Usuario.class, true);
		TableUtils.dropTable(getConnection(),TipoEquipe.class,true);
		TableUtils.dropTable(getConnection(), Casal.class,true);
		TableUtils.dropTable(getConnection(), OrientadorEspiritual.class,true);
		TableUtils.dropTable(getConnection(), Equipe.class,true);
		TableUtils.dropTable(getConnection(), HistoricoEquipe.class,true);
		TableUtils.dropTable(getConnection(), Encontro.class,true);
	}
	
	public void createTables() throws SQLException 
	{
		TableUtils.createTableIfNotExists(getConnection(), Usuario.class);
		TableUtils.createTableIfNotExists(getConnection(),TipoEquipe.class);
		TableUtils.createTableIfNotExists(getConnection(), Casal.class);
		TableUtils.createTableIfNotExists(getConnection(), OrientadorEspiritual.class);
		TableUtils.createTableIfNotExists(getConnection(), Equipe.class);
		TableUtils.createTableIfNotExists(getConnection(), HistoricoEquipe.class);
		TableUtils.createTableIfNotExists(getConnection(), Encontro.class);
	}
}
