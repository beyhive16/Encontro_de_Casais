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
import Entidade.TipoEquipe;

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

	@Override
	public List<HistoricoEquipe> getHistoricoPorApelido(String apelidoCasal) throws SQLException {
		//BUSCA DO CASAL
		CasalDAO casal = new CasalDAOImp(baseDAO);
		Casal casalSelecionado = casal.queryForEq("APELIDO_DO_CASAL", apelidoCasal).get(0);
		
		//BUSCA DO HISTORICO
		QueryBuilder<HistoricoEquipe, Integer> qb = queryBuilder();
		qb.where().eq("ID_CASAL", casalSelecionado.getId());
		GenericRawResults<String[]> results = queryRaw(qb.prepareStatementString());
		
		//POPULAÇÃO DE OBJETOS DA LISTA
		List<String[]> resultadosHistorico = results.getResults();
		List<HistoricoEquipe> resultadoEquipe = new ArrayList<>();
		List<HistoricoEquipe> resultadoEquipe2 = new ArrayList<>();
		for (String[] temps : resultadosHistorico) {
			HistoricoEquipe hist = new HistoricoEquipe(temps);
			resultadoEquipe.add(hist);
		}
		//BUSCA DOS COMPONENTES DA LISTA
		for (HistoricoEquipe historicoEquipe : resultadoEquipe) {
			EquipeDAO equipeDAO = new EquipeDAOImp(baseDAO);
			Equipe equipe = equipeDAO.queryForId(historicoEquipe.getEquipe().getId());
			
			TipoEquipeDAO tipoEquipeDAO = new TipoEquipeDAOImp(baseDAO);
			TipoEquipe tipoEquipe = tipoEquipeDAO.queryForId(equipe.getTipoEquipe().getId());
			equipe.setTipoEquipe(tipoEquipe);
			
			EncontroDAO encontroDAO = new EncontroDAOImp(baseDAO);
			Encontro encontro = encontroDAO.queryForId(historicoEquipe.getEncontro().getId());
			
			historicoEquipe.setEquipe(equipe);
			historicoEquipe.setCasal(casalSelecionado);
			historicoEquipe.setEncontro(encontro);
			resultadoEquipe2.add(historicoEquipe);
		}
		
		return resultadoEquipe2;
	}

	@Override
	public List<HistoricoEquipe> getHistoricoPorApelidoAno(String apelidoCasal, String ano) throws SQLException {
		CasalDAO casal = new CasalDAOImp(baseDAO);
		Casal casalSelecionado = casal.queryForEq("APELIDO_DO_CASAL", apelidoCasal).get(0);
		
		EncontroDAO encontroDAO = new EncontroDAOImp(baseDAO);
		Encontro encontro = encontroDAO.queryForEq("ANO", Integer.parseInt(ano)).get(0);
		
		QueryBuilder<HistoricoEquipe, Integer> qb = queryBuilder();
		qb.where().eq("ID_CASAL", casalSelecionado.getId()).and().eq("ID_ENCONTRO", encontro.getId());
		GenericRawResults<String[]> results = queryRaw(qb.prepareStatementString());
		List<String[]> result = results.getResults();
		List<HistoricoEquipe> historicoEquipes = new ArrayList<>();
		
		for (String[] strings : result) {
			HistoricoEquipe historicoEquipe = new HistoricoEquipe(strings);
			
			EquipeDAO equipeDAO = new EquipeDAOImp(baseDAO);
			Equipe equipe = equipeDAO.queryForId(historicoEquipe.getEquipe().getId());
			
			TipoEquipeDAO tipoEquipeDAO = new TipoEquipeDAOImp(baseDAO);
			TipoEquipe tipoEquipe = tipoEquipeDAO.queryForId(equipe.getTipoEquipe().getId());
			equipe.setTipoEquipe(tipoEquipe);
			
			historicoEquipe.setEquipe(equipe);
			
			historicoEquipes.add(historicoEquipe);
		}
		return historicoEquipes;
	}

}
