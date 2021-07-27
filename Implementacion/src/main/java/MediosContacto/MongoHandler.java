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

    public static MongoClient iniciarConexion(){
        MongoClient mongoClient = new MongoClient("localhost",27017);
        return mongoClient;
    }

    public static void insertarRegistro(MongoDatabase db, String collectionName, Notificacion notificacion){

        MongoCollection<Document> collection = db.getCollection(collectionName);
        Document documento = new Document();
        documento.put("asunto",notificacion.getAsunto());
        documento.put("cuerpo",notificacion.getCuerpo());
        documento.put("fecha",notificacion.getFechaHora());

        collection.insertOne(documento);
    }

    public static  void loguearNotificacion(String db, String coleccion, Notificacion notificacion){
        MongoClient mongoClient = iniciarConexion();
        MongoDatabase database = mongoClient.getDatabase(db);
        insertarRegistro(database, coleccion, notificacion);
    }

}
