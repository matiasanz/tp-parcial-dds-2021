package MediosContacto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
            .append("fecha",notificacion.getFechaHora())
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

    /*
    public void cargarNotifDesdeMongo(){
        Notificacion notificacion = null;
        MongoCollection<Document> collection = database.getCollection(collectionName);
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                ObjectMapper mapper = new ObjectMapper();
                notificacion = mapper.readValue(cursor.next().toJson(), Notificacion.class);
                notificaciones.add(notificacion);
            }
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        System.out.println(notificaciones);
    }

     */

}
