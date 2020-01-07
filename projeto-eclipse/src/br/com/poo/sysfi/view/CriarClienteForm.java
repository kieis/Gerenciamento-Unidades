package br.com.poo.sysfi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.com.poo.sysfi.exception.ApplicationException;
import br.com.poo.sysfi.model.Cliente;
import br.com.poo.sysfi.model.Unidade;

@SuppressWarnings("serial")
public class CriarClienteForm extends EditarClienteForm{
	public CriarClienteForm() {
		super();
	}
	
	public CriarClienteForm(JFrame ultimoFrame, Unidade unidadeSelecionada) {
		super(ultimoFrame, unidadeSelecionada, null);
		initComponents();
	}
	
	private void initComponents() {
		setTitle("Cash Bank - Criar Cliente");
		
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
			if(unidadeSelecionada !=  null) {
				Cliente cliente = new Cliente(
			              nomeField.getText(),
			              senhaField.getText(),
			              enderecoField.getText(),
			              cepField.getText(),
			              cidadeField.getText(),
			              (String)estadosComboBox.getSelectedItem(),
			              telefoneField.getText(),
			              Integer.parseInt(numeroField.getText()),
			              loginField.getText(),
			              cpfField.getText(),
			              rgField.getText(),
			              (String)estadoCivilComboBox.getSelectedItem(),
			              nascimentoField.getText(),
			              unidadeSelecionada.getCodigo());
		
				services.getClienteService().save(cliente);
				JOptionPane.showMessageDialog(null, "Operação realizada");
				voltarUltimaTela();
			}else {
				throw new ApplicationException("CriarClienteForm", "salvar", "Unidade inválida");
			}
		}catch (ApplicationException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
