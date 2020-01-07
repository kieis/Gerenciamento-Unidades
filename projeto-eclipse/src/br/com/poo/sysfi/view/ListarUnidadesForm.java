package br.com.poo.sysfi.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import br.com.poo.sysfi.model.Entity;
import br.com.poo.sysfi.model.Funcionario;
import br.com.poo.sysfi.model.Unidade;

@SuppressWarnings("serial")
public class ListarUnidadesForm extends EntityFrame {
	private JTable tabela;
	private JScrollPane tabelaScroll;
	private JPanel painel;
	private JPanel painel2;
	private JLabel matriculaLabel;
	private JLabel usuarioLabel;
	private JLabel fundoTela;
	private JButton botaoAtualizar;
	private JButton botaoEditar;
	private JButton botaoExcluir;
	private JButton botaoNovo;
	private JButton botaoEntrar;
	
	private int selectedIndex = -1;
	
	private Funcionario funcionarioLogado;
	private List<Entity> unidadeLista = new ArrayList<Entity>();
	
	public ListarUnidadesForm() {
		initComponents();
	}
	
	public ListarUnidadesForm(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
		initComponents();
		atualizarInformacoesFuncionario(funcionarioLogado);
		atualizarTabela();
	}
	
	private void initComponents() {
		setTitle("Cash Bank - Unidades");
		setSize(550, 550);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		painel = new JPanel();
		painel.setPreferredSize(new Dimension(400, 400));
		painel.setLayout(null);
		add(painel);

		matriculaLabel = new JLabel("Matricula:");
		matriculaLabel.setBounds(10, 69, 200, 14);
		painel.add(matriculaLabel);

		usuarioLabel = new JLabel("Usuário:");
		usuarioLabel.setBounds(10, 87, 200, 14);
		painel.add(usuarioLabel);

		painel2 = new JPanel();
		painel2.setLayout(new BorderLayout());
		painel2.setBounds(10, 113, 514, 275);
		painel.add(painel2);

		botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.setBounds(10, 399, 89, 23);
		botaoAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	atualizarTabela();
            }
        });
		painel.add(botaoAtualizar);

		botaoEditar = new JButton("Editar");
		botaoEditar.setBounds(119, 399, 89, 23);
		botaoEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	if(validarAcesso()) {
            		editar();
            	} 	
            }
        });
		painel.add(botaoEditar);

		botaoExcluir = new JButton("Excluir");
		botaoExcluir.setBounds(226, 399, 89, 23);
		botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	if(validarAcesso()) {
            		excluir();
            	}
            }
        });
		painel.add(botaoExcluir);

		botaoNovo = new JButton("Novo");
		botaoNovo.setBounds(330, 399, 89, 23);
		botaoNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	novo();
            }
        });
		painel.add(botaoNovo);

		botaoEntrar = new JButton("Entrar");
		botaoEntrar.setBounds(435, 399, 89, 23);
		botaoEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	if(validarAcesso()) {
            		entrar();
            	}
            }
        });
		painel.add(botaoEntrar);

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
	
	private boolean validarAcesso() {
		if(validarSelecao()) {
	    	if(((Unidade)unidadeLista.get(selectedIndex)).getSenha().equals(new AutentificacaoField("Acesso Restrito", "Informe a senha: ", 20).getText())) {
	    		return true;
	    	}else {
	    		JOptionPane.showMessageDialog(null, "Senha incorreta", "Erro", JOptionPane.ERROR_MESSAGE);
	    	}
		}
    	return false;
	}

	private void editar() {
		if(validarSelecao()) {
			EditarUnidadeForm editarForm = new EditarUnidadeForm(this, (Unidade)unidadeLista.get(selectedIndex));
			editarForm.carregarValoresUnidade();
			editarForm.setVisible(true);
			setVisible(false);
		}
	}
	
	private void excluir() {
		if(validarSelecao()) {
			try {
				services.getUnidadeService().delete(unidadeLista.get(selectedIndex));
				unidadeLista.remove(selectedIndex);
				DefaultTableModel model = (DefaultTableModel)tabela.getModel();
				model.removeRow(selectedIndex);
			}catch(ApplicationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Operação não realizada", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void novo() {
		if((funcionarioLogado.getUnidade().getSenha().trim().equals(""))){
    		JOptionPane.showMessageDialog(null, "Não existe uma unidade associada ao funcionario", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
    	if (funcionarioLogado.getUnidade().getSenha().equals(
    					new AutentificacaoField("Acesso Restrito", "Informe a senha: ", 20).getText())) {
    		new CriarUnidadeForm(this).setVisible(true);;
    		setVisible(false);
    	}else {
    		JOptionPane.showMessageDialog(null, "Senha incorreta", "Erro", JOptionPane.ERROR_MESSAGE);
    	}
	}
	
	private void entrar() {
		if(validarSelecao()) {
			new AdministrativoUnidadeForm(this, (Unidade)unidadeLista.get(selectedIndex)).setVisible(true);;
			setVisible(false);
		}
	}
	
	private void limparTabela(DefaultTableModel model) {	
		if(model.getColumnCount() == 0) {
			model.addColumn("Nome");
			model.addColumn("Código");
			model.addColumn("Cnpj");
		}

		while(model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}
	
	protected void atualizarTabela() {
		selectedIndex = -1;
		if(!validarServices()) {
			return;
		}
		
		DefaultTableModel model = (DefaultTableModel)tabela.getModel();
		limparTabela(model);

		unidadeLista.clear();
		services.atualizarUnidadeLista();
		unidadeLista.addAll(services.getUnidadeLista());
		
		for(Entity x : unidadeLista) {
			Unidade p = (Unidade)x;
			if(p != null) {
				if(funcionarioLogado != null && funcionarioLogado.getUnidade().getCodigo() == p.getCodigo()) {
					funcionarioLogado.setUnidade(p);
				}
		        Vector<Object> linha = new Vector<Object>();
		        linha.addElement(p.getNome());
		        linha.addElement(p.getCodigo());
		        linha.addElement(p.getCnpj());
		        model.addRow(linha);
			}  
		}
	}
	
	private void atualizarInformacoesFuncionario(Funcionario funcionarioLogado) {
		if(funcionarioLogado != null) {
			matriculaLabel.setText("Matricula: " + funcionarioLogado.getMatricula());
			usuarioLabel.setText("Usuário: " + funcionarioLogado.getLogin());
			for(Entity x: services.getUnidadeLista()) {
				Unidade unidade = (Unidade)x;
				if(unidade.getCodigo() == funcionarioLogado.getUnidade().getCodigo()) {
					funcionarioLogado.setUnidade(unidade);
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ListarUnidadesForm().setVisible(true);
			}
		});
	}
}