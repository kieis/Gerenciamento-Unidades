package br.com.poo.sysfi.model;

@SuppressWarnings("serial")
public class Funcionario extends Usuario{
	public static final String FUNCIONARIO = "funcionario";
	public static final String MATRICULA = "funcionario_matricula";
	
	private String matricula;
	
	public Funcionario(String nome, String senha, String endereco, String cep, String cidade, 
			           String estado, String telefone, int numeroResidencial, String login, String cpf, String rg, String estadoCivil, 
			           String dataNascimento, int codigo, String matricula) {
		super(nome, senha, endereco, cep, cidade, estado, telefone, numeroResidencial, login, cpf, rg,
			  estadoCivil, dataNascimento, codigo);
		setMatricula(matricula);
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
}
