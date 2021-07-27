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
    MongoDatabase database;

    public static void main (String[] args){
        MongoClient mongoClient = iniciarConexion();
        MongoDatabase database = mongoClient.getDatabase("prueba");
        insertarRegistro(database, "nombres","romina");
    }


    public static MongoClient iniciarConexion(){
        MongoClient mongoClient = new MongoClient("localhost",27017);
        return mongoClient;
    }


    public void crearColeccion(String collectionName){
        database.createCollection(collectionName);
    }

    public static void insertarRegistro(MongoDatabase db, String collectionName, String nombre){

        MongoCollection<Document> collection = db.getCollection(collectionName);
        Document documento = new Document();
        documento.put("nombre",nombre);

        collection.insertOne(documento);
    }

    
}
