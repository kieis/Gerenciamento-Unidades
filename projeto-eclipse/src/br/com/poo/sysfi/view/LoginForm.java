package br.com.poo.sysfi.view;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.poo.sysfi.exception.ApplicationException;
import br.com.poo.sysfi.model.Cliente;
import br.com.poo.sysfi.model.Entity;
import br.com.poo.sysfi.model.Funcionario;
import br.com.poo.sysfi.model.Usuario;

@SuppressWarnings("serial")
public class LoginForm extends EntityFrame {
	private JPanel painel;
	private JLabel loginLabel;
	private JLabel senhaLabel;
	private JLabel fundoTela;
	private JTextField loginField;
	private JPasswordField senhaField;
	private JButton botaoEntrar;
	
	public LoginForm() {
		initComponents();
	}
	
	private void initComponents() {
		setTitle("Cash Bank - Login");
		setSize(550, 550);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		painel = new JPanel();
		painel.setPreferredSize(new Dimension(400, 400));
		painel.setLayout(null);
		add(painel);

		loginLabel = new JLabel("Login:");
		loginLabel.setBounds(147, 205, 46, 14);
		painel.add(loginLabel);

		loginField = new JTextField();
		loginField.setBounds(203, 201, 151, 23);
		painel.add(loginField);

		senhaLabel = new JLabel("Senha:");
		senhaLabel.setBounds(147, 250, 46, 14);
		painel.add(senhaLabel);

		senhaField = new JPasswordField();
		senhaField.setBounds(203, 246, 151, 23);
		painel.add(senhaField);

		botaoEntrar = new JButton("Entrar");
		botaoEntrar.setBounds(275, 291, 78, 23);
		botaoEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logar();
            }
        });
		painel.add(botaoEntrar);
		
		fundoTela = new JLabel("");
		fundoTela.setIcon(new ImageIcon(getClass().getResource("/br/com/poo/sysfi/img/imgFundo.jpeg")));
		fundoTela.setBounds(0, 0, 550, 550);
		painel.add(fundoTela);
	}
	
	@SuppressWarnings("deprecation")
	private void logar() {
		if(!validarServices()) {
			return;
		}
		
		try {
			boolean loginConfirm = false;
			services.atualizarUsuariosLista();
			for(Entity x : services.getUsuariosLista()) {
				Usuario usuario = (Usuario)x;
				if(usuario != null && usuario.getLogin().equals(loginField.getText()) && usuario.getSenha().equals(senhaField.getText())) {
					if(usuario instanceof Cliente) {
						Cliente cliente = (Cliente)usuario;
						loginConfirm = true;
						abrirClienteJanela(cliente);
					}else if(usuario instanceof Funcionario) {
						Funcionario funcionario = (Funcionario)usuario;
						loginConfirm = true;
						abrirFuncionarioJanela(funcionario);
					}else {
						throw new ApplicationException("LoginForm", "logar", "Instância não encontrada");
					}
					break;
				}
			}
			if(loginConfirm == false) {
				throw new ApplicationException("LoginForm", "logar", "Não foi possível logar");
			}
		}catch(ApplicationException e) { 
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void abrirClienteJanela(Cliente cliente) {
		new ListarContasForm(cliente).setVisible(true);
		dispose();
	}
	
	private void abrirFuncionarioJanela(Funcionario funcionario) {
		new ListarUnidadesForm(funcionario).setVisible(true);
		dispose();
	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new LoginForm().setVisible(true);
			}
		});
	}
}
