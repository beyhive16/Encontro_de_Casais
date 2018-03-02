package dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;

import entity.Casal;

public interface CasalDAO extends Dao<Casal, Integer>
{
	public List<Casal> selecionarTudo() throws SQLException;
}
