package dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;

import entity.Usuario;

public class UsuarioDAOImp extends BaseDaoImpl<Usuario, Integer> implements UsuarioDAO{

	private BaseDAO baseDAO;

	public UsuarioDAOImp(BaseDAO bdao) throws SQLException {
		super(bdao.getConnection(), Usuario.class);
		this.baseDAO = bdao;
	}
	

	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public Usuario buscarPorNomeUsual(String nomeUsual) throws SQLException {
		QueryBuilder<Usuario, Integer> qb = queryBuilder();
		qb.where().eq("NOME_USUAL", nomeUsual);
		GenericRawResults<String[]> results = this.queryRaw(qb.prepareStatementString());
		String[] values = results.getFirstResult();
		Usuario user = new Usuario();
		user.setId(new Integer(values[0]));
		user.setNome(values[1]);
		user.setNomeUsual(values[2]);
		user.setSenha(values[3]);
		user.setEmail(values[4]);
		return user;
	}

}
