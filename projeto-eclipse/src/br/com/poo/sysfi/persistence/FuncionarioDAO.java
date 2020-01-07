package br.com.poo.sysfi.persistence;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

import br.com.poo.sysfi.exception.ApplicationException;
import br.com.poo.sysfi.model.Funcionario;
import br.com.poo.sysfi.model.Entity;

public class FuncionarioDAO extends DAO{
	@Override 
	public Entity findById(String id) throws ApplicationException {
        try {
            DB db = super.connection();
            DBCollection coll = db.getCollection(Funcionario.FUNCIONARIO);
            DBCursor cursor = coll.find();
            
            while(cursor.hasNext()) {
               DBObject dbObject = cursor.next();
               if(dbObject.get(Funcionario.ID).toString().equals(id)){
            	   
                   Funcionario funcionario = new Funcionario(
                		                         ""+dbObject.get(Funcionario.NOME),
                		   						 ""+dbObject.get(Funcionario.SENHA),
                		    					 ""+dbObject.get(Funcionario.ENDERECO), 
                		   						 ""+dbObject.get(Funcionario.CEP),
                		   						 ""+dbObject.get(Funcionario.CIDADE), 
                		   						 ""+dbObject.get(Funcionario.ESTADO), 
                		   						 ""+dbObject.get(Funcionario.TELEFONE),
                		   						 (int)dbObject.get(Funcionario.NUMERORESIDENCIAL),
                		   						 ""+dbObject.get(Funcionario.LOGIN),
                		   						 ""+dbObject.get(Funcionario.CPF),
                		   						 ""+dbObject.get(Funcionario.RG),
                		   						 ""+dbObject.get(Funcionario.ESTADOCIVIL),
                		   						 ""+dbObject.get(Funcionario.DATANASCIMENTO),
                		   						 (int)dbObject.get(Funcionario.UNIDADECODIGO),
                		   						 ""+dbObject.get(Funcionario.MATRICULA));
                   
                   funcionario.setId(dbObject.get(Funcionario.ID).toString());
                   return funcionario;
               }
            }
            
        } catch (ApplicationException ex) {
            throw new ApplicationException("FuncionarioDAO", "findById", ex.getMessage());
        }
		return null;
	}

	@Override
	public List<Entity> findAll() throws ApplicationException {
		List<Entity> lista = new ArrayList<Entity>();
        try {
            DB db = super.connection();
            DBCollection coll = db.getCollection(Funcionario.FUNCIONARIO);
            DBCursor cursor = coll.find();
            
            while(cursor.hasNext()) {
               DBObject dbObject = cursor.next();
               
               Funcionario funcionario = new Funcionario(
            		                         ""+dbObject.get(Funcionario.NOME),
            		                         ""+dbObject.get(Funcionario.SENHA),
            		                         ""+dbObject.get(Funcionario.ENDERECO), 
					 						 ""+dbObject.get(Funcionario.CEP),
					 						 ""+dbObject.get(Funcionario.CIDADE), 
					 						 ""+dbObject.get(Funcionario.ESTADO), 
					 						 ""+dbObject.get(Funcionario.TELEFONE),
					 						 (int)dbObject.get(Funcionario.NUMERORESIDENCIAL),
					 						 ""+dbObject.get(Funcionario.LOGIN),
					 						 ""+dbObject.get(Funcionario.CPF),
					 						 ""+dbObject.get(Funcionario.RG),
					 						 ""+dbObject.get(Funcionario.ESTADOCIVIL),
					 						 ""+dbObject.get(Funcionario.DATANASCIMENTO),
					 						 (int)dbObject.get(Funcionario.UNIDADECODIGO),
					 						 ""+dbObject.get(Funcionario.MATRICULA));
               
               funcionario.setId(dbObject.get(Funcionario.ID).toString());
               lista.add(funcionario);
            }
            
        } catch (ApplicationException ex) {
            throw new ApplicationException("FuncionarioDAO", "findAll", ex.getMessage());
        }
        return lista;
	}

	@Override
	public void delete(Entity entity) throws ApplicationException {
		try {
			Funcionario funcionario = (Funcionario)entity; 
            DB db = super.connection();
            DBCollection coll = db.getCollection(Funcionario.FUNCIONARIO);
            if(coll== null){
                coll = db.createCollection(Funcionario.FUNCIONARIO, new BasicDBObject());
            }           
            
            BasicDBObject doc = new BasicDBObject();
            doc.append(Funcionario.NOME, funcionario.getNome());
            doc.append(Funcionario.SENHA, funcionario.getSenha());
            doc.append(Funcionario.ENDERECO, funcionario.getEndereco());
            doc.append(Funcionario.CEP, funcionario.getCep());
            doc.append(Funcionario.CIDADE, funcionario.getCidade());
            doc.append(Funcionario.ESTADO, funcionario.getEstado());
            doc.append(Funcionario.TELEFONE, funcionario.getTelefone());
            doc.append(Funcionario.NUMERORESIDENCIAL, funcionario.getNumeroResidencial());
            doc.append(Funcionario.LOGIN, funcionario.getLogin());
            doc.append(Funcionario.CPF, funcionario.getCpf());
            doc.append(Funcionario.RG, funcionario.getRg());
            doc.append(Funcionario.ESTADOCIVIL, funcionario.getEstadoCivil());
            doc.append(Funcionario.DATANASCIMENTO, funcionario.getDataNascimento().toString());
            doc.append(Funcionario.UNIDADECODIGO, funcionario.getUnidade().getCodigo());
            doc.append(Funcionario.MATRICULA, funcionario.getMatricula());
            
            doc = (BasicDBObject)coll.findAndRemove(doc);
        } catch (ApplicationException ex) {
            throw new ApplicationException("FuncionarioDAO", "delete", ex.getMessage());
        }		
	}

	@Override
	public void update(Entity entity) throws ApplicationException {
		try {
			Funcionario funcionarioOriginal = (Funcionario)findById(entity.getId());
			Funcionario funcionarioAtualizado = (Funcionario)entity;
			
			if(funcionarioOriginal == null) {
				throw new ApplicationException("FuncionarioDAO", "update", "A atualização não foi feita, funcionario não encontrado.");
			}
			
            DB db = super.connection();
            DBCollection coll = db.getCollection(Funcionario.FUNCIONARIO);
            if(coll== null){
                coll = db.createCollection(Funcionario.FUNCIONARIO, new BasicDBObject());
            }            
            
            BasicDBObject docOriginal = new BasicDBObject();
            docOriginal.append(Funcionario.NOME, funcionarioOriginal.getNome());
            docOriginal.append(Funcionario.SENHA, funcionarioOriginal.getSenha());
            docOriginal.append(Funcionario.ENDERECO, funcionarioOriginal.getEndereco());
            docOriginal.append(Funcionario.CEP, funcionarioOriginal.getCep());
            docOriginal.append(Funcionario.CIDADE, funcionarioOriginal.getCidade());
            docOriginal.append(Funcionario.ESTADO, funcionarioOriginal.getEstado());
            docOriginal.append(Funcionario.TELEFONE, funcionarioOriginal.getTelefone());
            docOriginal.append(Funcionario.NUMERORESIDENCIAL, funcionarioOriginal.getNumeroResidencial());
            docOriginal.append(Funcionario.LOGIN, funcionarioOriginal.getLogin());
            docOriginal.append(Funcionario.CPF, funcionarioOriginal.getCpf());
            docOriginal.append(Funcionario.RG, funcionarioOriginal.getRg());
            docOriginal.append(Funcionario.ESTADOCIVIL, funcionarioOriginal.getEstadoCivil());
            docOriginal.append(Funcionario.DATANASCIMENTO, funcionarioOriginal.getDataNascimento().toString());
            docOriginal.append(Funcionario.UNIDADECODIGO, funcionarioOriginal.getUnidade().getCodigo());
            docOriginal.append(Funcionario.MATRICULA, funcionarioOriginal.getMatricula());

            BasicDBObject docAtualizado = new BasicDBObject();
            docAtualizado.append(Funcionario.NOME, funcionarioAtualizado.getNome());
            docAtualizado.append(Funcionario.SENHA, funcionarioAtualizado.getSenha());
            docAtualizado.append(Funcionario.ENDERECO, funcionarioAtualizado.getEndereco());
            docAtualizado.append(Funcionario.CEP, funcionarioAtualizado.getCep());
            docAtualizado.append(Funcionario.CIDADE, funcionarioAtualizado.getCidade());
            docAtualizado.append(Funcionario.ESTADO, funcionarioAtualizado.getEstado());
            docAtualizado.append(Funcionario.TELEFONE, funcionarioAtualizado.getTelefone());
            docAtualizado.append(Funcionario.NUMERORESIDENCIAL, funcionarioAtualizado.getNumeroResidencial());
            docAtualizado.append(Funcionario.LOGIN, funcionarioAtualizado.getLogin());
            docAtualizado.append(Funcionario.CPF, funcionarioAtualizado.getCpf());
            docAtualizado.append(Funcionario.RG, funcionarioAtualizado.getRg());
            docAtualizado.append(Funcionario.ESTADOCIVIL, funcionarioAtualizado.getEstadoCivil());
            docAtualizado.append(Funcionario.DATANASCIMENTO, funcionarioAtualizado.getDataNascimento().toString());
            docAtualizado.append(Funcionario.UNIDADECODIGO, funcionarioAtualizado.getUnidade().getCodigo());
            docAtualizado.append(Funcionario.MATRICULA, funcionarioAtualizado.getMatricula());

            @SuppressWarnings("unused")
			WriteResult result = coll.update(docOriginal, docAtualizado);
        } catch (ApplicationException ex) {
            throw new ApplicationException("FuncionarioDAO", "update", ex.getMessage());
        }
	}

	@Override
	public void save(Entity entity) throws ApplicationException {
		try {
			Funcionario funcionario = (Funcionario)entity; 
            DB db = super.connection();
            DBCollection coll = db.getCollection(Funcionario.FUNCIONARIO);
            if(coll== null){
                coll = db.createCollection(Funcionario.FUNCIONARIO, new BasicDBObject());
            }       
            
            BasicDBObject doc = new BasicDBObject();
            doc.append(Funcionario.NOME, funcionario.getNome());
            doc.append(Funcionario.SENHA, funcionario.getSenha());
            doc.append(Funcionario.ENDERECO, funcionario.getEndereco());
            doc.append(Funcionario.CEP, funcionario.getCep());
            doc.append(Funcionario.CIDADE, funcionario.getCidade());
            doc.append(Funcionario.ESTADO, funcionario.getEstado());
            doc.append(Funcionario.TELEFONE, funcionario.getTelefone());
            doc.append(Funcionario.NUMERORESIDENCIAL, funcionario.getNumeroResidencial());
            doc.append(Funcionario.LOGIN, funcionario.getLogin());
            doc.append(Funcionario.CPF, funcionario.getCpf());
            doc.append(Funcionario.RG, funcionario.getRg());
            doc.append(Funcionario.ESTADOCIVIL, funcionario.getEstadoCivil());
            doc.append(Funcionario.DATANASCIMENTO, funcionario.getDataNascimento().toString());
            doc.append(Funcionario.UNIDADECODIGO, funcionario.getUnidade().getCodigo());
            doc.append(Funcionario.MATRICULA, funcionario.getMatricula());
            
            coll.insert(doc);
        } catch (ApplicationException ex) {
            throw new ApplicationException("FuncionarioDAO", "save", ex.getMessage());
        }		
	}
}
