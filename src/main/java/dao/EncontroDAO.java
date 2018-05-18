package dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;

import entidade.Encontro;

public interface EncontroDAO extends Dao<Encontro, Integer>
{
	public List<Encontro> getAll() throws SQLException;
	public String[][] getAllWithCoordAndOri() throws SQLException;
}
