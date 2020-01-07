package br.com.poo.sysfi.service;

import br.com.poo.sysfi.exception.ApplicationException;
import br.com.poo.sysfi.model.Entity;
import java.util.List;

public interface BusinessService {
    public void delete(Entity object) throws ApplicationException;
    public void save(Entity object) throws ApplicationException;
    public void update(Entity object) throws ApplicationException;
    public Entity findById(String id) throws ApplicationException;
    public List<Entity> findAll() throws ApplicationException;
}
