package br.com.poo.sysfi.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario{
	private static final long serialVersionUID = 1L;
	public static final String CLIENTE = "cliente";
    
    public List<ContaCorrente> contasLista = new ArrayList<ContaCorrente>();
    
    public Cliente(String nome, String senha, String endereco, String cep, String cidade, String estado,
		      String telefone, int numeroResidencial, String login, String cpf, String rg, String estadoCivil, 
		      String dataNascimento, int codigo) {
    	super(nome, senha, endereco, cep, cidade, estado, telefone, numeroResidencial, login, cpf, rg, estadoCivil,
    		  dataNascimento, codigo);
    }
}
