package br.com.poo.sysfi.model;

@SuppressWarnings("serial")
public class ContaSimples extends ContaCorrente{
	public final static String CONTASIMPLES =  "conta_simples";
	
	public ContaSimples(int numero, String donoCpf, String senha, double saldo, int codigo)
	{
		super(numero, donoCpf, senha, saldo, codigo);
	}
}
