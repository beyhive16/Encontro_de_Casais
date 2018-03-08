package entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "EQUIPE")
public class Equipe {
	
	@DatabaseField(columnName = "ID", canBeNull = false, generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "ANO", canBeNull = false)
	private Integer ano;

	@DatabaseField(columnName = "ID_TIPO_EQUIPE", canBeNull = false, foreign = true)
	private TipoEquipe tipoEquipe;

	@DatabaseField(columnName = "ID_COORDENADORES", foreign = true)
	private Casal coordenador;

	@ForeignCollectionField(eager=true, columnName="ID_MEMBROS")
	private ForeignCollection<Casal> membros;
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "ID_ENCONTRO")
	private Encontro encontro;
	
	
	public Equipe(){}
	public Equipe(String id)
	{
		this.id = Integer.parseInt(id);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Casal getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Casal coordenadores) {
		this.coordenador = coordenadores;
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

	public Encontro getEncontro() {
		return encontro;
	}

	public void setEncontro(Encontro encontro) {
		this.encontro = encontro;
	}

}
