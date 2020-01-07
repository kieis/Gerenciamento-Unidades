package br.com.poo.sysfi.service.business;

import java.util.List;

import br.com.poo.sysfi.exception.ApplicationException;
import br.com.poo.sysfi.model.ContaPoupanca;
import br.com.poo.sysfi.model.Entity;
import br.com.poo.sysfi.persistence.ContaPoupancaDAO;
import br.com.poo.sysfi.persistence.DAO;
import br.com.poo.sysfi.service.BusinessService;

public class ContaPoupancaService implements BusinessService{
	private DAO contaPoupancaDAO = new ContaPoupancaDAO();
	private List<Entity> contaPoupancaLista;

	public void delete(Entity object) throws ApplicationException {
		ContaPoupanca contaPoupanca = (ContaPoupanca)object;

		if(contaPoupanca == null){
			throw new ApplicationException("ContaPoupancaService", "save", "Informe os dados da Conta");
		}else if(contaPoupanca.getNumero() < ContaPoupanca.numMinimo || contaPoupanca.getNumero() > ContaPoupanca.numMaximo) {
			throw new ApplicationException("ContaPoupancaService", "save", "Numero de conta inválido, necessário 6 digitos");
		}else if(contaPoupanca.getDonoCpf() != null && contaPoupanca.getDonoCpf().trim().equals("")) {
			throw new ApplicationException("ContaPoupancaService", "save", "Informe o cpf do dono");
		}else if(contaPoupanca.getSenha() != null && contaPoupanca.getSenha() != null && contaPoupanca.getSenha().trim().equals("")) {
			throw new ApplicationException("ContaPoupancaService", "save", "Informe a senha da contaPoupanca");
		}else if(contaPoupanca.getRendimento() < 0.0) {
			throw new ApplicationException("ContaPoupancaService", "save", "Informe o rendimento da conta");
		}else if(contaPoupanca.getSaldo() < 0.0) {
			throw new ApplicationException("ContaPoupancaService", "save", "Informe o saldo da conta");
		}else if(contaPoupanca.getUnidade() != null && Integer.toString(contaPoupanca.getUnidade().getCodigo()).length() != 5) {
			throw new ApplicationException("ContaSimplesService", "save", "Informe uma unidade válida");
		}
		contaPoupancaDAO.delete(object);
	}

	public void save(Entity object) throws ApplicationException {
		ContaPoupanca contaPoupanca = (ContaPoupanca)object;
		contaPoupancaLista = findAll();
		
		if(contaPoupanca == null){
			throw new ApplicationException("ContaPoupancaService", "save", "Informe os dados da Conta");
		}else if(contaPoupanca.getNumero() < ContaPoupanca.numMinimo || contaPoupanca.getNumero() > ContaPoupanca.numMaximo) {
			throw new ApplicationException("ContaPoupancaService", "save", "Numero de conta inválido, necessário 6 digitos");
		}else if(contaPoupanca.getDonoCpf() != null && contaPoupanca.getDonoCpf().trim().equals("")) {
			throw new ApplicationException("ContaPoupancaService", "save", "Informe o cpf do dono");
		}else if(contaPoupanca.getSenha() != null && contaPoupanca.getSenha() != null && contaPoupanca.getSenha().trim().equals("")) {
			throw new ApplicationException("ContaPoupancaService", "save", "Informe a senha da contaPoupanca");
		}else if(contaPoupanca.getRendimento() < 0.0) {
			throw new ApplicationException("ContaPoupancaService", "save", "Informe o rendimento da conta");
		}else if(contaPoupanca.getSaldo() < 0.0) {
			throw new ApplicationException("ContaPoupancaService", "save", "Informe o saldo da conta");
		}else if(contaPoupanca.getUnidade() != null && Integer.toString(contaPoupanca.getUnidade().getCodigo()).length() != 5) {
			throw new ApplicationException("ContaSimplesService", "save", "Informe uma unidade válida");
		}else if(contaPoupanca.getNumero() > ContaPoupanca.numMinimo && contaPoupanca.getNumero() < ContaPoupanca.numMaximo) {
			for(Entity x : contaPoupancaLista) {
				ContaPoupanca p = (ContaPoupanca)x;
				if(p.getNumero() == contaPoupanca.getNumero()) {
					throw new ApplicationException("ContaPoupancaService", "save", "O número de conta informado já está sendo utilizado");
				}
			}
		}
		contaPoupancaDAO.save(contaPoupanca);		
	}

	public void update(Entity object) throws ApplicationException {
		ContaPoupanca contaPoupanca = (ContaPoupanca)object;
		contaPoupancaLista = findAll();
		
		if(contaPoupanca == null){
			throw new ApplicationException("ContaPoupancaService", "save", "Informe os dados da Conta");
		}else if(contaPoupanca.getNumero() < ContaPoupanca.numMinimo || contaPoupanca.getNumero() > ContaPoupanca.numMaximo) {
			throw new ApplicationException("ContaPoupancaService", "save", "Numero de conta inválido, necessário 6 digitos");
		}else if(contaPoupanca.getDonoCpf() != null && contaPoupanca.getDonoCpf().trim().equals("")) {
			throw new ApplicationException("ContaPoupancaService", "save", "Informe o cpf do dono");
		}else if(contaPoupanca.getSenha() != null && contaPoupanca.getSenha() != null && contaPoupanca.getSenha().trim().equals("")) {
			throw new ApplicationException("ContaPoupancaService", "save", "Informe a senha da contaPoupanca");
		}else if(contaPoupanca.getRendimento() < 0.0) {
			throw new ApplicationException("ContaPoupancaService", "save", "Informe o rendimento da conta");
		}else if(contaPoupanca.getSaldo() < 0.0) {
			throw new ApplicationException("ContaPoupancaService", "save", "Informe o saldo da conta");
		}else if(contaPoupanca.getUnidade() != null && Integer.toString(contaPoupanca.getUnidade().getCodigo()).length() != 5) {
			throw new ApplicationException("ContaSimplesService", "save", "Informe uma unidade válida");
		}else if(contaPoupanca.getNumero() > ContaPoupanca.numMinimo && contaPoupanca.getNumero() < ContaPoupanca.numMaximo) {
			for(Entity x : contaPoupancaLista) {
				ContaPoupanca p = (ContaPoupanca)x;
				if(p.getNumero() == contaPoupanca.getNumero() && !p.getId().equals(contaPoupanca.getId())) {
					throw new ApplicationException("ContaPoupancaService", "save", "O número de conta informado já está sendo utilizado");
				}
			}
		}
		contaPoupancaDAO.update(object);
	}

	public Entity findById(String id) throws ApplicationException {
		if(id.equals("") || id == null) {
			throw new ApplicationException("ContaPoupancaService", "findById", "Object Id inválido.");
		}
		return contaPoupancaDAO.findById(id);
	}

	public List<Entity> findAll() throws ApplicationException {
		return contaPoupancaDAO.findAll();
	}
}
