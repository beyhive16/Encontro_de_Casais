package dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;

import entity.TipoEquipe;

public interface TipoEquipeDAO extends Dao<TipoEquipe, Integer>{
	public TipoEquipe buscarPorNome(String nmTipo) throws SQLException;
	public List<TipoEquipe> buscarTodos() throws SQLException;
	public String[] buscarTodosTipoEquipe() throws SQLException;
	public List<String[]> selecionarTudo() throws SQLException;
}
