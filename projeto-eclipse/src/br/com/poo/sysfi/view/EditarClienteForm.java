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
import br.com.poo.sysfi.model.Cliente;
import br.com.poo.sysfi.model.Data;
import br.com.poo.sysfi.model.Unidade;

@SuppressWarnings("serial")
public class EditarClienteForm extends EntityFrame {
	private JFrame ultimoFrame;
	private JPanel painel;
	private JLabel nomeLabel;
	private JLabel loginLabel;
	private JLabel senhaLabel;
	private JLabel rgLabel;
	private JLabel cpfLabel;
	private JLabel nascimentoLabel;
	private JLabel enderecoLabel;
	private JLabel numeroLabel;
	private JLabel cepLabel;
	private JLabel cidadeLabel;
	private JLabel telefoneLabel;
	private JLabel estadoLabel;
	private JLabel estadoCivilLabel;
	private JLabel fundoTela;
	protected JPasswordField senhaField;
	protected JTextField nomeField;
	protected JTextField loginField;
	protected JTextField rgField;
	protected JTextField cpfField;
	protected JTextField nascimentoField;
	protected JTextField enderecoField;
	protected JTextField numeroField;
	protected JTextField cepField;
	protected JTextField cidadeField;
	protected JTextField telefoneField;
	protected JComboBox<String> estadosComboBox;
	protected JComboBox<String> estadoCivilComboBox;
	private JButton botaoLimpar;
	protected JButton botaoSalvar;

	protected Unidade unidadeSelecionada;
	private Cliente clienteSelecionado;
	
	public EditarClienteForm() {
		initComponents();
	}
	
	public EditarClienteForm(JFrame ultimoFrame, Unidade unidadeSelecionada, Cliente clienteSelecionado) {
		this.ultimoFrame = ultimoFrame;
		this.clienteSelecionado = clienteSelecionado;
		this.unidadeSelecionada = unidadeSelecionada;
		initComponents();
	}
	
	private void initComponents() {
		setTitle("Cash Bank - Editar Cliente");
		setSize(550, 550);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		painel = new JPanel();
		painel.setPreferredSize(new Dimension(400, 400));
		painel.setLayout(null);
		add(painel);

		nomeLabel = new JLabel("Nome:");
		nomeLabel.setBounds(46, 102, 46, 14);
		painel.add(nomeLabel);

		nomeField = new JTextField();
		nomeField.setBounds(46, 116, 130, 20);
		painel.add(nomeField);
		
		loginLabel = new JLabel("Login:");
		loginLabel.setBounds(203, 102, 46, 14);
		painel.add(loginLabel);

		loginField = new JTextField();
		loginField.setBounds(203, 116, 130, 20);
		painel.add(loginField);
		
		senhaLabel = new JLabel("Senha:");
		senhaLabel.setBounds(352, 102, 46, 14);
		painel.add(senhaLabel);

		senhaField = new JPasswordField();
		senhaField.setBounds(352, 116, 130, 20);
		painel.add(senhaField);

		rgLabel = new JLabel("RG:");
		rgLabel.setBounds(46, 141, 46, 14);
		painel.add(rgLabel);

		rgField = new LimitadoTextField(LimitadoTextField.SOMENTENUMEROS, 10);
		rgField.setBounds(46, 155, 130, 20);
		painel.add(rgField);

		cpfLabel = new JLabel("CPF:");
		cpfLabel.setBounds(203, 139, 46, 14);
		painel.add(cpfLabel);

		cpfField = new LimitadoTextField(LimitadoTextField.SOMENTENUMEROS, 11);
		cpfField.setBounds(203, 155, 130, 20);
		painel.add(cpfField);

		nascimentoLabel = new JLabel("Nascimento:");
		nascimentoLabel.setBounds(352, 141, 94, 14);
		painel.add(nascimentoLabel);

		nascimentoField = new JTextField();
		nascimentoField.setBounds(352, 156, 130, 20);
		painel.add(nascimentoField);

		enderecoLabel = new JLabel("Endereço:");
		enderecoLabel.setBounds(46, 186, 62, 14);
		painel.add(enderecoLabel);

		enderecoField = new JTextField();
		enderecoField.setBounds(46, 200, 339, 20);
		painel.add(enderecoField);
		
		numeroLabel = new JLabel("Nº:");
		numeroLabel.setBounds(404, 186, 46, 14);
		painel.add(numeroLabel);

		numeroField = new LimitadoTextField(LimitadoTextField.SOMENTENUMEROS, 4);
		numeroField.setBounds(404, 200, 77, 20);
		painel.add(numeroField);

		cepLabel = new JLabel("CEP:");
		cepLabel.setBounds(46, 232, 46, 14);
		painel.add(cepLabel);

		cepField = new LimitadoTextField(LimitadoTextField.SOMENTENUMEROS, 8);
		cepField.setBounds(46, 247, 100, 20);
		painel.add(cepField);
		
		cidadeLabel = new JLabel("Cidade:");
		cidadeLabel.setBounds(166, 232, 46, 14);
		painel.add(cidadeLabel);

		cidadeField = new JTextField();
		cidadeField.setBounds(166, 247, 100, 20);
		painel.add(cidadeField);

		telefoneLabel = new JLabel("Telefone:");
		telefoneLabel.setBounds(285, 277, 62, 14);
		painel.add(telefoneLabel);

		telefoneField = new LimitadoTextField(LimitadoTextField.SOMENTENUMEROS, 11);
		telefoneField.setBounds(285, 292, 197, 20);
		painel.add(telefoneField);

		estadoLabel = new JLabel("Estado:");
		estadoLabel.setBounds(285, 231, 46, 14);
		painel.add(estadoLabel);

		estadosComboBox = new EstadosComboBox();
		estadosComboBox.setBounds(285, 246, 197, 20);
		painel.add(estadosComboBox);

		estadoCivilLabel = new JLabel("Estado Civil:");
		estadoCivilLabel.setBounds(46, 278, 79, 14);
		painel.add(estadoCivilLabel);

		estadoCivilComboBox = new JComboBox<String>();
		estadoCivilComboBox.setBounds(46, 293, 197, 20);
		estadoCivilComboBox.addItem("Solteiro");
		estadoCivilComboBox.addItem("Casado");
		estadoCivilComboBox.addItem("Separado");
		painel.add(estadoCivilComboBox);

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
        	ultimaForm.atualizarTabelaClientes();
        	ultimaForm.setVisible(true);
        	dispose();
        }
	}
	
	private void limpar() {
		nomeField.setText("");
		senhaField.setText("");
		enderecoField.setText("");
		cepField.setText("");
		cidadeField.setText("");
		estadosComboBox.setSelectedIndex(0);
		telefoneField.setText("");
		numeroField.setText("");
		loginField.setText("");
		cpfField.setText("");
		rgField.setText("");
		estadoCivilComboBox.setSelectedIndex(0);
		nascimentoField.setText("");
	}
	
	protected void carregarValoresCliente() {
		if(clienteSelecionado != null) {
			nomeField.setText(clienteSelecionado.getNome());
			senhaField.setText(clienteSelecionado.getSenha());
			enderecoField.setText(clienteSelecionado.getEndereco());
			cepField.setText(clienteSelecionado.getCep());
			cidadeField.setText(clienteSelecionado.getCidade());
			estadosComboBox.setSelectedItem(clienteSelecionado.getEstado());
			telefoneField.setText(clienteSelecionado.getTelefone());
			numeroField.setText(""+clienteSelecionado.getNumeroResidencial());
			loginField.setText(clienteSelecionado.getLogin());
			cpfField.setText(clienteSelecionado.getCpf());
			rgField.setText(clienteSelecionado.getRg());
			estadoCivilComboBox.setSelectedItem(clienteSelecionado.getEstadoCivil());
			nascimentoField.setText(clienteSelecionado.getDataNascimento().toString());
		}
	}
	
	private void salvar() {
		try {
			if(clienteSelecionado != null) {
				Cliente cliente = clienteSelecionado;
				cliente.setId(clienteSelecionado.getId());
				cliente.setNome(nomeField.getText());
				cliente.setSenha(senhaField.getText());
				cliente.setEndereco(enderecoField.getText());
				cliente.setCep(cepField.getText());
				cliente.setCidade(cidadeField.getText());
				cliente.setEstado((String)estadosComboBox.getSelectedItem());
				cliente.setTelefone(telefoneField.getText());
				cliente.setNumeroResidencial(Integer.parseInt(numeroField.getText()));
				cliente.setLogin(loginField.getText());
				cliente.setCpf(cpfField.getText());
				cliente.setRg(rgField.getText());
				cliente.setEstadoCivil((String)estadoCivilComboBox.getSelectedItem());
				cliente.setDataNascimento(new Data(nascimentoField.getText()));
				cliente.setUnidade(unidadeSelecionada);
				
				services.getClienteService().update(cliente);
				JOptionPane.showMessageDialog(null, "Operação realizada");
				voltarUltimaTela();
			}else {
				throw new ApplicationException("EditarClienteForm", "salvar", "Cliente inválido");
			}
		}catch (ApplicationException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new EditarClienteForm().setVisible(true);
			}
		});
	}
}
