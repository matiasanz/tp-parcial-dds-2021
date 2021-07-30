package Utils.Exceptions;

public class DatosNulosException extends RuntimeException {
    public DatosNulosException(){
        super("Algunos datos no se recibieron correctamente");
    }
}
