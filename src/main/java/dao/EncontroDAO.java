package dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;

import entity.Encontro;

public interface EncontroDAO extends Dao<Encontro, Integer>{
	public Encontro buscarUltimoEncontro() throws SQLException;
	public String[][] buscarTodos() throws SQLException;
	
}
