package dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;

import entity.Casal;
import entity.Encontro;
import entity.Equipe;
import entity.TipoEquipe;

public interface EquipeDAO extends Dao<Equipe, Integer>
{
	public List<Equipe> selecionarMembros(Casal membro);
	public Equipe selecionarPorTipo(TipoEquipe tipoEquipe,Encontro encontro ,Integer ano) throws SQLException;
	public Equipe selecionarPorTipoAno(TipoEquipe tipoEquipe, Integer ano) throws SQLException;
}
