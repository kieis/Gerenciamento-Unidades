package br.com.poo.sysfi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.com.poo.sysfi.exception.ApplicationException;
import br.com.poo.sysfi.model.Unidade;

@SuppressWarnings("serial")
public class CriarUnidadeForm extends EditarUnidadeForm{
	public CriarUnidadeForm() {
		super();
	}
	
	public CriarUnidadeForm(JFrame ultimoFrame) {
		super(ultimoFrame, null);
		initComponents();
	}
	
	private void initComponents() {
		setTitle("Cash Bank - Criar Unidade");
		
		for(ActionListener x : botaoSalvar.getActionListeners()) {
			botaoSalvar.removeActionListener(x);
		}
		botaoSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
	}

	private void salvar() {
		try {
			if(services.getUnidadeService() != null) {
				Unidade unidade = new Unidade(
				          nomeField.getText(),
				          senhaField.getText(),
				          enderecoField.getText(),
				          cepField.getText(),
				          cidadeField.getText(),
				          (String)estadosComboBox.getSelectedItem(),
				          telefoneField.getText(),
				          Integer.parseInt(numeroField.getText()),
				          Integer.parseInt(codField.getText()),
				          cnpjField.getText());
				
				services.getUnidadeService().save(unidade);
				JOptionPane.showMessageDialog(null, "Operação realizada");
				voltarUltimaTela();	
			}
		}catch (ApplicationException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
