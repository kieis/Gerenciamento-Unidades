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
import br.com.poo.sysfi.model.ContaPoupanca;
import br.com.poo.sysfi.model.ContaSimples;
import br.com.poo.sysfi.model.Entity;

public class ContaSimplesDAO extends DAO{
	public Entity findById(String id) throws ApplicationException {
		//ContaSimples contaSimples = null;
        try {
            DB db = super.connection();
            DBCollection coll = db.getCollection(ContaSimples.CONTASIMPLES);
            DBCursor cursor = coll.find();
            
//            BasicDBObject doc = new BasicDBObject();
//            doc.append(ContaSimples.ID, id);
//            
//            DBObject retorno = coll.findOne(doc);
//     	    contaSimples = new ContaSimples(
//     	    						        (int)retorno.get(ContaSimples.NUMERO),
//     	    						        ""+retorno.get(ContaSimples.DONOCPF),
//     	    						        ""+retorno.get(ContaSimples.SENHA),
//     	    						        (double)retorno.get(ContaSimples.SALDO),
//     	    						        (int)retorno.get(ContaPoupanca.UNIDADECODIGO));
//     	    contaSimples.setId(retorno.get(ContaSimples.ID).toString());
//            
//            
//            //return contaSimples;
            
            while(cursor.hasNext()) {
               DBObject dbObject = cursor.next();
               if(dbObject.get(ContaSimples.ID).toString().equals(id)){

            	   ContaSimples contaSimples = new ContaSimples(
            			                 	       (int)dbObject.get(ContaSimples.NUMERO),
                		   				            ""+dbObject.get(ContaSimples.DONOCPF),
                		   				            ""+dbObject.get(ContaSimples.SENHA),
                		   				           (double)dbObject.get(ContaSimples.SALDO),
                		   				           (int)dbObject.get(ContaPoupanca.UNIDADECODIGO));
                   
            	   contaSimples.setId(dbObject.get(ContaSimples.ID).toString());
                   return contaSimples;
               }
            }
        } catch (ApplicationException ex) {
            throw new ApplicationException("ContaSimplesDAO", "findById", ex.getMessage());
        }
		return null;
	}

	@Override
	public List<Entity> findAll() throws ApplicationException {
		List<Entity> lista = new ArrayList<Entity>();
        try {
            DB db = super.connection();
            DBCollection coll = db.getCollection(ContaSimples.CONTASIMPLES);
            DBCursor cursor = coll.find();
            
            while(cursor.hasNext()) {
               DBObject dbObject = cursor.next();
               
        	   ContaSimples contaSimples = new ContaSimples(
             	       						   (int)dbObject.get(ContaSimples.NUMERO),
             	       						   	""+dbObject.get(ContaSimples.DONOCPF),
             	       						   	""+dbObject.get(ContaSimples.SENHA),
             	       						   (double)dbObject.get(ContaSimples.SALDO),
             	       						   (int)dbObject.get(ContaPoupanca.UNIDADECODIGO));
               
        	   contaSimples.setId(dbObject.get(ContaSimples.ID).toString());
               lista.add(contaSimples);
            }
            
        } catch (ApplicationException ex) {
            throw new ApplicationException("ContaSimplesDAO", "findAll", ex.getMessage());
        }
        return lista;
	}

	@Override
	public void delete(Entity entity) throws ApplicationException {
		try {
			ContaSimples contaSimples = (ContaSimples)entity; 
            DB db = super.connection();
            DBCollection coll = db.getCollection(ContaSimples.CONTASIMPLES);
            if(coll== null){
                coll = db.createCollection(ContaSimples.CONTASIMPLES, new BasicDBObject());
            }           
            
            BasicDBObject doc = new BasicDBObject();
            doc.append(ContaSimples.NUMERO, contaSimples.getNumero());
            doc.append(ContaSimples.DONOCPF, contaSimples.getDonoCpf());
            doc.append(ContaSimples.SENHA, contaSimples.getSenha());
            doc.append(ContaSimples.SALDO, contaSimples.getSaldo());
            doc.append(ContaSimples.UNIDADECODIGO, contaSimples.getUnidade().getCodigo());
            
            doc = (BasicDBObject)coll.findAndRemove(doc);
        } catch (ApplicationException ex) {
            throw new ApplicationException("ContaSimplesDAO", "delete", ex.getMessage());
        }		
	}

	@Override
	public void update(Entity entity) throws ApplicationException {
		try {
			ContaSimples contaSimplesOriginal = (ContaSimples)findById(entity.getId());
			ContaSimples contaSimplesAtualizada = (ContaSimples)entity;
			
			if(contaSimplesOriginal == null) {
				throw new ApplicationException("ContaSimplesDAO", "update", "A atualização não foi feita, conta não encontrada.");
			}
			
            DB db = super.connection();
            DBCollection coll = db.getCollection(ContaSimples.CONTASIMPLES);
            if(coll== null){
                coll = db.createCollection(ContaSimples.CONTASIMPLES, new BasicDBObject());
            }            
            
            BasicDBObject docOriginal = new BasicDBObject();
            docOriginal.append(ContaSimples.NUMERO, contaSimplesOriginal.getNumero());
            docOriginal.append(ContaSimples.DONOCPF, contaSimplesOriginal.getDonoCpf());
            docOriginal.append(ContaSimples.SENHA, contaSimplesOriginal.getSenha());
            docOriginal.append(ContaSimples.SALDO, contaSimplesOriginal.getSaldo());
            docOriginal.append(ContaSimples.UNIDADECODIGO, contaSimplesOriginal.getUnidade().getCodigo());

            BasicDBObject docAtualizado = new BasicDBObject();
            docAtualizado.append(ContaSimples.NUMERO, contaSimplesAtualizada.getNumero());
            docAtualizado.append(ContaSimples.DONOCPF, contaSimplesAtualizada.getDonoCpf());
            docAtualizado.append(ContaSimples.SENHA, contaSimplesAtualizada.getSenha());
            docAtualizado.append(ContaSimples.SALDO, contaSimplesAtualizada.getSaldo());
            docAtualizado.append(ContaSimples.UNIDADECODIGO, contaSimplesAtualizada.getUnidade().getCodigo());

            @SuppressWarnings("unused")
			WriteResult result = coll.update(docOriginal, docAtualizado);
        } catch (ApplicationException ex) {
            throw new ApplicationException("ContaSimplesDAO", "update", ex.getMessage());
        }
	}

	@Override
	public void save(Entity entity) throws ApplicationException {
		try {
			ContaSimples contaSimples = (ContaSimples)entity; 
            DB db = super.connection();
            DBCollection coll = db.getCollection(ContaSimples.CONTASIMPLES);
            if(coll== null){
                coll = db.createCollection(ContaSimples.CONTASIMPLES, new BasicDBObject());
            }       
            
            BasicDBObject doc = new BasicDBObject();
            doc.append(ContaSimples.NUMERO, contaSimples.getNumero());
            doc.append(ContaSimples.DONOCPF, contaSimples.getDonoCpf());
            doc.append(ContaSimples.SENHA, contaSimples.getSenha());
            doc.append(ContaSimples.SALDO, contaSimples.getSaldo());
            doc.append(ContaSimples.UNIDADECODIGO, contaSimples.getUnidade().getCodigo());
            
            coll.insert(doc);
        } catch (ApplicationException ex) {
            throw new ApplicationException("ContaSimplesDAO", "save", ex.getMessage());
        }		
	}
}
