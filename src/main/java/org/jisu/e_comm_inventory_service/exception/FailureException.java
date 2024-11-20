package org.jisu.e_comm_inventory_service.exception;

public class FailureException extends RuntimeException{

    public FailureException(){
        super("Something went Wrong");
    }

    public FailureException(String msg){
        super(msg);
    }
}
