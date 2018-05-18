package Entidade;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "HISTORICO_EQUIPE")
public class HistoricoEquipe {

	@DatabaseField(columnName = "ID", canBeNull = false, generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "ID_EQUIPE", canBeNull = false, foreign = true, foreignAutoRefresh = true)
	private Equipe equipe;

	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "ID_ENCONTRO")
	private Encontro encontro;
	
	@DatabaseField(columnName = "ID_CASAL", canBeNull = false,foreign = true)
	private Casal casal;
	
	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Encontro getEncontro() {
		return encontro;
	}

	public void setEncontro(Encontro encontro) {
		this.encontro = encontro;
	}

	public Casal getCasal() {
		return casal;
	}

	public void setCasal(Casal casal) {
		this.casal = casal;
	}
	
	
}
