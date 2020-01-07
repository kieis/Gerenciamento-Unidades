package br.com.poo.sysfi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.com.poo.sysfi.exception.ApplicationException;
import br.com.poo.sysfi.model.ContaCorrente;
import br.com.poo.sysfi.model.ContaPoupanca;
import br.com.poo.sysfi.model.ContaSimples;
import br.com.poo.sysfi.model.Data;
import br.com.poo.sysfi.model.Unidade;

@SuppressWarnings("serial")
public class CriarContaForm extends EditarContaForm {
	private Unidade unidadeSelecionada;
	
	public CriarContaForm() {
		initComponents();
	}
	
	public CriarContaForm(JFrame ultimoFrame, Unidade unidadeSelecionada) {
		super(ultimoFrame, null);
		this.unidadeSelecionada = unidadeSelecionada;
		initComponents();
	}
	
	private void initComponents() {
		setTitle("Cash Bank - Criar Conta");

		numLabel.setVisible(false);
		numField.setVisible(false);
		codLabel.setVisible(false);
		codField.setVisible(false);
		
		tipoContaComboBox.setVisible(true);
		tipoContaComboBox.setEnabled(true);
		tipoContaComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tipoContaComboBox.getSelectedIndex() == 0) {
					rendimentoLabel.setVisible(false);
					rendimentoField.setEnabled(false);
					rendimentoField.setVisible(false);
				}else if(tipoContaComboBox.getSelectedIndex() == 1) {
					rendimentoLabel.setVisible(true);
					rendimentoField.setEnabled(true);
					rendimentoField.setVisible(true);
				}
			}
		});
		
		for(ActionListener x : botaoSalvar.getActionListeners()) {
			botaoSalvar.removeActionListener(x);
		}
		botaoSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				criar();
			}
		});
	}
	
	private void criar() {
		try {
			ContaCorrente conta = null;
			
			if(tipoContaComboBox.getSelectedIndex() == 0) {
				conta = new ContaSimples(
		                    ContaCorrente.gerarNumeroConta(),
		                    cpfField.getText(),
		                    senhaField.getText(),
		                    Double.parseDouble(saldoField.getText()),
		                    unidadeSelecionada.getCodigo());
				
				services.getContaSimplesService().save(conta);
			}else if(tipoContaComboBox.getSelectedIndex() == 1) {
				conta = new ContaPoupanca(
						    ContaCorrente.gerarNumeroConta(),
	                    	cpfField.getText(),
	                    	senhaField.getText(),
	                    	Double.parseDouble(saldoField.getText()),
	                    	unidadeSelecionada.getCodigo(),
	                    	Double.parseDouble(rendimentoField.getText())/100.0, 
	                    	Data.getDataAtual().toString());
			
				services.getContaPoupancaService().save(conta);
			}
			
			if(conta != null) {		
				JOptionPane.showMessageDialog(null, "Operação realizada");
				voltarUltimaTela();
			}else {
				throw new ApplicationException("CriarContaForm", "salvar", "Conta inválida");
			}
		}catch (ApplicationException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new CriarContaForm().setVisible(true);
			}
		});
	}
}
