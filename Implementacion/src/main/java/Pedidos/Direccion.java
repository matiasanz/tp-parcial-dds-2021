package Pedidos;

import Usuarios.Ubicacion;

public class Direccion {
    public Direccion(String calle){
        this.calle=calle;
    }
    private final String calle;
//TODO Ubicacion ubicacion

    public String getCalle(){
        return calle;
    }
}
