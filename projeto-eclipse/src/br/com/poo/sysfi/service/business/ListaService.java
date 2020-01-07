package br.com.poo.sysfi.service.business;

import java.util.ArrayList;
import java.util.List;

import br.com.poo.sysfi.exception.ApplicationException;
import br.com.poo.sysfi.model.Entity;
import br.com.poo.sysfi.service.BusinessService;

public class ListaService {
	private List<Entity> usuariosLista = new ArrayList<Entity>();
	private List<Entity> unidadeLista = new ArrayList<Entity>();
	private List<Entity> contasLista = new ArrayList<Entity>();
	
	private BusinessService clienteService;
	private BusinessService funcionarioService;
	private BusinessService unidadeService;
	private BusinessService contaSimplesService;
	private BusinessService contaPoupancaService;
	
	public ListaService() {
		clienteService = new ClienteService();
		funcionarioService = new FuncionarioService();
		unidadeService = new UnidadeService();
		contaSimplesService = new ContaSimplesService();
		contaPoupancaService = new ContaPoupancaService();
		
		atualizarUsuariosLista();
		atualizarUnidadeLista();
		atualizarContasLista();
	}
	
	public List<Entity> getUsuariosLista() {
		return usuariosLista;
	}

	public List<Entity> getUnidadeLista() {
		return unidadeLista;
	}

	public List<Entity> getContasLista() {
		return contasLista;
	}

	public BusinessService getClienteService() {
		return clienteService;
	}

	public BusinessService getFuncionarioService() {
		return funcionarioService;
	}

	public BusinessService getUnidadeService() {
		return unidadeService;
	}

	public BusinessService getContaSimplesService() {
		return contaSimplesService;
	}

	public BusinessService getContaPoupancaService() {
		return contaPoupancaService;
	}

	public void atualizarUsuariosLista() {
		try {
			usuariosLista.clear();
			usuariosLista.addAll(clienteService.findAll());
			usuariosLista.addAll(funcionarioService.findAll());
		}catch (ApplicationException e) { }
	}
	
	public void atualizarUnidadeLista() {
		try {
			unidadeLista.clear();
			unidadeLista.addAll(unidadeService.findAll());
		}catch (ApplicationException e) { }
	}
	
	public void atualizarContasLista() {
		try {
			contasLista.clear();
			contasLista.addAll(contaSimplesService.findAll());
			contasLista.addAll(contaPoupancaService.findAll());
		}catch (ApplicationException e) { }
	}
}
