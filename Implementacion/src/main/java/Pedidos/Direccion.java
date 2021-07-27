package Pedidos;

import Usuarios.Ubicacion;

public class Direccion {
    private final String calle;
    //TODO Ubicacion ubicacion

    public Direccion(String calle){
        this.calle=calle;
    }

    public String getCalle(){
        return calle;
    }
}
