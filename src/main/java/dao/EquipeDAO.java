package dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;

import entidade.Equipe;
import entidade.TipoEquipe;

public interface EquipeDAO extends Dao<Equipe, Integer>{
	public Equipe getEquipeForTipoAno(TipoEquipe tipoEquipe, Integer ano) throws SQLException;
}
