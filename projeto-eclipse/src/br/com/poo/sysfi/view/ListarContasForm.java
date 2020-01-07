package br.com.poo.sysfi.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import br.com.poo.sysfi.model.ContaSimples;
import br.com.poo.sysfi.model.ContaPoupanca;
import br.com.poo.sysfi.model.Cliente;
import br.com.poo.sysfi.model.ContaCorrente;
import br.com.poo.sysfi.model.Entity;

@SuppressWarnings("serial")
public class ListarContasForm extends EntityFrame {
	private JPanel painel;
	private JPanel painel2;
	private JLabel usuarioLabel;
	private JLabel fundoTela;
	private JButton botaoEntrar;
	private JButton botaoAtualizar;
	private JTable tabela;
	private JScrollPane tabelaScroll;
	
	private int selectedIndex = -1;
	
	private Cliente clienteLogado = null;
	
	public ListarContasForm(){
		initComponents();
		atualizarTabela();
	}
	
	public ListarContasForm(Cliente clienteLogado){
		initComponents();
		atualizarInformacoesCliente(clienteLogado);
		atualizarTabela();
		aplicarRendimentoDiario();
	}
	
	private void initComponents() {
		setTitle("Cash Bank - Conta");
		setSize(550, 550);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		painel = new JPanel();
		painel.setPreferredSize(new Dimension(400, 400));
		painel.setLayout(null);
		add(painel);

		usuarioLabel = new JLabel("Olá, ");
		usuarioLabel.setBounds(15, 87, 200, 14);
		painel.add(usuarioLabel);
		
		painel2 = new JPanel();
		painel2.setLayout(new BorderLayout());
		painel2.setBounds(15, 113, 514, 275);
		painel.add(painel2);
		
		tabela = new JTable();
		DefaultTableModel tabelaModelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabela.setBounds(0, 20, 800, 300);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.setModel(tabelaModelo);
		tabela.setFillsViewportHeight(false);
		painel2.add(tabela);
		
		tabelaScroll = new JScrollPane(tabela);
		painel2.add(tabelaScroll, BorderLayout.CENTER);
		
		ListSelectionModel modeloSelecao = tabela.getSelectionModel();
		modeloSelecao.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedIndex = tabela.getSelectedRow();
			}
		});
		
		botaoEntrar = new JButton("Entrar");
		botaoEntrar.setBounds(439, 399, 89, 23);
		botaoEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logar();
            }
        });
		painel.add(botaoEntrar);
		
		botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.setBounds(340, 399, 89, 23);
		botaoAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	atualizarTabela();
            }
        });
		painel.add(botaoAtualizar);

		fundoTela = new JLabel("");
		fundoTela.setIcon(new ImageIcon(getClass().getResource("/br/com/poo/sysfi/img/imgFundo.jpeg")));
		fundoTela.setBounds(0, 0, 550, 550);
		painel.add(fundoTela);
		
		addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	if (JOptionPane.showConfirmDialog(getParent(), 
		    			"Tem certeza que quer continuar?", "Encerrar a sessão", 
			            JOptionPane.YES_NO_OPTION,
			            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
			            System.exit(0);
			    }
		    }
		});
	}
	
	private boolean validarSelecao() {
		if(selectedIndex < 0) {
			JOptionPane.showMessageDialog(null, "Nenhuma unidade foi selecionada", "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	private void logar() {
		if(validarSelecao() && clienteLogado != null) {
			try {
				ContaCorrente contaSelecionada = (ContaCorrente)clienteLogado.contasLista.get(selectedIndex);
				if(contaSelecionada.getSenha().equals(new AutentificacaoField("Autentificação", "Informe a senha:", 20).getText())){
					abrirContaJanela(contaSelecionada);
				}else {
					throw new ApplicationException("ListarCostasForm", "logar", "Senha incorreta");
				}
			}catch(ApplicationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Operação não realizada", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void abrirContaJanela(ContaCorrente conta) {
		if(conta != null) {
			new ContaClienteForm(this, conta).setVisible(true);
			setVisible(false);
		}
	}
	
	private void aplicarRendimentoDiario() {
		if(clienteLogado != null) {
			for(ContaCorrente x : clienteLogado.contasLista) {
				if(x != null && x instanceof ContaPoupanca) {
					try {
						ContaPoupanca y = (ContaPoupanca)x;
						y.aplicarRendimentoDiario();
						if(y.isRendimentoAplicado()) {
							services.getContaPoupancaService().update(y);
						}
					}catch(ApplicationException e) { }
				}
			}
			atualizarTabela();
		}
	}
	
	private void limparTabela(DefaultTableModel model) {	
		if(model.getColumnCount() == 0) {
			model.addColumn("Numero");
			model.addColumn("Dono CPF");
			model.addColumn("Saldo");
			model.addColumn("Tipo");
		}

		while(model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}
	
	protected void atualizarTabela() {
		selectedIndex = -1;
		if(clienteLogado != null) {
			DefaultTableModel model = (DefaultTableModel)tabela.getModel();
			limparTabela(model);

			clienteLogado.contasLista.clear();
			if(services != null) {
				services.atualizarContasLista();
				
				for(Entity x : services.getContasLista()) {
					ContaCorrente p = (ContaCorrente)x;
					if(p.getDonoCpf().equals(clienteLogado.getCpf())) {
						clienteLogado.contasLista.add(p);
				        Vector<Object> linha = new Vector<Object>();
				        linha.addElement(p.getNumero() / 10 + "-" + p.getUltimoDigito());
				        linha.addElement(p.getDonoCpf());
				        linha.addElement(p.getSaldo());
				        if(p instanceof ContaSimples) {
				        	linha.addElement("Simples");
				        }else if(p instanceof ContaPoupanca) {
				        	linha.addElement("Poupanca");
				        }
				        model.addRow(linha);
					}
				}
			}
		}
	}
	
	private void atualizarInformacoesCliente(Cliente clienteLogado) {
		this.clienteLogado = clienteLogado;
		if(clienteLogado != null) {
			usuarioLabel.setText("Olá, " + clienteLogado.getNome());
		}
	}
	
	public static void main(String[]args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ListarContasForm().setVisible(true);
			}
		});
	}
}