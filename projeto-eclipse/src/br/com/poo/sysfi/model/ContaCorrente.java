package br.com.poo.sysfi.model;

import java.util.Random;

@SuppressWarnings("serial")
public abstract class ContaCorrente extends Entity{
	public final static String NUMERO = "contacorrente_numero";
	public final static String DONOCPF = "contacorrente_dono_cpf";
	public final static String SENHA = "contacorrente_senha";
	public final static String SALDO = "contacorrente_saldo";
	public final static String UNIDADECODIGO = "contacorrente_unidade_codigo";
	
	public final static int numMaximo = 999999;
	public final static int numMinimo = 100000;
	
	private int numero;
	private String donoCpf;
	private String senha;
	private double saldo;
	private Unidade unidade;
	
	public ContaCorrente(int numero, String donoCpf, String senha, double saldo, int codigo) {
		setNumero(numero);
		setDonoCpf(donoCpf);
		setSenha(senha);
		depositar(saldo);
		setUnidade(new Unidade("", "","", "", "", "", "", 0, codigo, ""));
	}
	
	public boolean sacar(double valor){
		if(valor > this.saldo || valor <= 0){
			return false;
		}
		this.saldo -= valor;
		return true;
	}
		
	public boolean depositar(double valor){
		if(valor <= 0){
			return false;
		}
		this.saldo += valor;
		return true;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getDonoCpf() {
		return donoCpf;
	}

	public void setDonoCpf(String donoCpf) {
		this.donoCpf = donoCpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public double getSaldo() {
		return saldo;
	}
	
	public int getUltimoDigito() {
		return (this.numero % 10);
	}
	
	public static int gerarNumeroConta() {
		Random random = new Random();
	    return random.nextInt((numMaximo - numMinimo) + 1) + numMinimo;
	}
	
	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
}
