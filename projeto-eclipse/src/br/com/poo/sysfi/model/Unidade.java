package br.com.poo.sysfi.model;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Unidade extends Celula {
    public static final String UNIDADE = "unidade";
    public static final String CODIGO = "unidade_codigo";
    public static final String CNPJ = "unidade_cnpj";
    
    private int codigo;
    private String cnpj;
	public List<Cliente> listaClientes = new ArrayList<Cliente>();
	public List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
	public List<ContaCorrente> listaContas = new ArrayList<ContaCorrente>();
	
	public Unidade(String nome, String senha, String endereco, String cep, String cidade, String estado,
		      String telefone, int numeroResidencial, int codigo, String cnpj) {
		super(nome, senha, endereco, cep, cidade, estado, telefone, numeroResidencial);
		setCodigo(codigo);
		setCnpj(cnpj);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}
