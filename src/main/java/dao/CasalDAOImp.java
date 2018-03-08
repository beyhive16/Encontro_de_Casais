package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;

import entity.Casal;
import entity.Encontro;
import entity.Equipe;
import entity.Pessoa;
import entity.TipoEquipe;
import entity.Usuario;

public class CasalDAOImp extends BaseDaoImpl<Casal, Integer> implements CasalDAO
{
	private BaseDAO baseDAO;
	
	public CasalDAOImp(BaseDAO bDAO) throws SQLException {
		super(bDAO.getConnection(), Casal.class);
		this.baseDAO = bDAO;
	}
	
	public String[][] selecionarAll() throws SQLException
	{
		List<Casal> todos = selecionarTudo();
		String[][] casais = new String[todos.size()][4];
		for(int i=0;i<todos.size();i++)
		{
			casais[i][0] = todos.get(i).getNomeCasal();
			casais[i][1] = todos.get(i).getEsposa().getNome();
			casais[i][2] = todos.get(i).getMarido().getNome();
			casais[i][3] = null;
		}
		return casais;
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

	
	@Override
	public List<Casal> selecionarTudoPorEquipe(Equipe equipe) throws SQLException {
		QueryBuilder<Casal, Integer> qb = queryBuilder();
		qb.where().eq("ID_EQUIPE", equipe.getId());
		//System.out.println("ID EQUIPE:"+equipe.getId());
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
			novo.setEquipe(new Equipe(casal[6]));
			
			casais.add(novo);
		}
		return casais;
	}
	
	private Casal buscarPorIdEquipe(int idEquipe) throws SQLException
	{
		QueryBuilder<Casal, Integer> qb = queryBuilder();
		qb.where().eq("ID_EQUIPE", idEquipe);
		GenericRawResults<String[]> results = this.queryRaw(qb.prepareStatementString());
		String[] values = results.getFirstResult();
		if(values==null) return null;
		Casal casal = new Casal();
		casal.setId(Integer.parseInt(values[0]));
		return casal;
	}
	
	@Override
	public String[][] selecionarAllPorEquipe(Equipe equipe) throws SQLException {
		List<Casal> todos = selecionarTudoPorEquipe(equipe);
		String[][] casais = new String[todos.size()][4];
		for(int i=0;i<todos.size();i++)
		{
			casais[i][0] = todos.get(i).getNomeCasal();
			casais[i][1] = todos.get(i).getEsposa().getNome();
			casais[i][2] = todos.get(i).getMarido().getNome();
			casais[i][3] = null;
		}
		return casais;
	}

	@Override
	public String[][] selecionarPorNomeEquipe(String nomeEquipe) throws SQLException {
		GenericRawResults<String[]> qb = queryRaw("SELECT CA.NM_CASAL, P1.NOME,P2.NOME, EQ.ID, TE.NOME "
				+ "FROM CASAL CA "
				+ "JOIN PESSOA P1 ON P1.ID = CA.ID_ESPOSA "
				+ "JOIN PESSOA P2 ON P2.ID = CA.ID_MARIDO  "
				+ "JOIN EQUIPE EQ ON EQ.ID = CA.ID_EQUIPE "
				+ "JOIN TIPO_EQUIPE TE ON TE.ID = EQ.ID_TIPO_EQUIPE "
				+ "WHERE TE.NOME = ?;",nomeEquipe);
		
		List<String[]> results = qb.getResults();
		String[][] resultados = new String[results.size()][];
		for(int i=0;i<results.size();i++)
		{
			String[] temp = new String[4];
			temp[0] = results.get(i)[0];
			temp[1] = results.get(i)[1];
			temp[2] = results.get(i)[2];
			temp[3] = null;
			resultados[i] = temp;
		}
		return resultados;
	}

	@Override
	public void removerCasalEquipe(String nomeCasal, String nomeEquipe) throws SQLException {
		TipoEquipeDAO tipoEquipeDAO = new TipoEquipeDAOImp(baseDAO);
		EquipeDAO equipeDAO = new EquipeDAOImp(baseDAO);
		EncontroDAO encontroDAO = new EncontroDAOImp(baseDAO);
		
		Encontro encontro = encontroDAO.buscarUltimoEncontro();
		TipoEquipe tipoEquipe = tipoEquipeDAO.buscarPorNome(nomeEquipe);
		Equipe equipe = equipeDAO.selecionarPorTipo(tipoEquipe, encontro, encontro.getAno());
		
		Casal casal = this.buscarPorIdEquipe(equipe.getId());
		System.out.println("CASSSSSSSSSSSSLLLLL"+casal.getId());
		queryRaw("UPDATE CASAL SET ID_EQUIPE = NULL WHERE CASAL.ID = ?;",casal.getId().toString());
	}

	@Override
	public void adicionarAoTipoEquipe(Casal casal,String nomeEquipe) {
		/*TipoEquipeDAO tipoEquipeDAO = new TipoEquipeDAOImp(baseDAO);
		EncontroDAO encontroDAO = new EncontroDAOImp(baseDAO);
		EquipeDAO equipeDAO = new EquipeDAOImp(baseDAO);
		
		Encontro encontro = encontroDAO.buscarUltimoEncontro();
		
		TipoEquipe tipoEquipe = tipoEquipeDAO.buscarPorNome(nomeEquipe);
		Equipe equipe = equipeDAO.selecionarPorTipo(tipoEquipe, encontro, encontro.getAno());
		Object[] paramentros = {equipe.getId(),casal.getId()};
		//GenericRawResults<String[]> grr = queryRaw("UPDATE CASAL SET ID_EQUIPE = ? WHERE CASAL.ID = ?",paramentros);
		*/
	}
}
