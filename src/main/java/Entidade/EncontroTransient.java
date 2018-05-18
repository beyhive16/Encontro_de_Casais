package Entidade;

public class EncontroTransient 
{
	private Integer id = null;
	private Integer ano = null;
	private Integer id_padre = null;
	private String nome_padre = null;
	private Integer id_casalCoordenador = null;
	private String apelido_casal = null;
	
	public EncontroTransient(){}
	public EncontroTransient(Encontro encontro, Casal casal)
	{
		this.id=encontro.getId();
		this.ano = encontro.getAno();
		if(encontro.getOrientadorEspiritual()!=null)
		{			
			this.id_padre = encontro.getOrientadorEspiritual().getId();
			this.nome_padre = encontro.getOrientadorEspiritual().getNome();
		}
		if(casal!=null)
		{			
			this.id_casalCoordenador = casal.getId();
			this.apelido_casal = casal.getApelidoDoCasal();
		}
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public Integer getId_padre() {
		return id_padre;
	}
	public void setId_padre(Integer id_padre) {
		this.id_padre = id_padre;
	}
	public String getNome_padre() {
		return nome_padre;
	}
	public void setNome_padre(String nome_padre) {
		this.nome_padre = nome_padre;
	}
	public Integer getId_casalCoordenador() {
		return id_casalCoordenador;
	}
	public void setId_casalCoordenador(Integer id_casalCoordenador) {
		this.id_casalCoordenador = id_casalCoordenador;
	}
	
	public String getApelido_casal() {
		return apelido_casal;
	}
	public void setApelido_casal(String apelido_casal) {
		this.apelido_casal = apelido_casal;
	}
	@Override
	public String toString() {
		return "["+this.id+";"+this.ano+";"+this.id_padre+";"+this.nome_padre+";"+this.id_casalCoordenador+";"+this.apelido_casal+"]";
	}
}
