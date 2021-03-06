package Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;

import Entidade.Casal;

public class CasalDAOImp extends BaseDaoImpl<Casal, Integer> implements CasalDAO
{

	private BaseDAO baseDAO;
	
	public CasalDAOImp(BaseDAO baseDAO) throws SQLException {
		super(baseDAO.getConnection(),Casal.class);
		this.baseDAO = baseDAO;
	}

	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@Override
	public List<Casal> getAll() throws SQLException{
		List<Casal> casal = new ArrayList<>();
		
		QueryBuilder<Casal, Integer> qb = queryBuilder();
		qb.orderBy("APELIDO_DO_CASAL", true);
		GenericRawResults<String[]> results = queryRaw(qb.prepareStatementString());
		List<String[]> temp = results.getResults();
		for (String[] casalTemp : temp) {
			casal.add(new Casal(casalTemp));
		}
		return casal;
	}

	@Override
	public boolean remove(Casal casal) {
		try {
			delete(casal);
			return true;
		} catch (Exception e) {
			return false;
		}		
	}

	@Override
	public boolean remove(Integer casal) {
		try {
			deleteById(casal);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean creating(Casal casal) {
		try {
			create(casal);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean creating(List<Casal> casais) {
		try {
			create(casais);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Casal getCasal(Casal casal) throws SQLException {
		QueryBuilder<Casal, Integer> qb = queryBuilder();
		qb.where().eq("APELIDO_DO_CASAL", casal.getApelidoDoCasal())
		.and().eq("NOME_DELE", casal.getNomeDele())
		.and().eq("NOME_DELA", casal.getNomeDela());
		GenericRawResults<String[]> results = this.queryRaw(qb.prepareStatementString());
		String[] values = results.getFirstResult();
		casal.setId(Integer.parseInt(values[0]));
		casal.setTelefoneDele(values[4]);
		casal.setTelefoneDela(values[5]);
		//casal.setEndereço(values[6]);
		return casal;
	}

	@Override
	public Casal getCasalForApelido(String apelido) throws SQLException {
		QueryBuilder<Casal, Integer> qb = queryBuilder();
		qb.where().eq("APELIDO_DO_CASAL", apelido);
		GenericRawResults<String[]> results = queryRaw(qb.prepareStatementString());
		String[] casalArray = results.getFirstResult();
		return new Casal(casalArray);
	}

	
}
