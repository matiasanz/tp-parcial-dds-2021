package MediosContacto;

import com.mongodb.*;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;


import com.mongodb.client.MongoCursor;

import java.util.ArrayList;
import java.util.List;


public class MongoHandler {
    MongoClient mongoClient = new MongoClient("localhost",27017);
    MongoDatabase database = mongoClient.getDatabase("LogsNotificacionesPush");
    String collectionName = "notificaciones";
    List<Notificacion> notificaciones = new ArrayList<>();

    public void insertarRegistro(Notificacion notificacion){
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document documento = new Document()
            .append("asunto",notificacion.getAsunto())
            .append("cuerpo",notificacion.getCuerpo())
            .append("fechaHora",notificacion.getFechaHora().toString())
        ;

        collection.insertOne(documento);
    }

    public void muestraRegistros(){
        MongoCollection<Document> collection = database.getCollection(collectionName);
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
    }

    public void vaciarColeccion(){
        MongoCollection<Document> collection = database.getCollection(collectionName);
        MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            collection.deleteOne(cursor.next());
        }
    }


}
