package dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;

import entidade.Casal;
import entidade.OrientadorEspiritual;

public interface OrientadorEspiritualDAO extends Dao<OrientadorEspiritual, Integer>
{
	public List<OrientadorEspiritual> getAll() throws SQLException;
}
