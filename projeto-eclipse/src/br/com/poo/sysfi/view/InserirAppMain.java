package br.com.poo.sysfi.view;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.poo.sysfi.exception.ApplicationException;
import br.com.poo.sysfi.model.Cliente;
import br.com.poo.sysfi.model.ContaCorrente;
import br.com.poo.sysfi.model.ContaPoupanca;
import br.com.poo.sysfi.model.ContaSimples;
import br.com.poo.sysfi.model.Entity;
import br.com.poo.sysfi.model.Funcionario;
import br.com.poo.sysfi.model.Unidade;
import br.com.poo.sysfi.service.BusinessService;
import br.com.poo.sysfi.service.business.ClienteService;
import br.com.poo.sysfi.service.business.ContaPoupancaService;
import br.com.poo.sysfi.service.business.ContaSimplesService;
import br.com.poo.sysfi.service.business.FuncionarioService;
import br.com.poo.sysfi.service.business.UnidadeService;

public class InserirAppMain {

	public static void main(String[] args) {
		BusinessService service = new ClienteService();
		BusinessService funcionarioService = new FuncionarioService();
		BusinessService contaSimplesService = new ContaSimplesService();
		BusinessService contaPoupancaService = new ContaPoupancaService();
		BusinessService unidadeService = new UnidadeService();

		try {
			Unidade unidadePrimaria = new Unidade("Unidade 1", "unidade123", "Rua dos Pioneiros", 
					                              "85601355", "Francisco Beltrão", "Paraná (PR)", 
					                              "4628607052", 512, 63741, "75.803.935/0001-05");
			unidadeService.save(unidadePrimaria);
		
			Funcionario ff = new Funcionario("Edson Hugo Carlos Rocha", "funcionario123", 
					                         "Rua Edmundo Prado Maia", "49032020", 
					                         "Maringá", "Paraná (PR)", "61994166762", 214, 
					                         "funcionario1", "10205942172", "458393599",
					                         "Solteiro", "06/12/1985", 63742, "091220191");
		
			funcionarioService.save(ff);
			

		} catch (ApplicationException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
