package Utils.Exceptions;

import java.util.Arrays;
import java.util.List;

public class PendingException extends RuntimeException{
    public PendingException(String s){
        super("Falta implementar "+ s);
    }

    public PendingException(){
        this("");
    }
}


