package Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;

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

	@Override
	public List<String> getAllForName() throws SQLException {
		List<String> tedis = new ArrayList<>();
		
		QueryBuilder<OrientadorEspiritual, Integer> qb = queryBuilder();
		GenericRawResults<String[]> results = this.queryRaw(qb.prepareStatementString());
		List<String[]> resul = results.getResults();
		for (String[] strings : resul) {
			tedis.add(strings[2]);
		}
		return tedis;
	}

}
