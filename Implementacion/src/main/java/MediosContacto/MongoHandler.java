package MediosContacto;

import com.mongodb.*;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

public class MongoHandler {
    public void insertarRegistro(MongoDatabase db, String collectionName, Notificacion notificacion){
        MongoCollection<Document> collection = db.getCollection(collectionName);
        Document documento = new Document()
            .append("asunto",notificacion.getAsunto())
            .append("cuerpo",notificacion.getCuerpo())
            .append("fecha",notificacion.getFechaHora())
        ;

        collection.insertOne(documento);
    }

    public void loguearNotificacion(Notificacion notificacion){
        MongoClient mongoClient = new MongoClient("localhost",27017);
        MongoDatabase database = mongoClient.getDatabase("LogsNotificacionesPush");
        insertarRegistro(database, "notificaciones", notificacion);
    }
}
