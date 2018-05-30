package Dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;

import Entidade.Equipe;
import Entidade.TipoEquipe;

public class EquipeDAOImp extends BaseDaoImpl<Equipe, Integer> implements EquipeDAO {

	BaseDAO baseDAO;

	public EquipeDAOImp(BaseDAO baseDAO) throws SQLException {
		super(baseDAO.getConnection(), Equipe.class);
		this.baseDAO = baseDAO;
	}

	@Override
	public Equipe queryForId(Integer id) throws SQLException {
		Equipe equipe = super.queryForId(id);
		TipoEquipeDAO tipoEquipeDAO = new TipoEquipeDAOImp(baseDAO);
		TipoEquipe tipoEquipe = tipoEquipeDAO.queryForId(equipe.getTipoEquipe().getId());
		equipe.setTipoEquipe(tipoEquipe);
		return equipe;
	}

	@Override
	public Equipe getEquipeForTipoAno(TipoEquipe tipoEquipe, Integer ano) throws SQLException {
		QueryBuilder<Equipe, Integer> qb = queryBuilder();
		qb.where().eq("ANO", ano).and().eq("ID_TIPO_EQUIPE", tipoEquipe.getId());
		GenericRawResults<String[]> results = this.queryRaw(qb.prepareStatementString());
		String[] values = results.getFirstResult();
		if (values != null) {
			Equipe equipe = new Equipe();
			equipe.setId(Integer.parseInt(values[0]));
			equipe.setAno(Integer.parseInt(values[1]));
			equipe.setTipoEquipe(tipoEquipe);
			return equipe;
		} else {
			return null;
		}
	}
}
