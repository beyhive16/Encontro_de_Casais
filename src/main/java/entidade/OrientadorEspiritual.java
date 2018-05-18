package entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="ORIENTADOR_ESPIRITUAL")
public class OrientadorEspiritual 
{
	@DatabaseField(columnName="ID", canBeNull = false, generatedId=true)
	private Integer id;
	
	@DatabaseField(columnName="APELIDO", canBeNull=true)
	private String apelido;
	
	@DatabaseField(columnName="NOME", canBeNull=false)
	private String nome;
	
	@DatabaseField(columnName="TELEFONE", canBeNull=false)
	private String telefone;
	
	@DatabaseField(columnName="ENDERECO", canBeNull = false)
	private String endereco;
	
	private boolean select;
	
	public OrientadorEspiritual() {
		this.select = false;
	}
	public OrientadorEspiritual(String Nome) {
		// TODO Auto-generated constructor stub
		this.select = false;
		this.nome = Nome;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
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
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	@Override
	public String toString() {
		return "ID:"+getId()+";APELIDO:"+getApelido();
	}
	public boolean getSelected() {
		return select;
	}
	public void setSelected(boolean select) {
		this.select = select;
	}

}
