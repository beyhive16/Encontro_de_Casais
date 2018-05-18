package Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ENDERECO")
public class Endereco {

	@DatabaseField(columnName="ID",canBeNull=false,generatedId=true)
	private Integer id;

	@DatabaseField(columnName = "RUA",canBeNull=true)
	private String rua;
	
	@DatabaseField(columnName = "N_CASA",canBeNull=true)
	private String nCasa;
	
	@DatabaseField(columnName = "COMPLEMENTO", canBeNull=true)
	private String complemento;
	
	@DatabaseField(columnName = "BAIRRO", canBeNull=true)
	private String bairro;
	
	@DatabaseField(columnName = "CIDADE", canBeNull=true)
	private String cidade;
	
	@DatabaseField(columnName = "CEP", canBeNull=true)
	private String cep;
	
	public Endereco(){}
	public Endereco(String id)
	{
		this.id = new Integer(id);
	}
	
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

	public String getnCasa() {
		return nCasa;
	}

	public void setnCasa(String nCasa) {
		this.nCasa = nCasa;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
}
