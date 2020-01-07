package br.com.poo.sysfi.model;

@SuppressWarnings("serial")
public class ContaPoupanca extends ContaCorrente {
	public final static String CONTAPOUPANCA =  "conta_poupanca";
	public final static String RENDIMENTO =  "conta_poupanca_rendimento";
	public final static String DATARENDIMENTO =  "conta_poupanca_data_rendimento";
	
	private double rendimento;
	private Data dataRendimento;
	private boolean rendimentoAplicado = false;
	
	public ContaPoupanca(int numero, String donoCpf, String senha, double saldo, int codigo, double rendimento, String dataRendimento)
	{
		super(numero, donoCpf, senha, saldo, codigo);
		setRendimento(rendimento);
		setDataRendimento(new Data(dataRendimento));
	}

	public double getRendimento() {
		return rendimento;
	}

	private void setRendimento(double rendimento) {
		this.rendimento = rendimento;
	}
	
	public Data getDataRendimento() {
		return dataRendimento;
	}

	private void setDataRendimento(Data dataRendimento) {
		if(dataRendimento != null && dataRendimento.isDataValida()) {
			this.dataRendimento = dataRendimento;
			return;
		}
		this.dataRendimento = new Data(Data.getDataAtual());
	}
	
	public boolean isRendimentoAplicado() {
		return rendimentoAplicado;
	}

	public void aplicarRendimentoDiario() {
		int diasRendimento = Data.getDataDiff(Data.getDataAtual(), getDataRendimento().toString());
		if(diasRendimento > 0 && getRendimento() > 0.0) {
			for(int i = 1; i <= diasRendimento; i++) {
				depositar(getSaldo() * getRendimento());
			}
			this.rendimentoAplicado = true;
			setDataRendimento(new Data(Data.getDataAtual()));
		}
	}
}
