package entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "CASAL")
public class Casal {

	@DatabaseField(columnName = "ID", canBeNull = false, generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "ID_MARIDO", canBeNull = false, foreign = true)
	private Pessoa marido;

	@DatabaseField(columnName = "ID_ESPOSA", canBeNull = false, foreign = true)
	private Pessoa esposa;
	
	@DatabaseField(columnName = "NM_CASAL", canBeNull = false)
	private String nomeCasal;
	
	@DatabaseField(columnName = "EMAIL", canBeNull = false)
	private String email;

	@DatabaseField(columnName = "ENDERECO", canBeNull = false)
	private String endereco;

	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "ID_EQUIPE")
	private Equipe equipe;

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

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNomeCasal() {
		return nomeCasal;
	}

	public void setNomeCasal(String nomeCasal) {
		this.nomeCasal = nomeCasal;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String[] getCasal()
	{
		String[] casalString = new String[4];
		casalString[0] = getNomeCasal();
		casalString[1] = getEsposa().getNome();
		casalString[2] = getMarido().getNome();
		casalString[3] = getEsposa().getTelefone();
		return casalString;	
	}
}
