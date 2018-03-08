package entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ENCONTRO")
public class Encontro {
	
	@DatabaseField(columnName = "ID", canBeNull = false, generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "TEMA_ENCONTRO")
	private String temaEncontro;

	@DatabaseField(columnName = "ID_ORIENTADOR_ESPIRITUAL", foreign = true)
	private Pessoa orientadorEspiritual;
	
	@DatabaseField(columnName = "ID_COORDENADOR", foreign = true)
	private Casal casalCoordenador;
	
	@DatabaseField(columnName = "ANO", canBeNull = false)
	private int ano;
	
	@ForeignCollectionField(eager = true, columnName="ID_EQUIPE")
	private ForeignCollection<Equipe> equipes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTemaEncontro() {
		return temaEncontro;
	}

	public void setTemaEncontro(String temaEncontro) {
		this.temaEncontro = temaEncontro;
	}

	public Pessoa getOrientadorEspiritual() {
		return orientadorEspiritual;
	}

	public void setOrientadorEspiritual(Pessoa orientadorEspiritual) {
		this.orientadorEspiritual = orientadorEspiritual;
	}

	public ForeignCollection<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(ForeignCollection<Equipe> equipes) {
		this.equipes = equipes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Casal getCasalCoordenador() {
		return casalCoordenador;
	}

	public void setCasalCoordenador(Casal casalCoordenador) {
		this.casalCoordenador = casalCoordenador;
	}
	
	

}
