package org.jisu.e_comm_inventory_service.exception;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(){
        super("This request can not be fulfilled");
    }
    public InvalidRequestException(String msg){
        super(msg);
    }
}
