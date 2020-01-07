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
import br.com.poo.sysfi.model.Cliente;
import br.com.poo.sysfi.model.Entity;

public class ClienteDAO extends DAO{
	@Override 
	public Entity findById(String id) throws ApplicationException {
        try {
            DB db = super.connection();
            DBCollection coll = db.getCollection(Cliente.CLIENTE);
            DBCursor cursor = coll.find();
            
            while(cursor.hasNext()) {
               DBObject dbObject = cursor.next();
               if(dbObject.get(Cliente.ID).toString().equals(id)){
            	   
                   Cliente cliente = new Cliente(
                		   				 ""+dbObject.get(Cliente.NOME),
                		   				 ""+dbObject.get(Cliente.SENHA),
                		   				 ""+dbObject.get(Cliente.ENDERECO),
                		   				 ""+dbObject.get(Cliente.CEP), 
                		   				 ""+dbObject.get(Cliente.CIDADE),
				 						 ""+dbObject.get(Cliente.ESTADO), 
				 						 ""+dbObject.get(Cliente.TELEFONE),
				 						 (int)dbObject.get(Cliente.NUMERORESIDENCIAL),
				 						 ""+dbObject.get(Cliente.LOGIN),
				 						 ""+dbObject.get(Cliente.CPF),
				 						 ""+dbObject.get(Cliente.RG),
				 						 ""+dbObject.get(Cliente.ESTADOCIVIL),
				 						 ""+dbObject.get(Cliente.DATANASCIMENTO),
				 						 (int)dbObject.get(Cliente.UNIDADECODIGO));
                   
                   cliente.setId(dbObject.get(Cliente.ID).toString());
                   return cliente;
               }
            }
            
        } catch (ApplicationException ex) {
            throw new ApplicationException("ClienteDAO", "findById", ex.getMessage());
        }
		return null;
	}

	@Override
	public List<Entity> findAll() throws ApplicationException {
		List<Entity> lista = new ArrayList<Entity>();
        try {
            DB db = super.connection();
            DBCollection coll = db.getCollection(Cliente.CLIENTE);
            DBCursor cursor = coll.find();
            
            while(cursor.hasNext()) {
               DBObject dbObject = cursor.next();
               
               Cliente cliente = new Cliente(""+dbObject.get(Cliente.NOME),
            		   						 ""+dbObject.get(Cliente.SENHA),
 				                    		 ""+dbObject.get(Cliente.ENDERECO),
  			                        		 ""+dbObject.get(Cliente.CEP), 
					 						 ""+dbObject.get(Cliente.CIDADE),
					 						 ""+dbObject.get(Cliente.ESTADO), 
					 						 ""+dbObject.get(Cliente.TELEFONE),
					 						 (int)dbObject.get(Cliente.NUMERORESIDENCIAL),
					 						 ""+dbObject.get(Cliente.LOGIN),
					 						 ""+dbObject.get(Cliente.CPF),
					 						 ""+dbObject.get(Cliente.RG),
					 						 ""+dbObject.get(Cliente.ESTADOCIVIL),
					 						 ""+dbObject.get(Cliente.DATANASCIMENTO),
					 						 (int)dbObject.get(Cliente.UNIDADECODIGO));
               
               cliente.setId(dbObject.get(Cliente.ID).toString());
               lista.add(cliente);
            }
            
        } catch (ApplicationException ex) {
            throw new ApplicationException("ClienteDAO", "findAll", ex.getMessage());
        }
        return lista;
	}

	@Override
	public void delete(Entity entity) throws ApplicationException {
		try {
			Cliente cliente = (Cliente)entity; 
            DB db = super.connection();
            DBCollection coll = db.getCollection(Cliente.CLIENTE);
            if(coll== null){
                coll = db.createCollection(Cliente.CLIENTE, new BasicDBObject());
            }           
            
            BasicDBObject doc = new BasicDBObject();
            doc.append(Cliente.NOME, cliente.getNome());
            doc.append(Cliente.SENHA, cliente.getSenha());
            doc.append(Cliente.ENDERECO, cliente.getEndereco());
            doc.append(Cliente.CEP, cliente.getCpf());   
            doc.append(Cliente.CIDADE, cliente.getCidade());
            doc.append(Cliente.ESTADO, cliente.getEstado());
            doc.append(Cliente.TELEFONE, cliente.getTelefone());
            doc.append(Cliente.NUMERORESIDENCIAL, cliente.getNumeroResidencial());
            doc.append(Cliente.LOGIN, cliente.getLogin());
            doc.append(Cliente.CPF, cliente.getCpf());
            doc.append(Cliente.RG, cliente.getRg());
            doc.append(Cliente.ESTADOCIVIL, cliente.getEstadoCivil());
            doc.append(Cliente.DATANASCIMENTO, cliente.getDataNascimento().toString());
            doc.append(Cliente.UNIDADECODIGO, cliente.getUnidade().getCodigo());
            
            doc = (BasicDBObject)coll.findAndRemove(doc);
        } catch (ApplicationException ex) {
            throw new ApplicationException("ClienteDAO", "delete", ex.getMessage());
        }		
	}

	@Override
	public void update(Entity entity) throws ApplicationException {
		try {
			Cliente clienteOriginal = (Cliente)findById(entity.getId());
			Cliente clienteAtualizado = (Cliente)entity;
			
			if(clienteOriginal == null) {
				throw new ApplicationException("ClienteDAO", "update", "A atualização não foi feita, cliente não encontrado.");
			}
			
            DB db = super.connection();
            DBCollection coll = db.getCollection(Cliente.CLIENTE);
            if(coll== null){
                coll = db.createCollection(Cliente.CLIENTE, new BasicDBObject());
            }            
            
            BasicDBObject docOriginal = new BasicDBObject();
            docOriginal.append(Cliente.NOME, clienteOriginal.getNome());
            docOriginal.append(Cliente.SENHA, clienteOriginal.getSenha());
            docOriginal.append(Cliente.ENDERECO, clienteOriginal.getEndereco());
            docOriginal.append(Cliente.CEP, clienteOriginal.getCpf());
            docOriginal.append(Cliente.CIDADE, clienteOriginal.getCidade());
            docOriginal.append(Cliente.ESTADO, clienteOriginal.getEstado());
            docOriginal.append(Cliente.TELEFONE, clienteOriginal.getTelefone());
            docOriginal.append(Cliente.NUMERORESIDENCIAL, clienteOriginal.getNumeroResidencial());
            docOriginal.append(Cliente.LOGIN, clienteOriginal.getLogin());
            docOriginal.append(Cliente.CPF, clienteOriginal.getCpf());
            docOriginal.append(Cliente.RG, clienteOriginal.getRg());
            docOriginal.append(Cliente.ESTADOCIVIL, clienteOriginal.getEstadoCivil());
            docOriginal.append(Cliente.DATANASCIMENTO, clienteOriginal.getDataNascimento().toString());
            docOriginal.append(Cliente.UNIDADECODIGO, clienteOriginal.getUnidade().getCodigo());

            BasicDBObject docAtualizado = new BasicDBObject();
            docAtualizado.append(Cliente.NOME, clienteAtualizado.getNome());
            docAtualizado.append(Cliente.SENHA, clienteAtualizado.getSenha());
            docAtualizado.append(Cliente.ENDERECO, clienteAtualizado.getEndereco());
            docAtualizado.append(Cliente.CEP, clienteAtualizado.getCpf());
            docAtualizado.append(Cliente.CIDADE, clienteAtualizado.getCidade());
            docAtualizado.append(Cliente.ESTADO, clienteAtualizado.getEstado());
            docAtualizado.append(Cliente.TELEFONE, clienteAtualizado.getTelefone());
            docAtualizado.append(Cliente.NUMERORESIDENCIAL, clienteAtualizado.getNumeroResidencial());
            docAtualizado.append(Cliente.LOGIN, clienteAtualizado.getLogin());
            docAtualizado.append(Cliente.CPF, clienteAtualizado.getCpf());
            docAtualizado.append(Cliente.RG, clienteAtualizado.getRg());
            docAtualizado.append(Cliente.ESTADOCIVIL, clienteAtualizado.getEstadoCivil());
            docAtualizado.append(Cliente.DATANASCIMENTO, clienteAtualizado.getDataNascimento().toString());
            docAtualizado.append(Cliente.UNIDADECODIGO, clienteAtualizado.getUnidade().getCodigo());

            @SuppressWarnings("unused")
			WriteResult result = coll.update(docOriginal, docAtualizado);
        } catch (ApplicationException ex) {
            throw new ApplicationException("ClienteDAO", "update", ex.getMessage());
        }
	}

	@Override
	public void save(Entity entity) throws ApplicationException {
		try {
			Cliente cliente = (Cliente)entity; 
            DB db = super.connection();
            DBCollection coll = db.getCollection(Cliente.CLIENTE);
            if(coll== null){
                coll = db.createCollection(Cliente.CLIENTE, new BasicDBObject());
            }       
            
            BasicDBObject doc = new BasicDBObject();
            doc.append(Cliente.NOME, cliente.getNome());
            doc.append(Cliente.SENHA, cliente.getSenha());
            doc.append(Cliente.ENDERECO, cliente.getEndereco());
            doc.append(Cliente.CEP, cliente.getCpf());
            doc.append(Cliente.CIDADE, cliente.getCidade());
            doc.append(Cliente.ESTADO, cliente.getEstado());
            doc.append(Cliente.TELEFONE, cliente.getTelefone());
            doc.append(Cliente.NUMERORESIDENCIAL, cliente.getNumeroResidencial());
            doc.append(Cliente.LOGIN, cliente.getLogin());
            doc.append(Cliente.CPF, cliente.getCpf());
            doc.append(Cliente.RG, cliente.getRg());
            doc.append(Cliente.ESTADOCIVIL, cliente.getEstadoCivil());
            doc.append(Cliente.DATANASCIMENTO, cliente.getDataNascimento().toString());
            doc.append(Cliente.UNIDADECODIGO, cliente.getUnidade().getCodigo());
            
            coll.insert(doc);
        } catch (ApplicationException ex) {
            throw new ApplicationException("ClienteDAO", "save", ex.getMessage());
        }		
	}
}
