package Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;

import Entidade.TipoEquipe;

public class TipoEquipeDAOImp extends BaseDaoImpl<TipoEquipe, Integer> implements TipoEquipeDAO
{

	private BaseDAO baseDAO;
	
	public TipoEquipeDAOImp(BaseDAO baseDAO) throws SQLException {
		super(baseDAO.getConnection(), TipoEquipe.class);
		this.baseDAO = baseDAO;
	}

	@Override
	public List<TipoEquipe> getAll() throws SQLException {
		return queryForAll();
	}

	@Override
	public TipoEquipe getForName(String nome) throws SQLException {
		QueryBuilder<TipoEquipe, Integer> qb = queryBuilder();
		qb.where().eq("NOME", nome);
		GenericRawResults<String[]> results = this.queryRaw(qb.prepareStatementString());
		String[] values = results.getFirstResult();
		TipoEquipe tipoEquipe = new TipoEquipe();
		tipoEquipe.setId(Integer.parseInt(values[0]));
		tipoEquipe.setNome(values[1]);
		return tipoEquipe;
	}

	@Override
	public List<String> getAllTipoEquipe() throws SQLException {
		List<String> resultados = new ArrayList<>();
		
		QueryBuilder<TipoEquipe, Integer> qb = queryBuilder();
		GenericRawResults<String[]> results = queryRaw(qb.prepareStatementString());
		List<String[]> values = results.getResults();
		for (String[] strings : values) {
			resultados.add(strings[1]);
		}
		return resultados;
	}

}
