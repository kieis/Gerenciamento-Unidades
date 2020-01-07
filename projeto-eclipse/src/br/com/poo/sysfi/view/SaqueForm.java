package br.com.poo.sysfi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.com.poo.sysfi.exception.ApplicationException;
import br.com.poo.sysfi.model.ContaCorrente;

@SuppressWarnings("serial")
public class SaqueForm extends ContaAcoesForm {
	public SaqueForm() {
		super();
		initComponents();
	}
	
	public SaqueForm(JFrame ultimoFrame, ContaCorrente contaLogada) {
		super(ultimoFrame, contaLogada);
		initComponents();
	}
	
	private void initComponents() {
		setTitle("Cash Bank - Saque");

		for(ActionListener x : botaoConfirmar.getActionListeners()) {
			botaoConfirmar.removeActionListener(x);
		}
		botaoConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saque();
			}
		});
	}
	
	private void saque() {
		if(contaLogada != null) {
			try {
				if(!valorField.getText().trim().equals("") && contaLogada.sacar(Double.parseDouble(valorField.getText()))) {
					if(contaService != null) {
						contaService.update(contaLogada);
						atualizarInformacoesConta(contaLogada);
						JOptionPane.showMessageDialog(null, "Operação realizada");
					}
				}else {
					throw new ApplicationException("SaqueForm", "saque", "Não foi possível realizar o saque");
				}
			}catch (ApplicationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new SaqueForm().setVisible(true);
			}
		});
	}
}