package dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;

import entity.Pessoa;

public interface PessoaDAO extends Dao<Pessoa, Integer>
{
	public Pessoa buscarPorNome(String nome) throws SQLException;
	public Pessoa buscarPorId(Integer id) throws SQLException;
}
