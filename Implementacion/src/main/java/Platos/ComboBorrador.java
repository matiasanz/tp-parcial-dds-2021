package Platos;

import Local.Local;
import Repositorios.Templates.Identificable;

import java.util.ArrayList;
import java.util.List;

public class ComboBorrador{
    private String nombre;
    private Local local;
    private List<Plato> platos = new ArrayList<>();

    public ComboBorrador(Local local){
        this.local = local;
    }

    public void agregarPlato(Plato plato){
        platos.add(plato);
    }

    public Combo crearCombo(){
        validarCombo();
        return new Combo(nombre, platos);
    }

    public List<Plato> getPlatos(){
        return platos;
    }

    private void validarCombo(){
        if(nombre==null) throw new RuntimeException("Debe especificar el nombre del combo");
        if(platos.size()<2) throw new RuntimeException("Debe agregar al menos dos platos al combo");
        if(local==null) throw new RuntimeException("No se especifico el local");
    }

    public String getNombre() {
        return nombre;
    }

    public Local getLocal() {
        return local;
    }

    public void setNombre(String nombre) {
        this.nombre=nombre;
    }
}

