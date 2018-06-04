package Dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;

import Entidade.Encontro;
import Entidade.Endereco;

public class EnderecoDAOImp extends BaseDaoImpl<Endereco, Integer> implements EnderecoDAO 
{

	BaseDAO baseDAO;
	
	public EnderecoDAOImp(BaseDAO base) throws SQLException {
		super(base.getConnection(), Endereco.class);
		this.baseDAO = baseDAO;
	}

}
