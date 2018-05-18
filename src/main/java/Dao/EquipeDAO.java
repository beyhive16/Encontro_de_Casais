package Dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;

import Entidade.Equipe;
import Entidade.TipoEquipe;

public interface EquipeDAO extends Dao<Equipe, Integer>{
	public Equipe getEquipeForTipoAno(TipoEquipe tipoEquipe, Integer ano) throws SQLException;
}
