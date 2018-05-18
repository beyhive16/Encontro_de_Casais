package Dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;

import Entidade.Casal;
import Entidade.Encontro;
import Entidade.Equipe;
import Entidade.HistoricoEquipe;
import Entidade.OrientadorEspiritual;
import Entidade.TipoEquipe;
import Entidade.Usuario;

public class EncontroDAOImp extends BaseDaoImpl<Encontro, Integer> implements EncontroDAO {

	private BaseDAO baseDAO;

	public EncontroDAOImp(BaseDAO baseDAO) throws SQLException {
		super(baseDAO.getConnection(), Encontro.class);
		this.baseDAO = baseDAO;
	}

	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@Override
	public List<Encontro> getAll() throws SQLException {
		return queryForAll();
	}

	@Override
	public String[][] getAllWithCoordAndOri() throws SQLException {
		List<Encontro> encontros = getAll();
		String[][] temp = new String[encontros.size()][3];
		HistoricoEquipeDAO historicoEquipeDAO = new HistoricoEquiDAOImp(baseDAO);
		CasalDAO casalDAO = new CasalDAOImp(baseDAO);
		EquipeDAO equipeDAO = new EquipeDAOImp(baseDAO);
		TipoEquipeDAO tipoEquipeDAO = new TipoEquipeDAOImp(baseDAO);
		TipoEquipe coordeanacao = tipoEquipeDAO.queryForId(1);
		OrientadorEspiritualDAO orDAO = new OrientadorEspiritualDAOImp(baseDAO);
		for (int i = 0; i < encontros.size(); i++) {
			temp[i][0] = encontros.get(i).getAno().toString();
			if(encontros.get(i).getOrientadorEspiritual()==null)
			{
				temp[i][1]=null;
			}else
			{
				OrientadorEspiritual or = orDAO.queryForId(encontros.get(i).getOrientadorEspiritual().getId());
				temp[i][1] = or.getNome();
			}
			Equipe equipe = equipeDAO.getEquipeForTipoAno(coordeanacao, encontros.get(i).getAno());
			Casal casal = historicoEquipeDAO.getCasalEquipeEncontro(equipe, encontros.get(i));
			if (casal == null) {
				temp[i][2] = null;
			} else {
				temp[i][2] = casal.getApelidoDoCasal();
			}
		}
		return temp;
	}

}
