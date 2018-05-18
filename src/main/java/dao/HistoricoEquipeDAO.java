package dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;

import entidade.Casal;
import entidade.Encontro;
import entidade.Equipe;
import entidade.HistoricoEquipe;

public interface HistoricoEquipeDAO extends Dao<HistoricoEquipe, Integer>{
	public List<Equipe> getHistoryForCasal(Casal casal) throws SQLException;
	public List<Casal> getCasalForEquipe(Equipe equipe) throws SQLException;
	public List<Casal> getCasalForEquipeEncontro(Equipe equipe, Encontro encontro) throws SQLException;
	public HistoricoEquipe getHistorico(Equipe equipe, Encontro encontro) throws SQLException;
	public boolean deleteHistoricoCasal(Equipe equipe, Encontro encontro,Casal casal) throws SQLException;
	public Casal getCasalEquipeEncontro(Equipe equipe, Encontro encontro) throws SQLException;
}
