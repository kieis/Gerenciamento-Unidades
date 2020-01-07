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
import br.com.poo.sysfi.model.ContaPoupanca;
import br.com.poo.sysfi.model.ContaSimples;
import br.com.poo.sysfi.model.Entity;
import br.com.poo.sysfi.model.Funcionario;
import br.com.poo.sysfi.model.Unidade;

@SuppressWarnings("serial")
public class EditarUnidadeForm extends EntityFrame {
	private JFrame ultimoFrame;
	private JPanel painel;
	private JLabel codLabel;
	private JLabel senhaLabel;
	private JLabel cnpjLabel;
	private JLabel enderecoLabel;
	private JLabel nomeLabel;
	private JLabel cepLabel;
	private JLabel numeroLabel;
	private JLabel cidadeLabel;
	private JLabel telefoneLabel;
	private JLabel estadoLabel;
	private JLabel fundoTela;
	protected JTextField enderecoField;
	protected JTextField codField;
	protected JTextField cnpjField;
	protected JTextField nomeField;
	protected JTextField numeroField;
	protected JTextField cepField;
	protected JTextField cidadeField;
	protected JTextField telefoneField;
	protected JPasswordField senhaField;
	protected JComboBox<String> estadosComboBox;
	private JButton botaoLimpar;
	protected JButton botaoSalvar;

	protected Unidade unidadeSelecionada;
	
	public EditarUnidadeForm() {
		initComponents();
	}
	
	public EditarUnidadeForm(JFrame ultimoFrame, Unidade unidadeSelecionada) {
		this.ultimoFrame = ultimoFrame;
		this.unidadeSelecionada = unidadeSelecionada;
		initComponents();
	}
	
	private void initComponents() {
		setTitle("Cash Bank - Editar Unidade");
		setSize(550, 550);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		painel = new JPanel();
		painel.setPreferredSize(new Dimension(550, 550));
		painel.setLayout(null);
		add(painel);

		codLabel = new JLabel("Cod:");
		codLabel.setBounds(80, 132, 46, 14);
		painel.add(codLabel);

		codField = new LimitadoTextField(LimitadoTextField.SOMENTENUMEROS, 5);
		codField.setBounds(80, 150, 89, 20);
		painel.add(codField);

		senhaLabel = new JLabel("Senha:");
		senhaLabel.setBounds(179, 132, 46, 14);
		painel.add(senhaLabel);

		senhaField = new JPasswordField();
		senhaField.setBounds(179, 150, 101, 20);
		painel.add(senhaField);

		cnpjLabel = new JLabel("CNPJ:");
		cnpjLabel.setBounds(290, 132, 46, 14);
		painel.add(cnpjLabel);

		cnpjField = new JTextField();
		cnpjField.setBounds(290, 150, 156, 20);
		painel.add(cnpjField);

		nomeLabel = new JLabel("Nome da Unidade:");
		nomeLabel.setBounds(80, 175, 158, 14);
		painel.add(nomeLabel);

		nomeField = new JTextField();
		nomeField.setBounds(80, 191, 366, 20);
		painel.add(nomeField);

		enderecoLabel = new JLabel("Endereço:");
		enderecoLabel.setBounds(80, 215, 72, 14);
		painel.add(enderecoLabel);

		enderecoField = new JTextField();
		enderecoField.setBounds(80, 230, 299, 20);
		painel.add(enderecoField);

		numeroLabel = new JLabel("Nº:");
		numeroLabel.setBounds(394, 215, 46, 14);
		painel.add(numeroLabel);

		numeroField = new LimitadoTextField(LimitadoTextField.SOMENTENUMEROS, 4);
		numeroField.setBounds(389, 230, 57, 20);
		painel.add(numeroField);

		cepLabel = new JLabel("CEP:");
		cepLabel.setBounds(80, 254, 72, 14);
		painel.add(cepLabel);

		cepField = new LimitadoTextField(LimitadoTextField.SOMENTENUMEROS, 8);
		cepField.setBounds(81, 269, 174, 20);
		painel.add(cepField);

		cidadeLabel = new JLabel("Cidade:");
		cidadeLabel.setBounds(271, 254, 46, 14);
		painel.add(cidadeLabel);

		cidadeField = new JTextField();
		cidadeField.setBounds(271, 269, 175, 20);
		painel.add(cidadeField);

		telefoneLabel = new JLabel("Telefone:");
		telefoneLabel.setBounds(271, 294, 55, 14);
		painel.add(telefoneLabel);

		telefoneField = new LimitadoTextField(LimitadoTextField.SOMENTENUMEROS, 11);
		telefoneField.setBounds(271, 311, 175, 20);
		painel.add(telefoneField);

		estadoLabel = new JLabel("Estado:");
		estadoLabel.setBounds(80, 294, 46, 14);
		painel.add(estadoLabel);

		estadosComboBox = new EstadosComboBox();
		estadosComboBox.setBounds(80, 311, 181, 20);
		painel.add(estadosComboBox);

		botaoLimpar = new JButton(("LIMPAR"));
		botaoLimpar.setBounds(80, 375, 89, 23);
		botaoLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		painel.add(botaoLimpar);

		botaoSalvar = new JButton(("SALVAR"));
		botaoSalvar.setBounds(357, 375, 89, 23);
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
	
	private void limpar() {
		nomeField.setText("");
		senhaField.setText("");
		enderecoField.setText("");
		cepField.setText("");
		cidadeField.setText("");
		estadosComboBox.setSelectedIndex(0);
		telefoneField.setText("");
		numeroField.setText("");
		codField.setText("");
		cnpjField.setText("");
	}
	
	protected void carregarValoresUnidade() {
		if(unidadeSelecionada != null) {
			nomeField.setText(unidadeSelecionada.getNome());
			senhaField.setText(unidadeSelecionada.getSenha());
			enderecoField.setText(unidadeSelecionada.getEndereco());
			cepField.setText(unidadeSelecionada.getCep());
			cidadeField.setText(unidadeSelecionada.getCidade());
			estadosComboBox.setSelectedItem(unidadeSelecionada.getEstado());
			telefoneField.setText(unidadeSelecionada.getTelefone());
			numeroField.setText(Integer.toString(unidadeSelecionada.getNumeroResidencial()));
			codField.setText(Integer.toString(unidadeSelecionada.getCodigo()));
			cnpjField.setText(unidadeSelecionada.getCnpj());
		}
	}
	
	private void salvar() {
		try {
			if(unidadeSelecionada != null) {
				int codAntigo = unidadeSelecionada.getCodigo();
				Unidade unidade = unidadeSelecionada;
				unidade.setId(unidadeSelecionada.getId());
				unidade.setNome(nomeField.getText());
				unidade.setSenha(senhaField.getText());
				unidade.setCep(cepField.getText());
				unidade.setEndereco(enderecoField.getText());
				unidade.setCidade(cidadeField.getText());
				unidade.setTelefone(telefoneField.getText());
				unidade.setEstado((String)estadosComboBox.getSelectedItem());
				unidade.setNumeroResidencial(Integer.parseInt(numeroField.getText()));
				unidade.setCodigo(Integer.parseInt(codField.getText()));
				unidade.setCnpj(cnpjField.getText());
				
				services.getUnidadeService().update(unidade);
				
				if(((Unidade)services.getUnidadeService().findById(unidade.getId())).getCodigo() != codAntigo) {
					System.out.println(((Unidade)services.getUnidadeService().findById(unidade.getId())).getCodigo() + "|"+ codAntigo + "|" + unidade.getCodigo());
					services.atualizarUsuariosLista();
					services.atualizarContasLista();
					for(Entity x : services.getUsuariosLista()) {
						if(x instanceof Cliente && ((Cliente)x).getUnidade().getCodigo() == codAntigo) {
							Cliente cliente = (Cliente)x;
							cliente.getUnidade().setCodigo(unidade.getCodigo());
							services.getClienteService().update(cliente);
						}else if(x instanceof Funcionario && ((Funcionario)x).getUnidade().getCodigo() == codAntigo) {
							Funcionario funcionario = (Funcionario)x;
							funcionario.getUnidade().setCodigo(unidade.getCodigo());
							services.getFuncionarioService().update(funcionario);
						}
					}
					for(Entity x : services.getContasLista()) {
						if(x instanceof ContaSimples && ((ContaSimples)x).getUnidade().getCodigo() == codAntigo) {
							ContaSimples conta = (ContaSimples)x;
							conta.getUnidade().setCodigo(unidade.getCodigo());
							services.getContaSimplesService().update(conta);
						}else if(x instanceof ContaPoupanca && ((ContaPoupanca)x).getUnidade().getCodigo() == codAntigo) {
							ContaPoupanca conta = (ContaPoupanca)x;
							conta.getUnidade().setCodigo(unidade.getCodigo());
							services.getContaPoupancaService().update(conta);
						}
					}
				}
				
				JOptionPane.showMessageDialog(null, "Operação realizada");
				voltarUltimaTela();
			}else {
				throw new ApplicationException("EditarUnidadeForm", "salvar", "Necessário selecionar uma unidade");
			}
		}catch (ApplicationException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	protected void voltarUltimaTela() {
        if(ultimoFrame != null) {
        	ListarUnidadesForm ultimaForm = (ListarUnidadesForm)ultimoFrame;
        	ultimaForm.atualizarTabela();
        	ultimaForm.setVisible(true);
        	dispose();
        }
	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new EditarUnidadeForm().setVisible(true);
			}
		});
	}
}
