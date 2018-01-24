package entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "EQUIPE")
public class Equipe {
	@DatabaseField(id = true, columnName = "ID", canBeNull = false, generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "ANO", canBeNull = false)
	private Integer ano;

	@DatabaseField(columnName = "ID_TIPO_EQUIPE", canBeNull = false, foreign = true)
	private TipoEquipe tipoEquipe;

	@ForeignCollectionField(columnName = "ID_COORDENADORES", eager = true)
	private ForeignCollection<Casal> coordenadores;

	@ForeignCollectionField(columnName = "ID_MEMBROS", eager = true)
	private ForeignCollection<Casal> membros;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ForeignCollection<Casal> getCoordenadores() {
		return coordenadores;
	}

	public void setCoordenadores(ForeignCollection<Casal> coordenadores) {
		this.coordenadores = coordenadores;
	}

	public ForeignCollection<Casal> getMembros() {
		return membros;
	}

	public void setMembros(ForeignCollection<Casal> membros) {
		this.membros = membros;
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
