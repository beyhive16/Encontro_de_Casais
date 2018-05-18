package Entidade;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "CASAL")
public class Casal 
{
	@DatabaseField(columnName="ID", canBeNull = false, generatedId=true)
	private Integer id;
	
	@DatabaseField(columnName="FOTO",dataType = DataType.BYTE_ARRAY)
	private byte[] foto;
	
	@DatabaseField(columnName="APELIDO_DO_CASAL", canBeNull=false)
	private String apelidoDoCasal;
	
	@DatabaseField(columnName="NOME_DELE",canBeNull = false)
	private String nomeDele;
	
	@DatabaseField(columnName="NOME_DELA",canBeNull = false)
	private String nomeDela;
	
	@DatabaseField(columnName="TELEFONE_DELE",canBeNull = true)
	private String telefoneDele;
	
	@DatabaseField(columnName="TELEFONE_DELA",canBeNull = true)
	private String telefoneDela;
	
	@DatabaseField(columnName="ID_ENDERECO",foreign=true, foreignAutoRefresh=true)
	private Endereco endereco;
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "ID_EQUIPE")
	private Equipe equipe;
	
	private boolean selected;

	public Casal(){
		this.selected = false;
	}
	
	public Casal(String nomeDela, String nomeDele, String apelido, String telefone) {
		this.selected = false;
		this.nomeDela = nomeDela;
		this.nomeDele = nomeDele;
		this.apelidoDoCasal = apelido;
		this.telefoneDele = telefone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeDele() {
		return nomeDele;
	}

	public void setNomeDele(String nomeDele) {
		this.nomeDele = nomeDele;
	}

	public String getNomeDela() {
		return nomeDela;
	}

	public void setNomeDela(String nomeDela) {
		this.nomeDela = nomeDela;
	}

	public String getTelefoneDele() {
		return telefoneDele;
	}

	public void setTelefoneDele(String telefoneDele) {
		this.telefoneDele = telefoneDele;
	}

	public String getTelefoneDela() {
		return telefoneDela;
	}

	public void setTelefoneDela(String telefoneDela) {
		this.telefoneDela = telefoneDela;
	}
	
	public String getApelidoDoCasal() {
		return apelidoDoCasal;
	}

	public void setApelidoDoCasal(String apelidoDoCasal) {
		this.apelidoDoCasal = apelidoDoCasal;
	}
	
	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "ID:"+getId()+";APELIDO:"+getApelidoDoCasal();
	}
	
    public boolean getSelected() {
        return selected;
    }
    
    public void setSelected(boolean value) {
        this.selected = value;
    }
}
