package Mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.LinkedList;
import java.util.List;

public class EventLogger {

    public static EventLogger autenticacionLogger = new EventLogger("usuarios_sospechosos");
    public static EventLogger pedidosRechazadosLogger = new EventLogger("pedidos_rechazados");
    public static boolean mongoHabilitado = true;

    private final String dataBase = "pedi2YaDBMongo"; //Es una sola
    private final String nombreColeccion; 
    private MongoClient mongoClient = new MongoClient("localhost",27017);

    public EventLogger(String nombreColeccion){
        this.nombreColeccion = nombreColeccion;
    }

    public void loguear(Document document){
        String color = "\u001B"+"[32m"; //Verde

        if(mongoHabilitado){
            coleccion().insertOne(document);
            color = "\u001B"+"[33m"; //Amarillo
        }

        System.out.println(color+"Log["+nombreColeccion+"] "+document.toJson() + "\u001B[37m");
    }

    public void crearColeccion(){
        database().createCollection(nombreColeccion);
    }

    public List<Document> getLogs(){
        MongoCursor<Document> cursor = coleccion().find().iterator();

        List<Document> registros = new LinkedList<>();
        try {
            while (cursor.hasNext()) {
                registros.add(cursor.next());
            }
        } finally {
            cursor.close();
        }

        return registros;
    }

    public void vaciar(){
        coleccion().drop();
    }

    private MongoCollection<Document> coleccion(){
        return database().getCollection(nombreColeccion);
    }

    private MongoDatabase database(){
        return mongoClient.getDatabase(dataBase);
    }
}
