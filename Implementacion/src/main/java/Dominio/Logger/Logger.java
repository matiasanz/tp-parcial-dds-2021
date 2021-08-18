package Dominio.Logger;

import Repositorios.Templates.Colecciones.Coleccion;
import org.bson.Document;

import java.util.List;

public class Logger {

    protected final Coleccion<Document> coleccion;

    public Logger(Coleccion<Document> coleccion){
        this.coleccion = coleccion;
    }

    public void loguear(Document document){
        coleccion.agregar(document);
        System.out.println(celeste("Log["+document.getString("asunto")+"] "+document.toJson()));
    }

    public List<Document> getLogs(){
        return coleccion.getAll();
    }

    public void eliminarLog(Document log){
        coleccion.eliminar(log);
    }

    public void destruir(){
        coleccion.borrarTodo();
    }

    private String celeste(String elem){
        return "\u001B[36m"+elem+"\u001B[0m";
    }
}
