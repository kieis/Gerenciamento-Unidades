package br.com.poo.sysfi.model;

import java.io.Serializable;

public class Entity implements Serializable {
	public static final String ID = "_id";
    private static final long serialVersionUID = 1L;
	private String id;
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getId(){
        return id;
    }
}
