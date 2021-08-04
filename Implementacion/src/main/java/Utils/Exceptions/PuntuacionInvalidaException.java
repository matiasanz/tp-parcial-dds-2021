package Utils.Exceptions;

public class PuntuacionInvalidaException extends RuntimeException {
    public PuntuacionInvalidaException(Float puntuacion) {
        super("La puntuacion debe ser un número entre 1 y 5, y ud. ha querido ingresar "+puntuacion);
    }
}
