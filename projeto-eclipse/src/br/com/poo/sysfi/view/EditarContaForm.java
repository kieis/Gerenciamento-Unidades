package br.com.poo.sysfi.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.poo.sysfi.exception.ApplicationException;
import br.com.poo.sysfi.model.ContaCorrente;
import br.com.poo.sysfi.model.ContaPoupanca;
import br.com.poo.sysfi.model.ContaSimples;
import br.com.poo.sysfi.model.Data;
import br.com.poo.sysfi.model.Entity;

@SuppressWarnings("serial")
public class EditarContaForm extends EntityFrame {
	private JFrame ultimoFrame;
	protected JPanel painel;
	protected JLabel numLabel;
	private JLabel senhaLabel;
	private JLabel cpfLabel;
	private JLabel saldoLabel;
	protected JLabel codLabel;
	private JLabel tipoContaLabel;
	protected JLabel rendimentoLabel;
	private JLabel fundoTela;
	protected JPasswordField senhaField;
	protected JTextField rendimentoField;
	protected JTextField numField;
	protected JTextField saldoField;
	protected JTextField cpfField;
	protected JTextField codField;
	private JButton botaoLimpar;
	protected JButton botaoSalvar;
	protected JComboBox<String> tipoContaComboBox;

	protected ContaCorrente contaSelecionada;
	
	public EditarContaForm() {
		initComponents();
	}
	
	public EditarContaForm(JFrame ultimoFrame, ContaCorrente contaSelecionada) {
		this.ultimoFrame = ultimoFrame;
		this.contaSelecionada = contaSelecionada;
		initComponents();
		atualizarInformacoesConta();
	}
	
	private void initComponents() {
		setTitle("Cash Bank - Editar Conta");
		setSize(550, 550);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		painel = new JPanel();
		painel.setPreferredSize(new Dimension(400, 400));
		painel.setLayout(null);
		add(painel);

		numLabel = new JLabel("Numero:");
		numLabel.setBounds(46, 102, 200, 14);
		painel.add(numLabel);

		numField = new LimitadoTextField(LimitadoTextField.SOMENTENUMEROS, 6);
		numField.setBounds(46, 116, 446, 20);
		painel.add(numField);
		
		senhaLabel = new JLabel("Senha:");
		senhaLabel.setBounds(46, 141, 200, 14);
		painel.add(senhaLabel);

		senhaField = new JPasswordField();
		senhaField.setBounds(46, 155, 130, 20);
		painel.add(senhaField);
		
		cpfLabel = new JLabel("Dono CPF:");
		cpfLabel.setBounds(203, 141, 200, 14);
		painel.add(cpfLabel);

		cpfField = new LimitadoTextField(LimitadoTextField.SOMENTENUMEROS, 11);
		cpfField.setBounds(203, 155, 140, 20);
		painel.add(cpfField);

		saldoLabel = new JLabel("Saldo: ");
		saldoLabel.setBounds(362, 141, 200, 14);
		painel.add(saldoLabel);
		
		saldoField = new LimitadoTextField(LimitadoTextField.SOMENTENUMEROS, 10);
		saldoField.setBounds(362, 155, 130, 20);
		painel.add(saldoField);
		
		codLabel = new JLabel("Unidade: ");
		codLabel.setBounds(46, 181, 200, 14);
		painel.add(codLabel);
		
		codField = new LimitadoTextField(LimitadoTextField.SOMENTENUMEROS, 5);
		codField.setBounds(46, 195, 130, 20);
		painel.add(codField);
		
		tipoContaLabel = new JLabel("Tipo de conta:");
		tipoContaLabel.setBounds(362, 181, 200, 14);
		tipoContaLabel.setVisible(false);
		painel.add(tipoContaLabel);

		tipoContaComboBox = new JComboBox<String>(); 
		tipoContaComboBox.addItem("Conta Simples");
		tipoContaComboBox.addItem("Conta Poupança");
		tipoContaComboBox.setBounds(362, 195, 130, 20);
		tipoContaComboBox.setEnabled(false);
		tipoContaComboBox.setVisible(false);
		painel.add(tipoContaComboBox);

		rendimentoLabel = new JLabel("Rendimento: ");
		rendimentoLabel.setBounds(203, 181, 200, 14);
		rendimentoLabel.setVisible(false);
		painel.add(rendimentoLabel);
		
		rendimentoField = new LimitadoTextField(LimitadoTextField.SOMENTENUMEROS, 3);
		rendimentoField.setBounds(203, 195, 140, 20);
		rendimentoField.setEnabled(false);
		rendimentoField.setVisible(false);
		painel.add(rendimentoField);
		
		botaoLimpar = new JButton("LIMPAR");
		botaoLimpar.setBounds(46, 381, 100, 23);
		botaoLimpar.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		painel.add(botaoLimpar);

		botaoSalvar = new JButton(("SALVAR"));
		botaoSalvar.setBounds(393, 381, 89, 23);
		botaoSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		painel.add(botaoSalvar);

		fundoTela = new JLabel("");
		fundoTela.setIcon(new ImageIcon(getClass().getResource("/br/com/poo/sysfi/img/imgFundo.jpeg")));
		fundoTela.setBounds(0, 0, 550, 550);
		painel.add(fundoTela);
		
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		    	voltarUltimaTela();
		    }
		});
	}
	
	protected void voltarUltimaTela() {
        if(ultimoFrame != null) {
        	AdministrativoUnidadeForm ultimaForm = (AdministrativoUnidadeForm)ultimoFrame;
        	ultimaForm.atualizarTabelaContas();
        	ultimaForm.setVisible(true);
        	dispose();
        }
	}
	
	private void limpar() {
		numField.setText("");
		senhaField.setText("");
		cpfField.setText("");
		codField.setText("");
		saldoField.setText("");
	}
	
	protected void carregarValoresCliente() {
		if(contaSelecionada != null) {
			numField.setText("" + contaSelecionada.getNumero());
			senhaField.setText(contaSelecionada.getSenha());
			cpfField.setText(contaSelecionada.getDonoCpf());
			codField.setText("" + contaSelecionada.getUnidade().getCodigo());
			saldoField.setText("" + contaSelecionada.getSaldo());
			if(contaSelecionada instanceof ContaPoupanca) {
				ContaPoupanca x = (ContaPoupanca)contaSelecionada;
				rendimentoField.setText("" + (x.getRendimento() * 100.0));
			}
		}
	}
	
	private void salvar() {
		try {
			ContaCorrente conta = null;

			if(contaSelecionada != null && contaSelecionada instanceof ContaSimples) {
				conta = new ContaSimples(
						    Integer.parseInt(numField.getText()),
						    cpfField.getText(),
						    senhaField.getText(),
						    Double.parseDouble(saldoField.getText()),
						    Integer.parseInt(codField.getText()));
				conta.setId(contaSelecionada.getId());
				
				services.getContaSimplesService().update(conta);
			}else if(contaSelecionada != null && contaSelecionada instanceof ContaPoupanca) {
				conta = new ContaPoupanca(
						Integer.parseInt(numField.getText()),
                    	cpfField.getText(),
                    	senhaField.getText(),
                    	Double.parseDouble(saldoField.getText()),
                    	Integer.parseInt(codField.getText()),
                    	Double.parseDouble(rendimentoField.getText())/100.0, 
                    	((ContaPoupanca)contaSelecionada).getDataRendimento().toString());
				conta.setId(contaSelecionada.getId());
				
				services.getContaPoupancaService().update(conta);
			}
			if(conta != null) {
				JOptionPane.showMessageDialog(null, "Operação realizada");
				voltarUltimaTela();
			}else {
				throw new ApplicationException("EditarContaForm", "salvar", "Conta inválida");
			}
		}catch (ApplicationException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void atualizarInformacoesConta() {
		if(contaSelecionada != null) {
			if(contaSelecionada instanceof ContaSimples) {
				System.out.println("oi");
				setTitle("Cash Bank - Editar Conta Simples");
			}else if(contaSelecionada instanceof ContaPoupanca) {
				setTitle("Cash Bank - Editar Conta Poupança");
				rendimentoField.setEnabled(true);
				rendimentoField.setVisible(true);
				rendimentoLabel.setVisible(true);
			}
		}
	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new EditarContaForm().setVisible(true);
			}
		});
	}
}
