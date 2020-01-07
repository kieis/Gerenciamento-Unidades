package br.com.poo.sysfi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.com.poo.sysfi.exception.ApplicationException;
import br.com.poo.sysfi.model.ContaCorrente;

@SuppressWarnings("serial")
public class DepositoForm extends ContaAcoesForm {
	public DepositoForm() {
		super();
		initComponents();
	}
	
	public DepositoForm(JFrame ultimoFrame, ContaCorrente contaLogada) {
		super(ultimoFrame, contaLogada);
		initComponents();
	}
	
	private void initComponents() {
		setTitle("Cash Bank - Deposito");

		for(ActionListener x : botaoConfirmar.getActionListeners()) {
			botaoConfirmar.removeActionListener(x);
		}
		botaoConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deposito();
			}
		});
	}
	
	private void deposito() {
		if(contaLogada != null) {
			try {
				if(!valorField.getText().trim().equals("") && contaLogada.depositar(Double.parseDouble(valorField.getText()))) {
					if(contaService != null) {
						contaService.update(contaLogada);
						atualizarInformacoesConta(contaLogada);
						JOptionPane.showMessageDialog(null, "Operação realizada");
					}
				}else {
					throw new ApplicationException("DepositoForm", "deposito", "Não foi possível realizar o deposito");
				}
			}catch (ApplicationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new DepositoForm().setVisible(true);
			}
		});
	}
}