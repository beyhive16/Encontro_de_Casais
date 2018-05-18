package Dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;

import Entidade.OrientadorEspiritual;

public class OrientadorEspiritualDAOImp extends BaseDaoImpl<OrientadorEspiritual, Integer> implements OrientadorEspiritualDAO
{

	private BaseDAO baseDAO;
	
	public OrientadorEspiritualDAOImp(BaseDAO baseDAO)
			throws SQLException {
		super(baseDAO.getConnection(), OrientadorEspiritual.class);
		this.baseDAO = baseDAO;
	}

	@Override
	public List<OrientadorEspiritual> getAll() throws SQLException {
		return queryForAll();
	}

}
