package Dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;

import Entidade.TipoEquipe;

public interface TipoEquipeDAO extends Dao<TipoEquipe, Integer>
{
	public List<TipoEquipe> getAll() throws SQLException;
	public TipoEquipe getForName(String nome) throws SQLException;
}
