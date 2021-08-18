package Dominio.Utils.Exceptions;

public class PendingException extends RuntimeException{
    public PendingException(String s){
        super("Falta implementar "+ s);
    }

    public PendingException(){
        this("");
    }
}


