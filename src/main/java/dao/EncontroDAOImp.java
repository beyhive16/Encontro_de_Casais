package dao;

import java.sql.SQLException;
import java.util.List;

import org.omg.CORBA.INTERNAL;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;

import entity.Encontro;
import entity.Equipe;
import entity.Pessoa;

public class EncontroDAOImp extends BaseDaoImpl<Encontro, Integer> implements EncontroDAO
{

	BaseDAO baseDAO;
	
	public EncontroDAOImp(BaseDAO baseDAO) throws SQLException {
		super(baseDAO.getConnection(),Encontro.class);
		this.baseDAO = baseDAO;
	}
	
	@Override
	public Encontro buscarUltimoEncontro() throws SQLException {
		QueryBuilder<Encontro, Integer> qb = queryBuilder();
		qb.orderBy("ID", false);
		GenericRawResults<String[]> results = this.queryRaw(qb.prepareStatementString());
		String[] values = results.getFirstResult();
		System.out.println("ENCONTRO::::");
		for(String temp:values)
		{
			System.out.println(temp);
		}
		Encontro encontro = new Encontro();
		encontro.setId(Integer.parseInt(values[0]));
		encontro.setTemaEncontro(values[1]);
		Pessoa padre = new Pessoa();
		padre.setId(Integer.parseInt(values[2]));
		encontro.setOrientadorEspiritual(padre);
		encontro.setAno(Integer.parseInt(values[4]));
		return encontro;
	}

	@Override
	public String[][] buscarTodos() throws SQLException {
		GenericRawResults<String[]> qb = queryRaw("SELECT ENCONTRO.ANO, PESSOA.NOME_USUAL, CASAL.NM_CASAL "
				+ "FROM ENCONTRO, PESSOA, CASAL "
				+ "WHERE ENCONTRO.ID_ORIENTADOR_ESPIRITUAL= PESSOA.ID "
				+ "AND ENCONTRO.ID_COORDENADOR = CASAL.ID;");
		
		List<String[]> results = qb.getResults();
		String[][] resultados = new String[results.size()][];
		for(int i=0;i<results.size();i++)
		{
			resultados[i] = results.get(i);
			for(int j=0;j<resultados[i].length;j++)
			{
				System.out.print(resultados[i][j]+";");
			}
			System.out.println();
		}
		return resultados;
	}

}
