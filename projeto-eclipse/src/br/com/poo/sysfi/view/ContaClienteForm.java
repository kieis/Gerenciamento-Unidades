package br.com.poo.sysfi.view;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.com.poo.sysfi.model.ContaCorrente;
import br.com.poo.sysfi.model.ContaPoupanca;
import br.com.poo.sysfi.model.ContaSimples;

@SuppressWarnings("serial")
public class ContaClienteForm extends EntityFrame {
	private JFrame ultimoFrame;
	private JPanel painel;
	private JLabel numeroLabel;
	private JLabel tipoLabel;
	private JLabel rendimentoLabel;
	private JLabel fundoTela;
	private JButton botaoSaldo;
	private JButton botaoDeposito;
	private JButton botaoSaque;
	private JButton botaoEncerrar;
	
	private ContaCorrente contaLogada = null;

	public ContaClienteForm() {
		initComponents();
	}
	
	public ContaClienteForm(JFrame ultimoFrame, ContaCorrente contaLogada) {
		this.ultimoFrame = ultimoFrame;
		initComponents();
		atualizarInformacoesConta(contaLogada);
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

		numeroLabel = new JLabel("");
		numeroLabel.setBounds(40, 61, 100, 20);
		painel.add(numeroLabel);
		
		tipoLabel = new JLabel("");
		tipoLabel.setBounds(40, 81, 100, 20);
		painel.add(tipoLabel);
		
		rendimentoLabel = new JLabel("");
		rendimentoLabel.setBounds(40, 101, 200, 20);
		painel.add(rendimentoLabel);

		botaoSaldo = new JButton(("Saldo"));
		botaoSaldo.setBounds(40, 183, 205, 50);
		botaoSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	saldo(contaLogada);
            }
        });
		painel.add(botaoSaldo);

		botaoDeposito = new JButton(("Depósito"));
		botaoDeposito.setBounds(270, 183, 205, 50);
		botaoDeposito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	deposito(contaLogada);
            }
        });
		painel.add(botaoDeposito);

		botaoSaque = new JButton(("Saque"));
		botaoSaque.setBounds(40, 281, 205, 50);
		botaoSaque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	saque(contaLogada);
            }
        });
		painel.add(botaoSaque);

		botaoEncerrar = new JButton(("Voltar"));
		botaoEncerrar.setBounds(270, 281, 205, 50);
		botaoEncerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	voltarUltimaTela();
            }
        });
		painel.add(botaoEncerrar);

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
	
	private void saldo(ContaCorrente conta) {
		if(conta != null) {
			JOptionPane.showMessageDialog(null, "R$: " + contaLogada.getSaldo(), "Saldo", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void saque(ContaCorrente conta) {
		if(conta != null) {
			new SaqueForm(this, conta);
			setVisible(false);
		}
	}
	
	private void deposito(ContaCorrente conta) {
		if(conta != null) {
			new DepositoForm(this, conta);
			setVisible(false);
		}
	}
	
	private void voltarUltimaTela() {
        if(ultimoFrame != null) {
        	ListarContasForm ultimaForm = (ListarContasForm)ultimoFrame;
        	ultimaForm.atualizarTabela();
        	ultimaForm.setVisible(true);
        	dispose();
        }
	}
	
	private void atualizarInformacoesConta(ContaCorrente contaLogada) {
		setContaLogada(contaLogada);
		if(contaLogada != null) {
			numeroLabel.setText("Numero: " + contaLogada.getNumero() / 10 + "-" + contaLogada.getUltimoDigito());
			if(contaLogada instanceof ContaSimples) {
				tipoLabel.setText("Conta Simples");
			}else if(contaLogada instanceof ContaPoupanca) {
				ContaPoupanca x = (ContaPoupanca)contaLogada;
				tipoLabel.setText("Conta Poupanca");
				rendimentoLabel.setText("Rendimento de " + (x.getRendimento() * 100.0) + "% ao dia");
			}
		}
	}
	
	protected void setContaLogada(ContaCorrente conta) {
		this.contaLogada = conta;
	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ContaClienteForm().setVisible(true);
			}
		});
	}
}