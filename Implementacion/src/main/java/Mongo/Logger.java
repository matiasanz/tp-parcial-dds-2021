package Mongo;

import Repositorios.Templates.Colecciones.Coleccion;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.LinkedList;
import java.util.List;

public class Logger {

    private final String asunto;
    protected final Coleccion<Document> coleccion;

    public Logger(String asunto, Coleccion<Document> coleccion){
        this.asunto = asunto;
        this.coleccion = coleccion;
    }

    public void loguear(Document document){
        coleccion.agregar(document);
        System.out.println(celeste("Log["+asunto+"] "+document.toJson()));
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
