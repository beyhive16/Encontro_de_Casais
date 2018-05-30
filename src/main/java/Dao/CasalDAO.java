package Dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;

import Entidade.Casal;

public interface CasalDAO extends Dao<Casal, Integer>
{
	List<Casal> getAll() throws SQLException;
	boolean remove(Casal casal);
	boolean remove(Integer casal);
	
	boolean creating(Casal casal);
	boolean creating(List<Casal> casais);
	
	Casal getCasal(Casal casal) throws SQLException;
	Casal getCasalForApelido(String apelido) throws SQLException;
}
