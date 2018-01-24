package dao;

import com.j256.ormlite.dao.Dao;

import entity.Pessoa;

public interface PessoaDAO extends Dao<Pessoa, Integer>
{
	public Pessoa getForName(String name);
	public Pessoa getForNickName(String nickName);
}
