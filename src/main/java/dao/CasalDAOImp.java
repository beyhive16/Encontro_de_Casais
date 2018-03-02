package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;

import entity.Casal;
import entity.Equipe;
import entity.Pessoa;
import entity.Usuario;

public class CasalDAOImp extends BaseDaoImpl<Casal, Integer> implements CasalDAO
{
	private BaseDAO baseDAO;
	
	public CasalDAOImp(BaseDAO bDAO) throws SQLException {
		super(bDAO.getConnection(), Casal.class);
		this.baseDAO = bDAO;
	}

	public List<Casal> selecionarTudo() throws SQLException {
		QueryBuilder<Casal, Integer> qb = queryBuilder();
		qb.orderBy("NM_CASAL", true);
		GenericRawResults<String[]> results = this.queryRaw(qb.prepareStatementString());
		List<String[]> values = results.getResults();
		List<Casal> casais = new ArrayList<Casal>();
		PessoaDAO pessoaDAO = new PessoaDAOImp(baseDAO);
		for(String[] casal:values)
		{
			Casal novo = new Casal();
			novo.setId(new Integer(casal[0]));
			novo.setMarido(pessoaDAO.buscarPorId(new Integer(casal[1])));
			novo.setEsposa(pessoaDAO.buscarPorId(new Integer(casal[2])));
			novo.setNomeCasal(casal[3]);
			novo.setEmail(casal[4]);
			novo.setEndereco(casal[5]);
			//novo.setEquipe(new Equipe(casal[6]));
			
			casais.add(novo);
		}
		return casais;
	}

}
