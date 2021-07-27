package MediosContacto;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

public class MongoHandler {
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;


    public void iniciarConexion(){
        mongoClient = new MongoClient("localhost");
        database = mongoClient.getDatabase("logs");
    }

    public void crearColeccion(String collectionName){
        database.createCollection(collectionName);
    }

    public void obtenerDb(String dbName){
        database = mongoClient.getDatabase(dbName);
    }

    public void traerColeccion(String collectionName){
        collection = database.getCollection(collectionName);
    }

    public void insertarUnDocumento(Document doc){
        collection.insertOne(doc);
    }

    public void insertarMuchosDocumentos(List<Document> documents){
        collection.insertMany(documents);
    }
    
}
