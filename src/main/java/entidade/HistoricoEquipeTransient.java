package entidade;

public class HistoricoEquipeTransient 
{
	private Integer ano;
	private String nome;
	
	
	public HistoricoEquipeTransient(){}
	public HistoricoEquipeTransient(HistoricoEquipe historico)
	{
		setAno(historico.getEquipe().getAno());
		setNome(historico.getEquipe().getTipoEquipe().getNome());
	}
	
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
