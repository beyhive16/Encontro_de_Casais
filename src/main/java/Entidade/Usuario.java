package Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "USUARIO")
public class Usuario {
	@DatabaseField(columnName = "ID", canBeNull = false, generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "NOME", canBeNull = true)
	private String nome;

	@DatabaseField(columnName = "NOME_USUAL", canBeNull = false)
	private String nomeUsual;

	@DatabaseField(columnName = "SENHA", canBeNull = true)
	private String senha;

	@DatabaseField(columnName = "TELEFONE", canBeNull = true)
	private String telefone;
	
	@DatabaseField(columnName="ID_ENDERECO",foreign=true, foreignAutoRefresh=true)
	private Endereco endereco;
	
	public Usuario() {
	}

	public Usuario(String[] usuario) {
		if (usuario!=null) {
			setId(Integer.parseInt(usuario[0]));
			setNome(usuario[1]);
			setNomeUsual(usuario[2]);
			setSenha(usuario[3]);
		}
	}

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

	public String getNomeUsual() {
		return nomeUsual;
	}

	public void setNomeUsual(String nomeUsual) {
		this.nomeUsual = nomeUsual;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	
}
