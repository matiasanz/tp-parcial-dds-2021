package Usuarios;

public class Repartidor extends Usuario{
    private Ubicacion ubicacionActual;

    public Repartidor(String username, String contrasenia, String nombre, String apellido) {
        super(username, contrasenia, nombre, apellido);
    }

    public void setUbicacion(Ubicacion ubicacion){
        this.ubicacionActual = ubicacion;
    }

    public Ubicacion getUbicacionActual(){
        return ubicacionActual;
    }
}
