package dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;

import entity.Casal;
import entity.Equipe;

public interface CasalDAO extends Dao<Casal, Integer>
{
	public List<Casal> selecionarTudo() throws SQLException;
	public String[][] selecionarAll() throws SQLException;
	public List<Casal> selecionarTudoPorEquipe(Equipe equipe) throws SQLException;
	public String[][] selecionarAllPorEquipe(Equipe equipe) throws SQLException;
	public String[][] selecionarPorNomeEquipe(String nomeEquipe) throws SQLException;
	public void removerCasalEquipe(String nomeCasal, String nomeEquipe) throws SQLException;
	public void adicionarAoTipoEquipe(Casal casal,String nomeEquipe);
}
