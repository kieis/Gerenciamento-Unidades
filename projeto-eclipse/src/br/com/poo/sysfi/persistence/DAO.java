package br.com.poo.sysfi.persistence;

import br.com.poo.sysfi.exception.ApplicationException;
import br.com.poo.sysfi.model.Entity;
import java.util.List;
import com.mongodb.DB;
import com.mongodb.MongoClient;

public abstract class DAO {
    
    protected DB connection() throws ApplicationException{
        DB db = null;
        try{
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            db = mongoClient.getDB("sysfi");
        }catch(Exception ex){
            throw new ApplicationException("DAO", "connection", ex.getMessage());
        }
        return db;
    }

    /**
     * Retorna uma entidade a partir do seu identificador
     * @param id
     * @return
     * @throws br.com.poo.cfc.exception.ApplicationException
     */
    public abstract Entity findById(String id) throws ApplicationException;
    
    /**
     * Retorna todos os objetos de uma entidade
     * @return 
     * @throws br.com.poo.cfc.exception.ApplicationException 
     */
    public abstract List<Entity> findAll() throws ApplicationException;
    
    /**
     * Remove o objeto do banco de dados
     * @param entity 
     * @throws br.com.poo.cfc.exception.ApplicationException 
     */
    public abstract void delete(Entity entity) throws ApplicationException;
    
    /**
     * Autualiza um objeto no banco de dados
     * @param entity 
     * @throws br.com.poo.cfc.exception.ApplicationException 
     */
    public abstract void update(Entity entity) throws ApplicationException; 
    
    /**
     * insere um objeto no banco de dados
     * @param entity 
     * @throws br.com.poo.cfc.exception.ApplicationException 
     */
    public abstract void save(Entity entity) throws ApplicationException; 
}

