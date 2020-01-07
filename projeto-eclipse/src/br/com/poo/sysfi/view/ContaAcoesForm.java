package br.com.poo.sysfi.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.poo.sysfi.model.ContaCorrente;
import br.com.poo.sysfi.model.ContaPoupanca;
import br.com.poo.sysfi.model.ContaSimples;
import br.com.poo.sysfi.service.BusinessService;

@SuppressWarnings("serial")
public class ContaAcoesForm extends EntityFrame {
	protected JFrame ultimoFrame;
	private JPanel painel;
	protected JTextField valorField;
	private JLabel disponivelLabel;
	private JLabel valorLabel;
	private JLabel fundoTela;
	private JButton botaoVoltar;
	private JButton botaoCorrigir;
	protected JButton botaoConfirmar;

	protected ContaCorrente contaLogada;
	protected BusinessService contaService;

	public ContaAcoesForm() {
		initComponents();
	}
	
	public ContaAcoesForm(JFrame ultimoFrame, ContaCorrente contaLogada) {
		this.ultimoFrame = ultimoFrame;
		initComponents();
		atualizarInformacoesConta(contaLogada);
		atualizarInformacoesServico();
	}
	
	private void initComponents() {
		setTitle("Cash Bank - Ações");
		setSize(550, 550);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		painel = new JPanel();
		painel.setPreferredSize(new Dimension(400, 400));
		painel.setLayout(null);
		add(painel);

		valorField = new LimitadoTextField(LimitadoTextField.SOMENTENUMEROS, -1);
		valorField.setBounds(102, 207, 336, 33);
		painel.add(valorField);

		disponivelLabel = new JLabel("Disponível: ");
		disponivelLabel.setBounds(101, 166, 200, 14);
		painel.add(disponivelLabel);
		
		valorLabel = new JLabel("Valor:");
		valorLabel.setBounds(101, 186, 200, 14);
		painel.add(valorLabel);

		botaoVoltar = new JButton("Voltar");
		botaoVoltar.setBounds(222, 299, 96, 33);
		botaoVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				voltarUltimaTela();
			}
		});
		painel.add(botaoVoltar);

		botaoCorrigir = new JButton("Corrigir");
		botaoCorrigir.setBounds(102, 299, 96, 33);
		botaoCorrigir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		painel.add(botaoCorrigir);

		botaoConfirmar = new JButton("Confirmar");
		botaoConfirmar.setBounds(342, 299, 96, 33);
		painel.add(botaoConfirmar);

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
	
	private void limparCampos() {
		valorField.setText("");
	}
	
	protected void voltarUltimaTela() {
        if(ultimoFrame != null) {
        	ContaClienteForm ultimaForm = (ContaClienteForm)ultimoFrame;
        	ultimaForm.setContaLogada(contaLogada);
        	ultimaForm.setVisible(true);
        	dispose();
        }
	}
	
	protected void atualizarInformacoesConta(ContaCorrente contaLogada) {
		this.contaLogada = contaLogada;
		if(contaLogada != null) {
			disponivelLabel.setText("Disponível: " + contaLogada.getSaldo() + " R$");
		}
	}
	
	private void atualizarInformacoesServico() {
		if(services != null && contaLogada != null && contaLogada instanceof ContaSimples) {
			contaService = services.getContaSimplesService();
		}else if(services != null && contaLogada != null && contaLogada instanceof ContaPoupanca){
			contaService = services.getContaPoupancaService();
		}
	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ContaAcoesForm().setVisible(true);
			}
		});
	}
}