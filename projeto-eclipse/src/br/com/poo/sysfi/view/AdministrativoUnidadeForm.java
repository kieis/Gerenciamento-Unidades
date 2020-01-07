package br.com.poo.sysfi.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import br.com.poo.sysfi.exception.ApplicationException;
import br.com.poo.sysfi.model.Cliente;
import br.com.poo.sysfi.model.ContaCorrente;
import br.com.poo.sysfi.model.ContaPoupanca;
import br.com.poo.sysfi.model.ContaSimples;
import br.com.poo.sysfi.model.Entity;
import br.com.poo.sysfi.model.Unidade;

@SuppressWarnings("serial")
public class AdministrativoUnidadeForm extends EntityFrame {
	private final static int SELECIONADOCLIENTE = 0;
	private final static int SELECIONADOCONTA = 1;
	
	private JFrame ultimoFrame;
	private JPanel painel;
	private JPanel painelCliente;
	private JPanel painelConta;
	private JButton botaoGerenciarClientes;
	private JButton botaoGerenciarContas;
	private JButton botaoGerenciarUnidades;
	private JButton botaoAtualizar;
	private JButton botaoEditar;
	private JButton botaoExcluir;
	private JButton botaoNovo;
	private JLabel fundoTela;
	private JTable tabelaCliente;
	private JTable tabelaConta;
	private JScrollPane tabelaScroll;
	private JScrollPane tabelaScroll2;
	
	private int tabelaSelecionada = -1;
	private int selectedIndexCliente = -1;
	private int selectedIndexConta = -1;
	
	private Unidade unidadeSelecionada;
	private List<Entity> clienteLista = new ArrayList<Entity>();
	private List<Entity> contaLista = new ArrayList<Entity>();
	
	public AdministrativoUnidadeForm() {
		initComponents();
	}
	
	public AdministrativoUnidadeForm(JFrame ultimoFrame, Unidade unidadeSelecionada) {
		this.ultimoFrame = ultimoFrame;
		this.unidadeSelecionada = unidadeSelecionada;
		initComponents();
	}
	
	private void initComponents() {
		setTitle("Cash Bank - Administrativo Unidade");
		setSize(550, 550);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		painel = new JPanel();
		painel.setPreferredSize(new Dimension(500, 500));
		painel.setBounds(0, 0, 550, 550);
		painel.setLayout(null);
		add(painel);

		botaoGerenciarClientes = new JButton("Gerir Clientes");
		botaoGerenciarClientes.setBounds(10, 154, 133, 38);
		botaoGerenciarClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizarTabelaClientes();
				painelCliente.setVisible(true);
				painelConta.setVisible(false);
				tabelaSelecionada = SELECIONADOCLIENTE;
			}
		});
		painel.add(botaoGerenciarClientes);

		botaoGerenciarContas = new JButton("Gerir Contas");
		botaoGerenciarContas.setBounds(10, 203, 133, 38);
		botaoGerenciarContas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizarTabelaContas();
				painelCliente.setVisible(false);
				painelConta.setVisible(true);
				tabelaSelecionada = SELECIONADOCONTA;
			}
		});
		painel.add(botaoGerenciarContas);

		botaoGerenciarUnidades = new JButton("Voltar");
		botaoGerenciarUnidades.setBounds(10, 252, 133, 38);
		botaoGerenciarUnidades.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				voltarUltimaTela();
			}
		});
		painel.add(botaoGerenciarUnidades);
		
		botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.setBounds(153, 423, 90, 23);
		botaoAtualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tabelaSelecionada == SELECIONADOCLIENTE) {
					atualizarTabelaClientes();
				}
				if(tabelaSelecionada == SELECIONADOCONTA) {
					atualizarTabelaContas();
				}
			}
		});
		painel.add(botaoAtualizar);

		botaoEditar = new JButton("Editar");
		botaoEditar.setBounds(260, 423, 75, 23);
		botaoEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tabelaSelecionada == SELECIONADOCLIENTE) {
					editarCliente();
				}else if(tabelaSelecionada == SELECIONADOCONTA) {
					editarConta();
				}
			}
		});
		painel.add(botaoEditar);

		botaoExcluir = new JButton("Excluir");
		botaoExcluir.setBounds(355, 423, 75, 23);
		botaoExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tabelaSelecionada == SELECIONADOCLIENTE) {
					excluirCliente();
				}else if(tabelaSelecionada == SELECIONADOCONTA) {
					excluirConta();
				}
			}
		});
		painel.add(botaoExcluir);

		botaoNovo = new JButton("Novo");
		botaoNovo.setBounds(451, 423, 75, 23);
		botaoNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tabelaSelecionada == SELECIONADOCLIENTE) {
					novoCliente();
				}else if(tabelaSelecionada == SELECIONADOCONTA) {
					novaConta();
				}
			}
		});
		painel.add(botaoNovo);

		painelCliente = new JPanel();
		painelCliente.setLayout(new BorderLayout());
		painelCliente.setBounds(153, 35, 373, 364);
		painel.add(painelCliente);
		
		tabelaCliente = new JTable();
		DefaultTableModel tblMd = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabelaCliente.setBounds(0, 20, 800, 300);
		tabelaCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaCliente.setModel(tblMd);
		tabelaCliente.setFillsViewportHeight(false);
		painelCliente.add(tabelaCliente);
		
		tabelaScroll = new JScrollPane(tabelaCliente);
		painelCliente.add(tabelaScroll, BorderLayout.CENTER);
		
		ListSelectionModel md = tabelaCliente.getSelectionModel();
		md.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedIndexCliente = tabelaCliente.getSelectedRow();
			}
		});
		
		painelConta = new JPanel();
		painelConta.setLayout(new BorderLayout());
		painelConta.setBounds(153, 35, 373, 364);
		painel.add(painelConta);
		
		tabelaConta = new JTable();
		DefaultTableModel tblMd1 = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabelaConta.setBounds(0, 20, 800, 300);
		tabelaConta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaConta.setModel(tblMd1);
		tabelaConta.setFillsViewportHeight(false);
		painelConta.add(tabelaConta);
		
		tabelaScroll2 = new JScrollPane(tabelaConta);
		painelConta.add(tabelaScroll2, BorderLayout.CENTER);
		
		ListSelectionModel md1 = tabelaConta.getSelectionModel();
		md1.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedIndexConta = tabelaConta.getSelectedRow();
			}
		});
		
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
	
	private boolean validarSelecao() {
		if(tabelaSelecionada != SELECIONADOCONTA && tabelaSelecionada != SELECIONADOCLIENTE) {
			JOptionPane.showMessageDialog(null, "Nada foi selecionado para gerenciar", "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(tabelaSelecionada == SELECIONADOCONTA && selectedIndexConta < 0) {
			JOptionPane.showMessageDialog(null, "Nenhuma conta foi selecionada", "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(tabelaSelecionada == SELECIONADOCLIENTE && selectedIndexCliente < 0) {
			JOptionPane.showMessageDialog(null, "Nenhum cliente foi selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	private void novaConta() {
		new CriarContaForm(this, unidadeSelecionada).setVisible(true);
		setVisible(false);
	}
	
	private void editarConta() {
		if(validarSelecao()) {
			EditarContaForm editarForm = new EditarContaForm(this, (ContaCorrente)contaLista.get(selectedIndexConta));
			editarForm.carregarValoresCliente();
			editarForm.setVisible(true);
			setVisible(false);
		}
	}
	
	private void excluirConta() {
		if(validarSelecao()) {
			try {
				ContaCorrente conta = (ContaCorrente)contaLista.get(selectedIndexConta);
				if(conta instanceof ContaSimples && services.getContaSimplesService() != null) {
					services.getContaSimplesService().delete(contaLista.get(selectedIndexConta));
					contaLista.remove(selectedIndexConta);
					DefaultTableModel model = (DefaultTableModel)tabelaConta.getModel();
					model.removeRow(selectedIndexConta);
				}else if(conta instanceof ContaPoupanca && services.getContaPoupancaService() != null) {
					services.getContaPoupancaService().delete(contaLista.get(selectedIndexConta));
					contaLista.remove(selectedIndexConta);
					DefaultTableModel model = (DefaultTableModel)tabelaConta.getModel();
					model.removeRow(selectedIndexConta);
				}
			}catch(ApplicationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Operação não realizada", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void editarCliente() {
		if(validarSelecao()) {
			EditarClienteForm editarForm = new EditarClienteForm(this, unidadeSelecionada, (Cliente)clienteLista.get(selectedIndexCliente));
			editarForm.carregarValoresCliente();
			editarForm.setVisible(true);
			setVisible(false);
		}
	}
	
	private void excluirCliente() {
		if(validarSelecao()) {
			try {
				services.atualizarContasLista();
				Cliente cliente = (Cliente)clienteLista.get(selectedIndexCliente);
				for(Entity x : services.getContasLista()) {
					ContaCorrente conta = (ContaCorrente)x;
					if(conta.getDonoCpf().equals(cliente.getCpf())) {
						throw new ApplicationException("AdministrativoUnidadeForm", "excluirCliente", "Existem contas associadas a este cliente");
					}
				}
				if(services.getClienteService() != null) {
					services.getClienteService().delete(cliente);
					clienteLista.remove(selectedIndexCliente);
					DefaultTableModel model = (DefaultTableModel)tabelaCliente.getModel();
					model.removeRow(selectedIndexCliente);
				}
			}catch(ApplicationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Operação não realizada", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void novoCliente() {
		new CriarClienteForm(this, unidadeSelecionada).setVisible(true);
		setVisible(false);
	}
	
	protected void voltarUltimaTela() {
        if(ultimoFrame != null) {
        	ultimoFrame.setVisible(true);
        	dispose();
        }
	}
	
	private void limparTabelaCliente(DefaultTableModel model) {
		if(model.getColumnCount() <= 0) {
			model.addColumn("Nome");
			model.addColumn("CPF");
			model.addColumn("Telefone");
			model.addColumn("Endereço");
		}

		while(model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}
	
	protected void atualizarTabelaClientes() {
		selectedIndexConta = selectedIndexCliente = -1;
		DefaultTableModel model = (DefaultTableModel)tabelaCliente.getModel();
		limparTabelaCliente(model);

		clienteLista.clear();
		try {
			if(services.getClienteService() != null) {
				for(Entity x : services.getClienteService().findAll()) {
					Cliente cliente = (Cliente)x;
					if(cliente.getUnidade().getCodigo() == unidadeSelecionada.getCodigo()) {
						clienteLista.add(cliente);
				        Vector<Object> linha = new Vector<Object>();
				        linha.addElement(cliente.getNome());
				        linha.addElement(cliente.getCpf());
				        linha.addElement(cliente.getTelefone());
				        linha.addElement(cliente.getEndereco());
				        model.addRow(linha);
					}
				}
			}		
		}catch (ApplicationException e) { }
	}
	
	private void limparTabelaContas(DefaultTableModel model) {
		if(model.getColumnCount() <= 0) {
			model.addColumn("Numero");
			model.addColumn("Dono CPF");
			model.addColumn("Saldo");
			model.addColumn("Tipo");
		}

		while(model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}
	
	protected void atualizarTabelaContas() {
		selectedIndexConta = selectedIndexCliente = -1;
		DefaultTableModel model = (DefaultTableModel)tabelaConta.getModel();
		limparTabelaContas(model);

		contaLista.clear();
		services.atualizarContasLista();
		contaLista.addAll(services.getContasLista());
		
		for(Entity x : contaLista) {
			ContaCorrente p = (ContaCorrente)x;
			if(p != null && unidadeSelecionada != null && p.getUnidade().getCodigo() == unidadeSelecionada.getCodigo()) {
		        Vector<Object> linha = new Vector<Object>();
		        linha.addElement(p.getNumero() / 10 + "-" + p.getUltimoDigito());
		        linha.addElement(p.getDonoCpf());
		        linha.addElement(p.getSaldo());
		        
				if(p instanceof ContaSimples) {
					linha.addElement("Conta Simples");
				}else if(p instanceof ContaPoupanca) {
					linha.addElement("Conta Poupança");
				}      
		        model.addRow(linha);
			}  
		}
	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new AdministrativoUnidadeForm().setVisible(true);
			}
		});
	}
}
