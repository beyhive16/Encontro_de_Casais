package entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "PESSOA")
public class Pessoa {

	@DatabaseField(columnName = "ID", canBeNull = false, generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "NOME",canBeNull=false)
	private String nome;
	
	@DatabaseField(columnName = "NOME_USUAL", canBeNull=true)
	private String nomeUsual;
	
	@DatabaseField(columnName = "TELEFONE", canBeNull=true)
	private String telefone;
	
	@DatabaseField(columnName = "EMAIL", canBeNull=true)
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNomeUsual() {
		return nomeUsual;
	}

	public void setNomeUsual(String nomeUsual) {
		this.nomeUsual = nomeUsual;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
