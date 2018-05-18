package dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;

import entidade.Usuario;

public class UsuarioDAOImp extends BaseDaoImpl<Usuario, Integer> implements UsuarioDAO
{

	private BaseDAO baseDAO;
	
	public UsuarioDAOImp(BaseDAO baseDAO) throws SQLException {
		super(baseDAO.getConnection(), Usuario.class);
		this.baseDAO = baseDAO;
	}

	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@Override
	public List<Usuario> getAll() throws SQLException {
		return queryForAll();
	}

	@Override
	public Usuario login(String nome, String senha) throws SQLException {
		QueryBuilder<Usuario, Integer> qb = queryBuilder();
		qb.where().eq("NOME_USUAL", nome).and().eq("SENHA", senha);
		GenericRawResults<String[]> results = this.queryRaw(qb.prepareStatementString());
		String[] values = results.getFirstResult();
		Usuario user = new Usuario(values);
		if(values==null)
		{
			return null;
		}else
		{
			return user;
		}
	}
	
	

}
