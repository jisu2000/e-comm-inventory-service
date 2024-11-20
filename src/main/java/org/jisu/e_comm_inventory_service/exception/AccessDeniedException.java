package org.jisu.e_comm_inventory_service.exception;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(){
        super("Access is Denied");
    }

    public AccessDeniedException(String msg){
        super(msg);
    }
}
