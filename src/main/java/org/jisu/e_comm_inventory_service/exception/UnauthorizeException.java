package org.jisu.e_comm_inventory_service.exception;

public class UnauthorizeException extends RuntimeException{
    
    public UnauthorizeException(){
        super("Invalid Request");
    }

    public UnauthorizeException(String msg){
        super(msg);
    }
}
