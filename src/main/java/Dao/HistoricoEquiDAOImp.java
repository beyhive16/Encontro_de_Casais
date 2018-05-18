package Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;

import Entidade.Casal;
import Entidade.Encontro;
import Entidade.Equipe;
import Entidade.HistoricoEquipe;
import Entidade.Usuario;

public class HistoricoEquiDAOImp extends BaseDaoImpl<HistoricoEquipe, Integer> implements HistoricoEquipeDAO {

	private BaseDAO baseDAO;

	public HistoricoEquiDAOImp(BaseDAO baseDAO) throws SQLException {
		super(baseDAO.getConnection(), HistoricoEquipe.class);
		this.baseDAO = baseDAO;
	}

	@Override
	public List<Equipe> getHistoryForCasal(Casal casal) throws SQLException {
		QueryBuilder<HistoricoEquipe, Integer> qb = queryBuilder();
		qb.where().eq("ID_CASAL", casal.getId().toString());
		GenericRawResults<String[]> results = this.queryRaw(qb.prepareStatementString());
		List<String[]> values = results.getResults();
		List<Equipe> equipes = new ArrayList<>();
		EquipeDAO equipeDAO = new EquipeDAOImp(baseDAO);
		for (String[] temp : values) {
			Equipe equipe = equipeDAO.queryForId(Integer.parseInt(temp[1]));
			equipes.add(equipe);
		}
		return equipes;
	}

	@Override
	public List<Casal> getCasalForEquipe(Equipe equipe) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HistoricoEquipe getHistorico(Equipe equipe, Encontro encontro) throws SQLException {
		QueryBuilder<HistoricoEquipe, Integer> qb = queryBuilder();

		qb.where().eq("ID_ENCONTRO", encontro.getId().toString()).and().eq("ID_EQUIPE", equipe.getId().toString());
		GenericRawResults<String[]> results = this.queryRaw(qb.prepareStatementString());
		String[] values = results.getFirstResult();
		if (values == null) {
			return null;
		}
		HistoricoEquipe historicoEquipe = new HistoricoEquipe();
		CasalDAO casalDAO = new CasalDAOImp(baseDAO);
		historicoEquipe.setId(Integer.parseInt(values[0]));
		historicoEquipe.setEquipe(equipe);
		historicoEquipe.setEncontro(encontro);
		historicoEquipe.setCasal(casalDAO.queryForId(Integer.parseInt(values[3])));
		return historicoEquipe;
	}

	@Override
	public List<Casal> getCasalForEquipeEncontro(Equipe equipe, Encontro encontro) throws SQLException {
		QueryBuilder<HistoricoEquipe, Integer> qb = queryBuilder();
		qb.where().eq("ID_ENCONTRO", encontro.getId().toString())
		.and().eq("ID_EQUIPE", equipe.getId().toString());
		GenericRawResults<String[]> results = this.queryRaw(qb.prepareStatementString());
		List<String[]> values = results.getResults();
		List<HistoricoEquipe> historicoEquipes = new ArrayList<>();
		List<Casal> casals = new ArrayList<>();
		CasalDAO casalDAO = new CasalDAOImp(baseDAO);
		for(String[] temp:values)
		{
			HistoricoEquipe tempHE = new HistoricoEquipe();
			tempHE.setEncontro(encontro);
			tempHE.setEquipe(equipe);
			Casal tempC = casalDAO.queryForId(Integer.parseInt(temp[3]));
			tempHE.setCasal(tempC);
			casals.add(tempC);
			historicoEquipes.add(tempHE);
		}
		return casals;
	}

	@Override
	public boolean deleteHistoricoCasal(Equipe equipe, Encontro encontro, Casal casal) throws SQLException {
		QueryBuilder<HistoricoEquipe, Integer> qb = queryBuilder();
		qb.where().eq("ID_ENCONTRO", encontro.getId().toString())
		.and().eq("ID_EQUIPE", equipe.getId().toString())
		.and().eq("ID_CASAL", casal.getId().toString());
		GenericRawResults<String[]> results = this.queryRaw(qb.prepareStatementString());
		String[] values = results.getFirstResult();
		if(values==null)
		{
			return false;
		}
		this.deleteById(Integer.parseInt(values[0]));
		return true;
	}

	@Override
	public Casal getCasalEquipeEncontro(Equipe equipe, Encontro encontro) throws SQLException {
		QueryBuilder<HistoricoEquipe, Integer> qb = queryBuilder();
		qb.where().eq("ID_ENCONTRO", encontro.getId().toString())
		.and().eq("ID_EQUIPE", equipe.getId().toString());
		GenericRawResults<String[]> results = this.queryRaw(qb.prepareStatementString());
		String[] values = results.getFirstResult();
		CasalDAO casalDAO = new CasalDAOImp(baseDAO);
		if(values==null)
		{
			return null;
		}
		return casalDAO.queryForId(Integer.parseInt(values[0]));
	}

}
