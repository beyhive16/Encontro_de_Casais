package entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="CASAL")
public class Casal {
	
	@DatabaseField(id = true, columnName = "ID", canBeNull = false, generatedId = true)
	private Integer id;
	
	@DatabaseField(columnName="ID_MARIDO",canBeNull=false,foreign=true)
	private Pessoa marido;
	
	@DatabaseField(columnName="ID_ESPOSA", canBeNull=false,foreign=true)
	private Pessoa esposa;
	
	@DatabaseField(columnName="ID_ENDERECO", canBeNull=false, foreign=true)
	private Endereco endereco;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Pessoa getMarido() {
		return marido;
	}
	public void setMarido(Pessoa marido) {
		this.marido = marido;
	}
	public Pessoa getEsposa() {
		return esposa;
	}
	public void setEsposa(Pessoa esposa) {
		this.esposa = esposa;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
