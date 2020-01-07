package br.com.poo.sysfi.model;

@SuppressWarnings("serial")
public class Celula extends Entity{
	public final static String NOME = "celula_nome";
	public final static String SENHA = "celula_senha";
	public final static String ENDERECO = "celula_endereco";
	public final static String CEP = "celula_cep";
	public final static String NUMERORESIDENCIAL = "celula_numero_residencial";
	public final static String CIDADE = "celula_cidade";
	public final static String ESTADO = "celula_estado";
	public final static String TELEFONE = "celula_telefone";
	
	private String nome;
	private String senha;
    private String endereco;
    private String cep;
    private String cidade;
    private String estado;
    private String telefone;
    private int numeroResidencial;
    
    public Celula(String nome, String senha, String endereco, String cep, String cidade, String estado,
    		      String telefone, int numeroResidencial) {
		setNome(nome);
		setSenha(senha);
		setEndereco(endereco);
		setCep(cep);
		setCidade(cidade);
		setEstado(estado);
		setTelefone(telefone);
		setNumeroResidencial(numeroResidencial);
    }
    
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public int getNumeroResidencial() {
		return numeroResidencial;
	}
	
	public void setNumeroResidencial(int numeroResidencial) {
		this.numeroResidencial = numeroResidencial;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
