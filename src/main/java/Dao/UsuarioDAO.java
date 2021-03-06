package Dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;

import Entidade.Usuario;

public interface UsuarioDAO extends Dao<Usuario, Integer>
{
	public List<Usuario> getAll() throws SQLException;
	public Usuario login(String nome, String senha) throws SQLException;
}
