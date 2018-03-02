package dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;

import entity.Pessoa;
import entity.Usuario;

public class PessoaDAOImp extends BaseDaoImpl<Pessoa, Integer> implements PessoaDAO {

	private BaseDAO baseDAO;

	public PessoaDAOImp(BaseDAO bDAO) throws SQLException {
		super(bDAO.getConnection(), Pessoa.class);
		this.baseDAO = bDAO;
	}

	public Pessoa buscarPorNome(String nome) throws SQLException {
		QueryBuilder<Pessoa, Integer> qb = queryBuilder();
		qb.where().ge("NOME", nome);
		GenericRawResults<String[]> results = this.queryRaw(qb.prepareStatementString());
		String[] values = results.getFirstResult();
		Pessoa pessoa = new Pessoa();
		pessoa.setId(new Integer(values[0]));
		pessoa.setNome(values[1]);
		pessoa.setNomeUsual(values[2]);
		pessoa.setTelefone(values[3]);
		pessoa.setEmail(values[4]);
		return pessoa;
	}

	public Pessoa buscarPorId(Integer id) throws SQLException {
		QueryBuilder<Pessoa, Integer> qb = queryBuilder();
		qb.where().ge("ID", id);
		GenericRawResults<String[]> results = this.queryRaw(qb.prepareStatementString());
		String[] values = results.getFirstResult();
		Pessoa pessoa = new Pessoa();
		pessoa.setId(new Integer(values[0]));
		pessoa.setNome(values[1]);
		pessoa.setNomeUsual(values[2]);
		pessoa.setTelefone(values[3]);
		pessoa.setEmail(values[4]);
		return pessoa;
	}

}
