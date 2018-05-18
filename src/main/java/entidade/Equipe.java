package entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "EQUIPE")
public class Equipe {
	
	@DatabaseField(columnName = "ID", canBeNull = false, generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "ANO", canBeNull = false)
	private Integer ano;

	@DatabaseField(columnName = "ID_TIPO_EQUIPE", canBeNull = false, foreign = true)
	private TipoEquipe tipoEquipe;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public TipoEquipe getTipoEquipe() {
		return tipoEquipe;
	}

	public void setTipoEquipe(TipoEquipe tipoEquipe) {
		this.tipoEquipe = tipoEquipe;
	}

}
