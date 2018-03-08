package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;

import entity.Casal;
import entity.TipoEquipe;

public class TipoEquipeDAOImp extends BaseDaoImpl<TipoEquipe, Integer> implements TipoEquipeDAO{

	private BaseDAO baseDAO;
		
	public TipoEquipeDAOImp(BaseDAO base) throws SQLException {
		super(base.getConnection(), TipoEquipe.class);
		this.baseDAO = base;
	}
	
	public TipoEquipe buscarPorNome(String nmTipo) throws SQLException {
		QueryBuilder<TipoEquipe, Integer> qb = queryBuilder();
		qb.where().eq("NOME", nmTipo);
		GenericRawResults<String[]> results = this.queryRaw(qb.prepareStatementString());
		String[] values = results.getFirstResult();
		TipoEquipe tipoEquipe = new TipoEquipe();
		tipoEquipe.setId(new Integer(values[0]));
		tipoEquipe.setNome(values[1]);
		return tipoEquipe;
	}

	@Override
	public List<TipoEquipe> buscarTodos() throws SQLException {
		List<String[]> values = selecionarTudo();
		List<TipoEquipe> tipoEquipes = new ArrayList<>();
		for(String[] temp: values)
		{
			TipoEquipe tipoEquipe = new TipoEquipe();
			tipoEquipe.setId(Integer.getInteger(temp[0]));
			tipoEquipe.setNome(temp[1]);
			tipoEquipes.add(tipoEquipe);
		}
		return tipoEquipes;
	}

	@Override
	public List<String[]> selecionarTudo() throws SQLException {
		QueryBuilder<TipoEquipe, Integer> qb = queryBuilder();
		qb.orderBy("NOME", true);
		GenericRawResults<String[]> results = this.queryRaw(qb.prepareStatementString());
		List<String[]> values = results.getResults();
		return values;
	}

	@Override
	public String[] buscarTodosTipoEquipe() throws SQLException {
		List<TipoEquipe> todos = buscarTodos();
		String[] nomeTipo = new String[todos.size()];
		for(int i=0;i<todos.size();i++)
		{
			nomeTipo[i] = todos.get(i).getNome();
		}
		return nomeTipo;
	}
}
