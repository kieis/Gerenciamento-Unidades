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
import br.com.poo.sysfi.model.Unidade;
import br.com.poo.sysfi.model.Entity;

public class UnidadeDAO extends DAO{
	public Entity findById(String id) throws ApplicationException {
        try {
            DB db = super.connection();
            DBCollection coll = db.getCollection(Unidade.UNIDADE);
            DBCursor cursor = coll.find();
            
            while(cursor.hasNext()) {
               DBObject dbObject = cursor.next();
               if(dbObject.get(Unidade.ID).toString().equals(id)){

            	   Unidade unidade = new Unidade(
            			                 ""+dbObject.get(Unidade.NOME),
                		   				 ""+dbObject.get(Unidade.SENHA),
                		   				 ""+dbObject.get(Unidade.ENDERECO),
                		   			 	 ""+dbObject.get(Unidade.CEP),
                		   				 ""+dbObject.get(Unidade.CIDADE),
                		   				 ""+dbObject.get(Unidade.ESTADO),
                		   				 ""+dbObject.get(Unidade.TELEFONE),
                		   				 (int)dbObject.get(Unidade.NUMERORESIDENCIAL),
                		   				 (int)dbObject.get(Unidade.CODIGO),
                		   				 ""+dbObject.get(Unidade.CNPJ));
                   
            	   unidade.setId(dbObject.get(Unidade.ID).toString());
                   return unidade;
               }
            }
            
        } catch (ApplicationException ex) {
            throw new ApplicationException("UnidadeDAO", "findById", ex.getMessage());
        }
		return null;
	}

	@Override
	public List<Entity> findAll() throws ApplicationException {
		List<Entity> lista = new ArrayList<Entity>();
        try {
            DB db = super.connection();
            DBCollection coll = db.getCollection(Unidade.UNIDADE);
            DBCursor cursor = coll.find();
            
            while(cursor.hasNext()) {
               DBObject dbObject = cursor.next();
               
        	   Unidade unidade = new Unidade(
		                 ""+dbObject.get(Unidade.NOME),
		   				 ""+dbObject.get(Unidade.SENHA),
		   				 ""+dbObject.get(Unidade.ENDERECO),
		   			 	 ""+dbObject.get(Unidade.CEP),
		   				 ""+dbObject.get(Unidade.CIDADE),
		   				 ""+dbObject.get(Unidade.ESTADO),
		   				 ""+dbObject.get(Unidade.TELEFONE),
		   				 (int)dbObject.get(Unidade.NUMERORESIDENCIAL),
		   				 (int)dbObject.get(Unidade.CODIGO),
		   				 ""+dbObject.get(Unidade.CNPJ));
               
               unidade.setId(dbObject.get(Unidade.ID).toString());
               lista.add(unidade);
            }
            
        } catch (ApplicationException ex) {
            throw new ApplicationException("UnidadeDAO", "findAll", ex.getMessage());
        }
        return lista;
	}

	@Override
	public void delete(Entity entity) throws ApplicationException {
		try {
			Unidade unidade = (Unidade)entity; 
            DB db = super.connection();
            DBCollection coll = db.getCollection(Unidade.UNIDADE);
            if(coll== null){
                coll = db.createCollection(Unidade.UNIDADE, new BasicDBObject());
            }           
            
            BasicDBObject doc = new BasicDBObject();
            doc.append(Unidade.NOME, unidade.getNome());
            doc.append(Unidade.SENHA, unidade.getSenha());
            doc.append(Unidade.ENDERECO, unidade.getEndereco());
            doc.append(Unidade.CEP, unidade.getCep());
            doc.append(Unidade.CIDADE, unidade.getCidade());
            doc.append(Unidade.ESTADO, unidade.getEstado());
            doc.append(Unidade.TELEFONE, unidade.getTelefone());
            doc.append(Unidade.NUMERORESIDENCIAL, unidade.getNumeroResidencial());
            doc.append(Unidade.CODIGO, unidade.getCodigo());
            doc.append(Unidade.CNPJ, unidade.getCnpj());
            
            doc = (BasicDBObject)coll.findAndRemove(doc);
        } catch (ApplicationException ex) {
            throw new ApplicationException("UnidadeDAO", "delete", ex.getMessage());
        }		
	}

	@Override
	public void update(Entity entity) throws ApplicationException {
		try {
			Unidade unidadeOriginal = (Unidade)findById(entity.getId());
			Unidade unidadeAtualizada = (Unidade)entity;
			
			if(unidadeOriginal == null) {
				throw new ApplicationException("UnidadeDAO", "update", "A atualização não foi feita, unidade não encontrada.");
			}
			
            DB db = super.connection();
            DBCollection coll = db.getCollection(Unidade.UNIDADE);
            if(coll== null){
                coll = db.createCollection(Unidade.UNIDADE, new BasicDBObject());
            }            
            
            BasicDBObject docOriginal = new BasicDBObject();
            docOriginal.append(Unidade.NOME, unidadeOriginal.getNome());
            docOriginal.append(Unidade.SENHA, unidadeOriginal.getSenha());
            docOriginal.append(Unidade.ENDERECO, unidadeOriginal.getEndereco());
            docOriginal.append(Unidade.CEP, unidadeOriginal.getCep());
            docOriginal.append(Unidade.CIDADE, unidadeOriginal.getCidade());
            docOriginal.append(Unidade.ESTADO, unidadeOriginal.getEstado());
            docOriginal.append(Unidade.TELEFONE, unidadeOriginal.getTelefone());
            docOriginal.append(Unidade.NUMERORESIDENCIAL, unidadeOriginal.getNumeroResidencial());
            docOriginal.append(Unidade.CODIGO, unidadeOriginal.getCodigo());
            docOriginal.append(Unidade.CNPJ, unidadeOriginal.getCnpj());

            BasicDBObject docAtualizado = new BasicDBObject();
            docAtualizado.append(Unidade.NOME, unidadeAtualizada.getNome());
            docAtualizado.append(Unidade.SENHA, unidadeAtualizada.getSenha());
            docAtualizado.append(Unidade.ENDERECO, unidadeAtualizada.getEndereco());
            docAtualizado.append(Unidade.CEP, unidadeAtualizada.getCep());
            docAtualizado.append(Unidade.CIDADE, unidadeAtualizada.getCidade());
            docAtualizado.append(Unidade.ESTADO, unidadeAtualizada.getEstado());
            docAtualizado.append(Unidade.TELEFONE, unidadeAtualizada.getTelefone());
            docAtualizado.append(Unidade.NUMERORESIDENCIAL, unidadeAtualizada.getNumeroResidencial());
            docAtualizado.append(Unidade.CODIGO, unidadeAtualizada.getCodigo());
            docAtualizado.append(Unidade.CNPJ, unidadeAtualizada.getCnpj());

            @SuppressWarnings("unused")
			WriteResult result = coll.update(docOriginal, docAtualizado);
        } catch (ApplicationException ex) {
            throw new ApplicationException("UnidadeDAO", "update", ex.getMessage());
        }
	}

	@Override
	public void save(Entity entity) throws ApplicationException {
		try {
			Unidade unidade = (Unidade)entity; 
            DB db = super.connection();
            DBCollection coll = db.getCollection(Unidade.UNIDADE);
            if(coll== null){
                coll = db.createCollection(Unidade.UNIDADE, new BasicDBObject());
            }       
            
            BasicDBObject doc = new BasicDBObject();
            doc.append(Unidade.NOME, unidade.getNome());
            doc.append(Unidade.SENHA, unidade.getSenha());
            doc.append(Unidade.ENDERECO, unidade.getEndereco());
            doc.append(Unidade.CEP, unidade.getCep());
            doc.append(Unidade.CIDADE, unidade.getCidade());
            doc.append(Unidade.ESTADO, unidade.getEstado());
            doc.append(Unidade.TELEFONE, unidade.getTelefone());
            doc.append(Unidade.NUMERORESIDENCIAL, unidade.getNumeroResidencial());
            doc.append(Unidade.CODIGO, unidade.getCodigo());
            doc.append(Unidade.CNPJ, unidade.getCnpj());
            
            coll.insert(doc);
        } catch (ApplicationException ex) {
            throw new ApplicationException("UnidadeDAO", "save", ex.getMessage());
        }		
	}
}
