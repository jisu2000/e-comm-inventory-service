package org.jisu.e_comm_inventory_service.exception;

public class ResourceNotFoundException extends RuntimeException{
   
    public ResourceNotFoundException(){
        super("Entity not found");
    }

    public ResourceNotFoundException(String msg){
        super(msg);
    }

    public ResourceNotFoundException(String entity, String field, String value){
        super(entity+" Not found with "+field+" "+value);
    }
}
