/**
 * 
 */
package dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;

import entity.Casal;
import entity.Encontro;
import entity.Equipe;
import entity.Pessoa;
import entity.TipoEquipe;

/**
 * @author kenad
 *
 */
public class EquipeDAOImp extends BaseDaoImpl<Equipe, Integer> implements EquipeDAO
{

	BaseDAO baseDAO;
	
	public EquipeDAOImp(BaseDAO baseDAO) throws SQLException {
		super(baseDAO.getConnection(),Equipe.class);
		this.baseDAO = baseDAO;
	}
	
	@Override
	public List<Equipe> selecionarMembros(Casal membro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Equipe selecionarPorTipo(TipoEquipe tipoEquipe,Encontro encontro ,Integer ano) throws SQLException {
		QueryBuilder<Equipe, Integer> qb = queryBuilder();
		qb.where().eq("ID_TIPO_EQUIPE", tipoEquipe.getId());
		qb.where().eq("ID_ENCONTRO", encontro.getId());
		System.out.println("ANOOO:"+ano);
		qb.where().eq("ANO", ano);
		GenericRawResults<String[]> results = this.queryRaw(qb.prepareStatementString());
		String[] values = results.getFirstResult();
		if(values!=null)
		{
			Casal coordenadores = new Casal();
			Equipe equipe = new Equipe();
			equipe.setId(Integer.parseInt(values[0]));
			equipe.setAno(ano);
			equipe.setTipoEquipe(tipoEquipe);
			equipe.setCoordenador(coordenadores);
			equipe.setEncontro(encontro);
			return equipe;			
		}else
		{
			return null;
		}
	}

	@Override
	public Equipe selecionarPorTipoAno(TipoEquipe tipoEquipe, Integer ano) throws SQLException {
		QueryBuilder<Equipe, Integer> qb = queryBuilder();
		qb.where().eq("ID_TIPO_EQUIPE", tipoEquipe.getId());
		qb.where().eq("ANO", ano);
		GenericRawResults<String[]> results = this.queryRaw(qb.prepareStatementString());
		String[] values = results.getFirstResult();
		Equipe equipe = new Equipe();
		equipe.setId(Integer.parseInt(values[0]));
		equipe.setAno(Integer.parseInt(values[1]));
		return equipe;
	}

}
