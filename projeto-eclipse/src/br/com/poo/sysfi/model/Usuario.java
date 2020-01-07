package br.com.poo.sysfi.model;

@SuppressWarnings("serial")
public class Usuario extends Celula {
    public static final String LOGIN = "usuario_login";
    public static final String CPF = "usuario_cpf";
    public static final String RG = "usuario_rg";
    public static final String ESTADOCIVIL = "usuario_estadocivil";
    public static final String DATANASCIMENTO = "usuario_datanascimento";
    public static final String UNIDADECODIGO = "usuario_unidade_codigo";
    
	private String login;
    private String cpf;
    private String rg;
    private String estadoCivil;
    private Data dataNascimento;
    private Unidade unidade;
	
	public Usuario(String nome, String senha, String endereco, String cep, String cidade, String estado,
		      String telefone, int numeroResidencial, String login, String cpf, String rg, String estadoCivil, 
		      String dataNascimento, int codigo) {
		super(nome, senha, endereco, cep, cidade, estado, telefone, numeroResidencial);
		setLogin(login);
		setCpf(cpf);
		setRg(rg);
		setEstadoCivil(estadoCivil);
		setDataNascimento(new Data(dataNascimento));
		setUnidade(new Unidade("", "","", "", "", "", "", 0, codigo, ""));
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Data getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Data dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
}
