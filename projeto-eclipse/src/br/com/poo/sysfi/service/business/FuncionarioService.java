package br.com.poo.sysfi.service.business;

import java.util.List;

import br.com.poo.sysfi.exception.ApplicationException;
import br.com.poo.sysfi.model.Funcionario;
import br.com.poo.sysfi.model.Entity;
import br.com.poo.sysfi.persistence.FuncionarioDAO;
import br.com.poo.sysfi.persistence.DAO;
import br.com.poo.sysfi.service.BusinessService;

public class FuncionarioService implements BusinessService{
	private DAO funcionarioDAO = new FuncionarioDAO();
	private List<Entity> funcionarioLista;

	public void delete(Entity object) throws ApplicationException {
		Funcionario funcionario = (Funcionario)object;
		
		if(funcionario == null){
			throw new ApplicationException("FuncionarioService", "save", "Informe os dados do funcionario");
		}else if(funcionario.getNome() != null && funcionario.getNome().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe o nome do funcionario");
		}else if(funcionario.getSenha() != null && !funcionario.getSenha().trim().equals("") && funcionario.getSenha().length() < 6) {
			throw new ApplicationException("FuncionarioService", "save", "A senha informada é fraca, necessário mais de 6 caracteres");
		}else if(funcionario.getSenha() != null && funcionario.getSenha().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe a senha do funcionario");
		}else if(funcionario.getEndereco() != null && funcionario.getEndereco().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe o endereço do funcionario");
		}else if(funcionario.getCep() != null && funcionario.getCep().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe o cep do funcionario");
		}else if(funcionario.getCidade() != null && funcionario.getCidade().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe a cidade do funcionario");
		}else if(funcionario.getEstado() != null && funcionario.getEstado().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe o estado do funcionario");
		}else if(funcionario.getTelefone() != null && funcionario.getTelefone().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe o telefone do funcionario");
		}else if(funcionario.getNumeroResidencial() <= 0) {
			throw new ApplicationException("FuncionarioService", "save", "Informe o número residencial do funcionario");
		}else if(funcionario.getLogin() != null && funcionario.getLogin().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe o login do funcionario");
		}else if(funcionario.getCpf() != null && funcionario.getCpf().trim().equals("") ){
			throw new ApplicationException("FuncionarioService", "save", "Informe o CPF do funcionario");
		}else if(funcionario.getRg() != null && funcionario.getRg().trim().equals("") ){
			throw new ApplicationException("FuncionarioService", "save", "Informe o RG do funcionario");
		}else if(funcionario.getEstadoCivil() != null && funcionario.getEstadoCivil().trim().equals("") ){
			throw new ApplicationException("FuncionarioService", "save", "Informe o estado civil do funcionario");
		}else if(funcionario.getDataNascimento() != null && !funcionario.getDataNascimento().isDataValida()){
			throw new ApplicationException("FuncionarioService", "save", "Informe a data de nascimento do funcionario");
		}else if(funcionario.getUnidade() != null && funcionario.getUnidade().getCodigo() <= 0){
			throw new ApplicationException("FuncionarioService", "save", "Informe a unidade do funcionario");
		}else if(funcionario.getNome()!=null && funcionario.getNome().trim().equals("") ){
			throw new ApplicationException("FuncionarioService", "save", "Informe o nome do funcionario");
		}
		funcionarioDAO.delete(object);
	}

	public void save(Entity object) throws ApplicationException {
		Funcionario funcionario = (Funcionario)object;
		funcionarioLista = findAll();
		
		if(funcionario == null){
			throw new ApplicationException("FuncionarioService", "save", "Informe os dados do funcionario");
		}else if(funcionario.getNome() != null && funcionario.getNome().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe o nome do funcionario");
		}else if(funcionario.getSenha() != null && !funcionario.getSenha().trim().equals("") && funcionario.getSenha().length() < 6) {
			throw new ApplicationException("FuncionarioService", "save", "A senha informada é fraca, necessário mais de 6 caracteres");
		}else if(funcionario.getSenha() != null && funcionario.getSenha().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe a senha do funcionario");
		}else if(funcionario.getEndereco() != null && funcionario.getEndereco().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe o endereço do funcionario");
		}else if(funcionario.getCep() != null && funcionario.getCep().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe o cep do funcionario");
		}else if(funcionario.getCidade() != null && funcionario.getCidade().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe a cidade do funcionario");
		}else if(funcionario.getEstado() != null && funcionario.getEstado().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe o estado do funcionario");
		}else if(funcionario.getTelefone() != null && funcionario.getTelefone().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe o telefone do funcionario");
		}else if(funcionario.getNumeroResidencial() <= 0) {
			throw new ApplicationException("FuncionarioService", "save", "Informe o número residencial do funcionario");
		}else if(funcionario.getLogin() != null && funcionario.getLogin().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe o login do funcionario");
		}else if(funcionario.getCpf() != null && funcionario.getCpf().trim().equals("") ){
			throw new ApplicationException("FuncionarioService", "save", "Informe o CPF do funcionario");
		}else if(funcionario.getRg() != null && funcionario.getRg().trim().equals("") ){
			throw new ApplicationException("FuncionarioService", "save", "Informe o RG do funcionario");
		}else if(funcionario.getEstadoCivil() != null && funcionario.getEstadoCivil().trim().equals("") ){
			throw new ApplicationException("FuncionarioService", "save", "Informe o estado civil do funcionario");
		}else if(funcionario.getDataNascimento() != null && !funcionario.getDataNascimento().isDataValida()){
			throw new ApplicationException("FuncionarioService", "save", "Informe a data de nascimento do funcionario");
		}else if(funcionario.getUnidade() != null && funcionario.getUnidade().getCodigo() <= 0){
			throw new ApplicationException("FuncionarioService", "save", "Informe a unidade do funcionario");
		}else if(funcionario.getNome()!=null && funcionario.getNome().trim().equals("") ){
			throw new ApplicationException("FuncionarioService", "save", "Informe o nome do funcionario");
		}else if(funcionario.getLogin() != null && !funcionario.getLogin().trim().equals("")) {
			for(Entity x : funcionarioLista) {
				Funcionario p = (Funcionario)x;
				if(p.getLogin().equals(funcionario.getLogin())) {
					throw new ApplicationException("FuncionarioService", "save", "O login informado já está sendo utilizado");
				}
			}
		}
		
		funcionarioDAO.save(funcionario);		
	}

	public void update(Entity object) throws ApplicationException {
		Funcionario funcionario = (Funcionario)object;
		funcionarioLista = findAll();
		
		if(funcionario == null){
			throw new ApplicationException("FuncionarioService", "save", "Informe os dados do funcionario");
		}else if(funcionario.getNome() != null && funcionario.getNome().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe o nome do funcionario");
		}else if(funcionario.getSenha() != null && !funcionario.getSenha().trim().equals("") && funcionario.getSenha().length() < 6) {
			throw new ApplicationException("FuncionarioService", "save", "A senha informada é fraca, necessário mais de 6 caracteres");
		}else if(funcionario.getSenha() != null && funcionario.getSenha().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe a senha do funcionario");
		}else if(funcionario.getEndereco() != null && funcionario.getEndereco().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe o endereço do funcionario");
		}else if(funcionario.getCep() != null && funcionario.getCep().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe o cep do funcionario");
		}else if(funcionario.getCidade() != null && funcionario.getCidade().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe a cidade do funcionario");
		}else if(funcionario.getEstado() != null && funcionario.getEstado().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe o estado do funcionario");
		}else if(funcionario.getTelefone() != null && funcionario.getTelefone().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe o telefone do funcionario");
		}else if(funcionario.getNumeroResidencial() <= 0) {
			throw new ApplicationException("FuncionarioService", "save", "Informe o número residencial do funcionario");
		}else if(funcionario.getLogin() != null && funcionario.getLogin().trim().equals("")) {
			throw new ApplicationException("FuncionarioService", "save", "Informe o login do funcionario");
		}else if(funcionario.getCpf() != null && funcionario.getCpf().trim().equals("") ){
			throw new ApplicationException("FuncionarioService", "save", "Informe o CPF do funcionario");
		}else if(funcionario.getRg() != null && funcionario.getRg().trim().equals("") ){
			throw new ApplicationException("FuncionarioService", "save", "Informe o RG do funcionario");
		}else if(funcionario.getEstadoCivil() != null && funcionario.getEstadoCivil().trim().equals("") ){
			throw new ApplicationException("FuncionarioService", "save", "Informe o estado civil do funcionario");
		}else if(funcionario.getDataNascimento() != null && !funcionario.getDataNascimento().isDataValida()){
			throw new ApplicationException("FuncionarioService", "save", "Informe a data de nascimento do funcionario");
		}else if(funcionario.getUnidade() != null && funcionario.getUnidade().getCodigo() <= 0){
			throw new ApplicationException("FuncionarioService", "save", "Informe a unidade do funcionario");
		}else if(funcionario.getNome()!=null && funcionario.getNome().trim().equals("") ){
			throw new ApplicationException("FuncionarioService", "save", "Informe o nome do funcionario");
		}else if(funcionario.getLogin() != null && !funcionario.getLogin().trim().equals("")) {
			for(Entity x : funcionarioLista) {
				Funcionario p = (Funcionario)x;
				if(p.getLogin().equals(funcionario.getLogin()) && !p.getId().equals(funcionario.getId())) {
					throw new ApplicationException("FuncionarioService", "save", "O login informado já está sendo utilizado");
				}
			}
		}
		funcionarioDAO.update(object);
	}

	public Entity findById(String id) throws ApplicationException {
		if(id.equals("") || id == null) {
			throw new ApplicationException("FuncionarioService", "findById", "Object Id inválido.");
		}
		return funcionarioDAO.findById(id);
	}

	public List<Entity> findAll() throws ApplicationException {
		return funcionarioDAO.findAll();
	}
}
