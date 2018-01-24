package entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ENDERECO")
public class Endereco {
	@DatabaseField(columnName = "ID", canBeNull = false, generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "RUA", canBeNull = true)
	private String rua;

	@DatabaseField(columnName = "NUMERO", canBeNull = true)
	private String numero;

	@DatabaseField(columnName = "BAIRRO", canBeNull = true)
	private String bairro;

	@DatabaseField(columnName = "CIDADE", canBeNull = true)
	private String cidade;

	@DatabaseField(columnName = "COMPLEMENTO", canBeNull = true)
	private String complemento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}
