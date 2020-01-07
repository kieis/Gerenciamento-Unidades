package br.com.poo.sysfi.service.business;

import java.util.List;

import br.com.poo.sysfi.exception.ApplicationException;
import br.com.poo.sysfi.model.Cliente;
import br.com.poo.sysfi.model.Entity;
import br.com.poo.sysfi.persistence.ClienteDAO;
import br.com.poo.sysfi.persistence.DAO;
import br.com.poo.sysfi.service.BusinessService;

public class ClienteService implements BusinessService{	
	private DAO clienteDAO = new ClienteDAO();
	private List<Entity> clienteLista;

	public void delete(Entity object) throws ApplicationException {
		Cliente cliente = (Cliente)object;

		if(cliente == null){
			throw new ApplicationException("ClienteService", "save", "Informe os dados do cliente");
		}else if(cliente.getNome() != null && cliente.getNome().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe o nome do cliente");
		}else if(cliente.getSenha() != null && cliente.getSenha().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe a senha do cliente");
		}else if(cliente.getEndereco() != null && cliente.getEndereco().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe o endereço do cliente");
		}else if(cliente.getCep() != null && cliente.getCep().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe o cep do cliente");
		}else if(cliente.getCidade() != null && cliente.getCidade().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe a cidade do cliente");
		}else if(cliente.getEstado() != null && cliente.getEstado().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe o estado do cliente");
		}else if(cliente.getTelefone() != null && cliente.getTelefone().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe o telefone do cliente");
		}else if(cliente.getNumeroResidencial() <= 0) {
			throw new ApplicationException("ClienteService", "save", "Informe o número residencial do cliente");
		}else if(cliente.getLogin() != null && cliente.getLogin().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe o login do cliente");
		}else if(cliente.getCpf() != null && cliente.getCpf().trim().equals("") ){
			throw new ApplicationException("ClienteService", "save", "Informe o CPF do cliente");
		}else if(cliente.getRg() != null && cliente.getRg().trim().equals("") ){
			throw new ApplicationException("ClienteService", "save", "Informe o RG do cliente");
		}else if(cliente.getEstadoCivil() != null && cliente.getEstadoCivil().trim().equals("") ){
			throw new ApplicationException("ClienteService", "save", "Informe o estado civil do cliente");
		}else if(cliente.getDataNascimento() != null && !cliente.getDataNascimento().isDataValida()){
			throw new ApplicationException("ClienteService", "save", "Informe a data de nascimento do cliente");
		}else if(cliente.getUnidade() != null && cliente.getUnidade().getCodigo() <= 0){
			throw new ApplicationException("ClienteService", "save", "Informe a unidade do cliente");
		}else if(cliente.getNome()!=null && cliente.getNome().trim().equals("") ){
			throw new ApplicationException("ClienteService", "save", "Informe o nome do cliente");
		}
		clienteDAO.delete(cliente);
	}

	public void save(Entity object) throws ApplicationException {
		Cliente cliente = (Cliente)object;
		clienteLista = findAll();
		
		if(cliente == null){
			throw new ApplicationException("ClienteService", "save", "Informe os dados do cliente");
		}else if(cliente.getNome() != null && cliente.getNome().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe o nome do cliente");
		}else if(cliente.getSenha() != null && !cliente.getSenha().trim().equals("") && cliente.getSenha().length() < 6) {
			throw new ApplicationException("ClienteService", "save", "A senha informada é fraca, necessário mais de 6 caracteres");
		}else if(cliente.getSenha() != null && cliente.getSenha().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe a senha do cliente");
		}else if(cliente.getEndereco() != null && cliente.getEndereco().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe o endereço do cliente");
		}else if(cliente.getCep() != null && cliente.getCep().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe o cep do cliente");
		}else if(cliente.getCidade() != null && cliente.getCidade().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe a cidade do cliente");
		}else if(cliente.getEstado() != null && cliente.getEstado().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe o estado do cliente");
		}else if(cliente.getTelefone() != null && cliente.getTelefone().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe o telefone do cliente");
		}else if(cliente.getNumeroResidencial() <= 0) {
			throw new ApplicationException("ClienteService", "save", "Informe o número residencial do cliente");
		}else if(cliente.getLogin() != null && cliente.getLogin().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe o login do cliente");
		}else if(cliente.getCpf() != null && cliente.getCpf().trim().equals("") ){
			throw new ApplicationException("ClienteService", "save", "Informe o CPF do cliente");
		}else if(cliente.getRg() != null && cliente.getRg().trim().equals("") ){
			throw new ApplicationException("ClienteService", "save", "Informe o RG do cliente");
		}else if(cliente.getEstadoCivil() != null && cliente.getEstadoCivil().trim().equals("") ){
			throw new ApplicationException("ClienteService", "save", "Informe o estado civil do cliente");
		}else if(cliente.getDataNascimento() != null && !cliente.getDataNascimento().isDataValida()){
			throw new ApplicationException("ClienteService", "save", "Informe a data de nascimento do cliente");
		}else if(cliente.getUnidade() != null && cliente.getUnidade().getCodigo() <= 0){
			throw new ApplicationException("ClienteService", "save", "Informe a unidade do cliente");
		}else if(cliente.getNome()!=null && cliente.getNome().trim().equals("") ){
			throw new ApplicationException("ClienteService", "save", "Informe o nome do cliente");
		}else if(cliente.getLogin() != null && !cliente.getLogin().trim().equals("") || !cliente.getCpf().trim().equals("")) {
			for(Entity x : clienteLista) {
				Cliente p = (Cliente)x;
				if(p.getLogin().equals(cliente.getLogin())) {
					throw new ApplicationException("ClienteService", "save", "O login informado já está sendo utilizado");
				}else if(p.getCpf().equals(cliente.getCpf())) {
					throw new ApplicationException("ClienteService", "save", "Já existe um cliente com esse cpf");
				}
			}
		}
		clienteDAO.save(cliente);		
	}

	public void update(Entity object) throws ApplicationException {
		Cliente cliente = (Cliente)object;
		clienteLista = findAll();
		
		if(cliente == null){
			throw new ApplicationException("ClienteService", "save", "Informe os dados do cliente");
		}else if(cliente.getNome() != null && cliente.getNome().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe o nome do cliente");
		}else if(cliente.getSenha() != null && !cliente.getSenha().trim().equals("") && cliente.getSenha().length() < 6) {
			throw new ApplicationException("ClienteService", "save", "A senha informada é fraca, necessário mais de 6 caracteres");
		}else if(cliente.getSenha() != null && cliente.getSenha().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe a senha do cliente");
		}else if(cliente.getEndereco() != null && cliente.getEndereco().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe o endereço do cliente");
		}else if(cliente.getCep() != null && cliente.getCep().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe o cep do cliente");
		}else if(cliente.getCidade() != null && cliente.getCidade().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe a cidade do cliente");
		}else if(cliente.getEstado() != null && cliente.getEstado().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe o estado do cliente");
		}else if(cliente.getTelefone() != null && cliente.getTelefone().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe o telefone do cliente");
		}else if(cliente.getNumeroResidencial() <= 0) {
			throw new ApplicationException("ClienteService", "save", "Informe o número residencial do cliente");
		}else if(cliente.getLogin() != null && cliente.getLogin().trim().equals("")) {
			throw new ApplicationException("ClienteService", "save", "Informe o login do cliente");
		}else if(cliente.getCpf() != null && cliente.getCpf().trim().equals("") ){
			throw new ApplicationException("ClienteService", "save", "Informe o CPF do cliente");
		}else if(cliente.getRg() != null && cliente.getRg().trim().equals("") ){
			throw new ApplicationException("ClienteService", "save", "Informe o RG do cliente");
		}else if(cliente.getEstadoCivil() != null && cliente.getEstadoCivil().trim().equals("") ){
			throw new ApplicationException("ClienteService", "save", "Informe o estado civil do cliente");
		}else if(cliente.getDataNascimento() != null && !cliente.getDataNascimento().isDataValida()){
			throw new ApplicationException("ClienteService", "save", "Informe a data de nascimento do cliente");
		}else if(cliente.getUnidade() != null && cliente.getUnidade().getCodigo() <= 0){
			throw new ApplicationException("ClienteService", "save", "Informe a unidade do cliente");
		}else if(cliente.getNome()!=null && cliente.getNome().trim().equals("") ){
			throw new ApplicationException("ClienteService", "save", "Informe o nome do cliente");
		}else if(cliente.getLogin() != null && !cliente.getLogin().trim().equals("") || !cliente.getCpf().trim().equals("")) {
			for(Entity x : clienteLista) {
				Cliente p = (Cliente)x;
				if(p.getLogin().equals(cliente.getLogin()) && !p.getId().equals(cliente.getId())) {
					throw new ApplicationException("ClienteService", "save", "O login informado já está sendo utilizado");
				}else if(p.getCpf().equals(cliente.getCpf()) && !p.getId().equals(cliente.getId())) {
					throw new ApplicationException("ClienteService", "save", "Já existe um cliente com esse cpf");
				}
			}
		}
		clienteDAO.update(object);
	}

	public Entity findById(String id) throws ApplicationException {
		if(id.equals("") || id == null) {
			throw new ApplicationException("ClienteService", "findById", "Id inválido");
		}
		return clienteDAO.findById(id);
	}

	public List<Entity> findAll() throws ApplicationException {
		return clienteDAO.findAll();
	}
}
