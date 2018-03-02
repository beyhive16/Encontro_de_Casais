package dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;

import entity.Usuario;

public interface UsuarioDAO extends Dao<Usuario, Integer>
{
	public Usuario buscarPorNomeUsual(String nomeUsual) throws SQLException;
}
