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
import br.com.poo.sysfi.model.Entity;

public class ContaPoupancaDAO extends DAO{
	public Entity findById(String id) throws ApplicationException {
        try {
            DB db = super.connection();
            DBCollection coll = db.getCollection(ContaPoupanca.CONTAPOUPANCA);
            DBCursor cursor = coll.find();
            
            while(cursor.hasNext()) {
               DBObject dbObject = cursor.next();
               if(dbObject.get(ContaPoupanca.ID).toString().equals(id)){

            	   ContaPoupanca contaPoupanca = new ContaPoupanca(
            			                 	       (int)dbObject.get(ContaPoupanca.NUMERO),
                		   				            ""+dbObject.get(ContaPoupanca.DONOCPF),
                		   				            ""+dbObject.get(ContaPoupanca.SENHA),
                		   				           (double)dbObject.get(ContaPoupanca.SALDO),
               		   				                (int)dbObject.get(ContaPoupanca.UNIDADECODIGO),
                		   				           (double)dbObject.get(ContaPoupanca.RENDIMENTO),
                		   				            ""+dbObject.get(ContaPoupanca.DATARENDIMENTO));
                   
            	   contaPoupanca.setId(dbObject.get(ContaPoupanca.ID).toString());
                   return contaPoupanca;
               }
            }
            
        } catch (ApplicationException ex) {
            throw new ApplicationException("ContaPoupancaDAO", "findById", ex.getMessage());
        }
		return null;
	}

	@Override
	public List<Entity> findAll() throws ApplicationException {
		List<Entity> lista = new ArrayList<Entity>();
        try {
            DB db = super.connection();
            DBCollection coll = db.getCollection(ContaPoupanca.CONTAPOUPANCA);
            DBCursor cursor = coll.find();
            
            while(cursor.hasNext()) {
               DBObject dbObject = cursor.next();
               
        	   ContaPoupanca contaPoupanca = new ContaPoupanca(
        			   							(int)dbObject.get(ContaPoupanca.NUMERO),
        			   							 ""+dbObject.get(ContaPoupanca.DONOCPF),
        			   							 ""+dbObject.get(ContaPoupanca.SENHA),
        			   							(double)dbObject.get(ContaPoupanca.SALDO),
            		   				            (int)dbObject.get(ContaPoupanca.UNIDADECODIGO),
        			   							(double)dbObject.get(ContaPoupanca.RENDIMENTO),
        			   							 ""+dbObject.get(ContaPoupanca.DATARENDIMENTO));
               
        	   contaPoupanca.setId(dbObject.get(ContaPoupanca.ID).toString());
               lista.add(contaPoupanca);
            }
            
        } catch (ApplicationException ex) {
            throw new ApplicationException("ContaPoupancaDAO", "findAll", ex.getMessage());
        }
        return lista;
	}

	@Override
	public void delete(Entity entity) throws ApplicationException {
		try {
			ContaPoupanca contaPoupanca = (ContaPoupanca)entity; 
            DB db = super.connection();
            DBCollection coll = db.getCollection(ContaPoupanca.CONTAPOUPANCA);
            if(coll== null){
                coll = db.createCollection(ContaPoupanca.CONTAPOUPANCA, new BasicDBObject());
            }           
            
            BasicDBObject doc = new BasicDBObject();
            doc.append(ContaPoupanca.NUMERO, contaPoupanca.getNumero());
            doc.append(ContaPoupanca.DONOCPF, contaPoupanca.getDonoCpf());
            doc.append(ContaPoupanca.SENHA, contaPoupanca.getSenha());
            doc.append(ContaPoupanca.SALDO, contaPoupanca.getSaldo());
            doc.append(ContaPoupanca.UNIDADECODIGO, contaPoupanca.getUnidade().getCodigo());
            doc.append(ContaPoupanca.RENDIMENTO, contaPoupanca.getRendimento());
            doc.append(ContaPoupanca.DATARENDIMENTO, contaPoupanca.getDataRendimento().toString());
            
            doc = (BasicDBObject)coll.findAndRemove(doc);
        } catch (ApplicationException ex) {
            throw new ApplicationException("ContaPoupancaDAO", "delete", ex.getMessage());
        }		
	}

	@Override
	public void update(Entity entity) throws ApplicationException {
		try {
			ContaPoupanca contaPoupancaOriginal = (ContaPoupanca)findById(entity.getId());
			ContaPoupanca contaPoupancaAtualizada = (ContaPoupanca)entity;
			
			if(contaPoupancaOriginal == null) {
				throw new ApplicationException("ContaPoupancaDAO", "update", "A atualização não foi feita, conta não encontrado.");
			}
			
            DB db = super.connection();
            DBCollection coll = db.getCollection(ContaPoupanca.CONTAPOUPANCA);
            if(coll== null){
                coll = db.createCollection(ContaPoupanca.CONTAPOUPANCA, new BasicDBObject());
            }            
            
            BasicDBObject docOriginal = new BasicDBObject();
            docOriginal.append(ContaPoupanca.NUMERO, contaPoupancaOriginal.getNumero());
            docOriginal.append(ContaPoupanca.DONOCPF, contaPoupancaOriginal.getDonoCpf());
            docOriginal.append(ContaPoupanca.SENHA, contaPoupancaOriginal.getSenha());
            docOriginal.append(ContaPoupanca.SALDO, contaPoupancaOriginal.getSaldo());
            docOriginal.append(ContaPoupanca.UNIDADECODIGO, contaPoupancaOriginal.getUnidade().getCodigo());
            docOriginal.append(ContaPoupanca.RENDIMENTO, contaPoupancaOriginal.getRendimento());
            docOriginal.append(ContaPoupanca.DATARENDIMENTO, contaPoupancaOriginal.getDataRendimento().toString());
            
            BasicDBObject docAtualizado = new BasicDBObject();
            docAtualizado.append(ContaPoupanca.NUMERO, contaPoupancaAtualizada.getNumero());
            docAtualizado.append(ContaPoupanca.DONOCPF, contaPoupancaAtualizada.getDonoCpf());
            docAtualizado.append(ContaPoupanca.SENHA, contaPoupancaAtualizada.getSenha());
            docAtualizado.append(ContaPoupanca.SALDO, contaPoupancaAtualizada.getSaldo());
            docAtualizado.append(ContaPoupanca.UNIDADECODIGO, contaPoupancaAtualizada.getUnidade().getCodigo());
            docAtualizado.append(ContaPoupanca.RENDIMENTO, contaPoupancaAtualizada.getRendimento());
            docAtualizado.append(ContaPoupanca.DATARENDIMENTO, contaPoupancaAtualizada.getDataRendimento().toString());     
            
            @SuppressWarnings("unused")
			WriteResult result = coll.update(docOriginal, docAtualizado);
        } catch (ApplicationException ex) {
            throw new ApplicationException("ContaPoupancaDAO", "update", ex.getMessage());
        }
	}

	@Override
	public void save(Entity entity) throws ApplicationException {
		try {
			ContaPoupanca contaPoupanca = (ContaPoupanca)entity; 
            DB db = super.connection();
            DBCollection coll = db.getCollection(ContaPoupanca.CONTAPOUPANCA);
            if(coll== null){
                coll = db.createCollection(ContaPoupanca.CONTAPOUPANCA, new BasicDBObject());
            }       
            
            BasicDBObject doc = new BasicDBObject();
            doc.append(ContaPoupanca.NUMERO, contaPoupanca.getNumero());
            doc.append(ContaPoupanca.DONOCPF, contaPoupanca.getDonoCpf());
            doc.append(ContaPoupanca.SENHA, contaPoupanca.getSenha());
            doc.append(ContaPoupanca.SALDO, contaPoupanca.getSaldo());
            doc.append(ContaPoupanca.UNIDADECODIGO, contaPoupanca.getUnidade().getCodigo());
            doc.append(ContaPoupanca.RENDIMENTO, contaPoupanca.getRendimento());
            doc.append(ContaPoupanca.DATARENDIMENTO, contaPoupanca.getDataRendimento().toString());
            
            coll.insert(doc);
        } catch (ApplicationException ex) {
            throw new ApplicationException("ContaPoupancaDAO", "save", ex.getMessage());
        }		
	}
}
