package br.com.poo.sysfi.view;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class AutentificacaoField {
	private JPasswordField senhaField;
	private JLabel mensagemLabel;
	private JPanel painel;
	
	private String titulo;
	private String mensagem;
	private int tamanho;
	
	public AutentificacaoField(String titulo, String mensagem, int tamanho) {
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.tamanho = tamanho;
		initComponents();
	}
	
	private void initComponents() {
		senhaField = new JPasswordField(tamanho);
		senhaField.setEchoChar('*'); 

		mensagemLabel = new JLabel(mensagem);
				
		painel = new JPanel();
		painel.add(mensagemLabel);
		painel.add(senhaField);
		
		JOptionPane.showMessageDialog(null, painel, titulo, JOptionPane.PLAIN_MESSAGE);
	}
	
	@SuppressWarnings("deprecation")
	public String getText() {
		return senhaField.getText();
	}
}
