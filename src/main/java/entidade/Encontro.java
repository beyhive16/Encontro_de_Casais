package entidade;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="ENCONTRO")
public class Encontro {

	@DatabaseField(columnName = "ID", canBeNull = false, generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "TEMA")
	private String tema = "";
	
	@DatabaseField(columnName = "ANO", canBeNull = false)
	private Integer ano;
	
	@DatabaseField(columnName = "ID_ORIENTADOR_ESPIRITUAL", canBeNull=true, foreign=true)
	private OrientadorEspiritual orientadorEspiritual;
	
	@ForeignCollectionField(eager = true, columnName="ID_HISTORICO_EQUIPE")
	private ForeignCollection<HistoricoEquipe> historicoEquipe;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public ForeignCollection<HistoricoEquipe> getHistoricoEquipe() {
		return historicoEquipe;
	}

	public void setHistoricoEquipe(ForeignCollection<HistoricoEquipe> historicoEquipe) {
		this.historicoEquipe = historicoEquipe;
	}

	public OrientadorEspiritual getOrientadorEspiritual() {
		return orientadorEspiritual;
	}

	public void setOrientadorEspiritual(OrientadorEspiritual orientadorEspiritual) {
		this.orientadorEspiritual = orientadorEspiritual;
	}
}
