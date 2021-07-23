package Utils.Exceptions;

public class NombreOcupadoException extends RuntimeException {
    public NombreOcupadoException(String nombre) {
        super("Ya existe un usuario con el nombre "+nombre);
    }
}
