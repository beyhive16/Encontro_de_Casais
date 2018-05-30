package Dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;

import Entidade.OrientadorEspiritual;

public interface OrientadorEspiritualDAO extends Dao<OrientadorEspiritual, Integer>
{
	public List<OrientadorEspiritual> getAll() throws SQLException;
	public List<String> getAllForName() throws SQLException;
}
