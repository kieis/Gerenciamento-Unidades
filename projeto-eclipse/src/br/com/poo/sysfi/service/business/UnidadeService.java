package br.com.poo.sysfi.service.business;

import java.util.List;

import br.com.poo.sysfi.exception.ApplicationException;
import br.com.poo.sysfi.model.Unidade;
import br.com.poo.sysfi.model.Entity;
import br.com.poo.sysfi.persistence.UnidadeDAO;
import br.com.poo.sysfi.persistence.DAO;
import br.com.poo.sysfi.service.BusinessService;

public class UnidadeService implements BusinessService{
	private DAO unidadeDAO = new UnidadeDAO();
	private List<Entity> unidadeLista;

	public void delete(Entity object) throws ApplicationException {
		Unidade unidade = (Unidade)object;

		if(unidade == null){
			throw new ApplicationException("UnidadeService", "save", "Informe os dados da unidade");
		}else if(unidade.getNome() != null && unidade.getNome().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe o nome da unidade");
		}else if(unidade.getSenha() != null && unidade.getSenha().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe a senha da unidade");
		}else if(unidade.getEndereco() != null && unidade.getEndereco().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe o endereço da unidade");
		}else if(unidade.getCep() != null && unidade.getCep().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe o cep da unidade");
		}else if(unidade.getCidade() != null && unidade.getCidade().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe a cidade da unidade");
		}else if(unidade.getEstado() != null && unidade.getEstado().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe o estado da unidade");
		}else if(unidade.getTelefone() != null && unidade.getTelefone().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe o telefone da unidade");
		}else if(unidade.getCnpj() != null && unidade.getCnpj().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe o cnpj da unidade");
		}
		unidadeDAO.delete(object);
	}

	public void save(Entity object) throws ApplicationException {
		Unidade unidade = (Unidade)object;
		unidadeLista = findAll();
		
		if(unidade == null){
			throw new ApplicationException("UnidadeService", "save", "Informe os dados da unidade");
		}else if(unidade.getNome() != null && unidade.getNome().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe o nome da unidade");
		}else if(unidade.getSenha() != null && !unidade.getSenha().trim().equals("") && unidade.getSenha().length() < 6) {
			throw new ApplicationException("UnidadeService", "save", "A senha informada é fraca, necessário mais de 6 caracteres");
		}else if(unidade.getSenha() != null && unidade.getSenha().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe a senha da unidade");
		}else if(unidade.getEndereco() != null && unidade.getEndereco().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe o endereço da unidade");
		}else if(unidade.getCep() != null && unidade.getCep().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe o cep da unidade");
		}else if(unidade.getCidade() != null && unidade.getCidade().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe a cidade da unidade");
		}else if(unidade.getEstado() != null && unidade.getEstado().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe o estado da unidade");
		}else if(unidade.getTelefone() != null && unidade.getTelefone().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe o telefone da unidade");
		}else if(unidade.getNumeroResidencial() <= 0) {
			throw new ApplicationException("UnidadeService", "save", "Informe o número residencial da unidade");
		}else if(Integer.toString(unidade.getCodigo()).length() != 5) {
			throw new ApplicationException("UnidadeService", "save", "Necessário 5 digitos no código da unidade");
		}else if(unidade.getCnpj() != null && unidade.getCnpj().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe o cnpj da unidade");
		}else if(unidade.getNome() != null && !unidade.getNome().trim().equals("")) {
			for(Entity x : unidadeLista) {
				Unidade p = (Unidade)x;
				if(p.getNome().equals(unidade.getNome())) {
					throw new ApplicationException("UnidadeService", "save", "O nome informado já está sendo utilizado");
				}else if(p.getCodigo() == unidade.getCodigo()) {
					throw new ApplicationException("UnidadeService", "save", "O código informado já está sendo utilizado");
				}
			}
		}
		unidadeDAO.save(unidade);		
	}

	public void update(Entity object) throws ApplicationException {
		Unidade unidade = (Unidade)object;
		unidadeLista = findAll();
		
		if(unidade == null){
			throw new ApplicationException("UnidadeService", "save", "Informe os dados da unidade");
		}else if(unidade.getNome() != null && unidade.getNome().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe o nome da unidade");
		}else if(unidade.getSenha() != null && !unidade.getSenha().trim().equals("") && unidade.getSenha().length() < 6) {
			throw new ApplicationException("UnidadeService", "save", "A senha informada é fraca, necessário mais de 6 caracteres");
		}else if(unidade.getSenha() != null && unidade.getSenha().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe a senha da unidade");
		}else if(unidade.getEndereco() != null && unidade.getEndereco().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe o endereço da unidade");
		}else if(unidade.getCep() != null && unidade.getCep().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe o cep da unidade");
		}else if(unidade.getCidade() != null && unidade.getCidade().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe a cidade da unidade");
		}else if(unidade.getEstado() != null && unidade.getEstado().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe o estado da unidade");
		}else if(unidade.getTelefone() != null && unidade.getTelefone().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe o telefone da unidade");
		}else if(unidade.getNumeroResidencial() <= 0) {
			throw new ApplicationException("UnidadeService", "save", "Informe o número residencial da unidade");
		}else if(Integer.toString(unidade.getCodigo()).length() != 5) {
			throw new ApplicationException("UnidadeService", "save", "Necessário 5 digitos no código da unidade");
		}else if(unidade.getCnpj() != null && unidade.getCnpj().trim().equals("")) {
			throw new ApplicationException("UnidadeService", "save", "Informe o cnpj da unidade");
		}else if(unidade.getNome() != null && !unidade.getNome().trim().equals("")) {
			for(Entity x : unidadeLista) {
				Unidade p = (Unidade)x;
				if(p.getNome().equals(unidade.getNome()) && !p.getId().equals(unidade.getId())) {
					throw new ApplicationException("UnidadeService", "save", "O nome informado já está sendo utilizado");
				}else if(p.getCodigo() == unidade.getCodigo() && !p.getId().equals(unidade.getId())) {
					throw new ApplicationException("UnidadeService", "save", "O código informado já está sendo utilizado");
				}
			}
		}
		unidadeDAO.update(object);
	}

	public Entity findById(String id) throws ApplicationException {
		if(id.equals("") || id == null) {
			throw new ApplicationException("UnidadeService", "findById", "Object Id inválido.");
		}
		return unidadeDAO.findById(id);
	}

	public List<Entity> findAll() throws ApplicationException {
		return unidadeDAO.findAll();
	}
}
