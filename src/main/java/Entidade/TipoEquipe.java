package Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="TIPO_EQUIPE")
public class TipoEquipe 
{
	@DatabaseField(columnName="ID", canBeNull = false, generatedId=true)
	private Integer id;
	
	@DatabaseField(columnName="NOME",canBeNull=false)
	private String nome;

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
	
	@Override
	public String toString() {
		return "ID:"+getId()+";NOME:"+getNome();
	}
}
