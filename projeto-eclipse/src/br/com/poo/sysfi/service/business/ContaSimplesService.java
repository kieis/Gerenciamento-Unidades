package br.com.poo.sysfi.service.business;

import java.util.List;

import br.com.poo.sysfi.exception.ApplicationException;
import br.com.poo.sysfi.model.ContaSimples;
import br.com.poo.sysfi.model.Entity;
import br.com.poo.sysfi.persistence.ContaSimplesDAO;
import br.com.poo.sysfi.persistence.DAO;
import br.com.poo.sysfi.service.BusinessService;

public class ContaSimplesService implements BusinessService{
	private DAO contaSimplesDAO = new ContaSimplesDAO();
	private List<Entity> contaSimplesLista;

	public void delete(Entity object) throws ApplicationException {
		ContaSimples contaSimples = (ContaSimples)object;

		if(contaSimples == null){
			throw new ApplicationException("ContaSimplesService", "save", "Informe os dados da Conta");
		}else if(contaSimples.getNumero() < ContaSimples.numMinimo || contaSimples.getNumero() > ContaSimples.numMaximo) {
			throw new ApplicationException("ContaSimplesService", "save", "Numero de conta inválido");
		}else if(contaSimples.getDonoCpf() != null && contaSimples.getDonoCpf().trim().equals("")) {
			throw new ApplicationException("ContaSimplesService", "save", "Informe o cpf do dono");
		}else if(contaSimples.getUnidade() != null && Integer.toString(contaSimples.getUnidade().getCodigo()).length() != 5) {
			throw new ApplicationException("ContaSimplesService", "save", "Informe uma unidade válida");
		}else if(contaSimples.getSenha() != null && contaSimples.getSenha() != null && contaSimples.getSenha().trim().equals("")) {
			throw new ApplicationException("ContaSimplesService", "save", "Informe a senha da conta");
		}
		contaSimplesDAO.delete(object);
	}

	public void save(Entity object) throws ApplicationException {
		ContaSimples contaSimples = (ContaSimples)object;
		contaSimplesLista = findAll();

		if(contaSimples == null){
			throw new ApplicationException("ContaSimplesService", "save", "Informe os dados da Conta");
		}else if(contaSimples.getNumero() < ContaSimples.numMinimo || contaSimples.getNumero() > ContaSimples.numMaximo) {
			throw new ApplicationException("ContaSimplesService", "save", "Numero de conta inválido, necessário 6 digitos");
		}else if(contaSimples.getDonoCpf() != null && contaSimples.getDonoCpf().trim().equals("")) {
			throw new ApplicationException("ContaSimplesService", "save", "Informe o cpf do dono");
		}else if(contaSimples.getSaldo() < 0.0) {
			throw new ApplicationException("ContaSimplesService", "save", "Não é possível criar uma conta com saldo negativo");
		}else if(contaSimples.getUnidade() != null && Integer.toString(contaSimples.getUnidade().getCodigo()).length() != 5) {
			throw new ApplicationException("ContaSimplesService", "save", "Informe uma unidade válida");
		}else if(contaSimples.getSenha() != null && !contaSimples.getSenha().trim().equals("") && contaSimples.getSenha().length() < 6) {
			throw new ApplicationException("ContaSimplesService", "save", "A senha informada é fraca, necessário mais de 6 caracteres");
		}else if(contaSimples.getSenha() != null && contaSimples.getSenha() != null && contaSimples.getSenha().trim().equals("")) {
			throw new ApplicationException("ContaSimplesService", "save", "Informe a senha da conta");
		}else if(contaSimples.getNumero() > ContaSimples.numMinimo && contaSimples.getNumero() < ContaSimples.numMaximo) {
			for(Entity x : contaSimplesLista) {
				ContaSimples p = (ContaSimples)x;
				if(p.getNumero() == contaSimples.getNumero()) {
					throw new ApplicationException("ContaSimplesService", "save", "O número de conta informado já está sendo utilizado");
				}
			}
		}
		contaSimplesDAO.save(contaSimples);		
	}

	public void update(Entity object) throws ApplicationException {
		ContaSimples contaSimples = (ContaSimples)object;
		contaSimplesLista = findAll();
		
		if(contaSimples == null){
			throw new ApplicationException("ContaSimplesService", "save", "Informe os dados da Conta");
		}else if(contaSimples.getNumero() < ContaSimples.numMinimo || contaSimples.getNumero() > ContaSimples.numMaximo) {
			throw new ApplicationException("ContaSimplesService", "save", "Numero de conta inválido");
		}else if(contaSimples.getDonoCpf() != null && contaSimples.getDonoCpf().trim().equals("")) {
			throw new ApplicationException("ContaSimplesService", "save", "Informe o cpf do dono");
		}else if(contaSimples.getSaldo() < 0.0) {
			throw new ApplicationException("ContaSimplesService", "save", "Não é possível atualizar uma conta com saldo negativo");
		}else if(contaSimples.getUnidade() != null && Integer.toString(contaSimples.getUnidade().getCodigo()).length() != 5) {
			throw new ApplicationException("ContaSimplesService", "save", "Informe uma unidade válida");
		}else if(contaSimples.getSenha() != null && contaSimples.getSenha() != null && contaSimples.getSenha().trim().equals("")) {
			throw new ApplicationException("ContaSimplesService", "save", "Informe a senha da conta");
		}else if(contaSimples.getNumero() > ContaSimples.numMinimo && contaSimples.getNumero() < ContaSimples.numMaximo) {
			for(Entity x : contaSimplesLista) {
				ContaSimples p = (ContaSimples)x;
				if(p.getNumero() == contaSimples.getNumero() && !p.getId().equals(contaSimples.getId())) {
					throw new ApplicationException("ContaSimplesService", "save", "O número de conta informado já está sendo utilizado");
				}
			}
		}
		contaSimplesDAO.update(object);
	}

	public Entity findById(String id) throws ApplicationException {
		if(id.equals("") || id == null) {
			throw new ApplicationException("ContaSimplesService", "findById", "Object Id inválido.");
		}
		return contaSimplesDAO.findById(id);
	}

	public List<Entity> findAll() throws ApplicationException {
		return contaSimplesDAO.findAll();
	}
}
