package Mongo;

import Controladores.Utils.Modelos;
import Pedidos.Pedido;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.MongoCursor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MongoHandler {
    MongoClient mongoClient = new MongoClient("localhost",27017);

    public void loguearPedidoRechazado(Pedido pedido){
        MongoDatabase database = mongoClient.getDatabase("pedidos");
        String collectionName = "rechazados";
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document documento = new Document()
                .append("precio",pedido.getPrecioAbonado())
                .append("direccion",pedido.getDireccion())
                .append("local",pedido.getLocal().getNombre())
                .append("cliente",pedido.getCliente().getUsername())
                ;

        collection.insertOne(documento);
    }

    public void loguearUsuarioSospechoso(int intentos, String nombre) {
        MongoDatabase database = mongoClient.getDatabase("logueos_fallidos");
        String collectionName = "usuarios_sospechosos";
        LocalDateTime fecha = LocalDateTime.now();
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document documento = new Document()
                .append("usuario",nombre)
                .append("intentos",intentos)
                .append("fechaHora", Modelos.parseModel(fecha));
                ;

        collection.insertOne(documento);
    }

    public List<String> getRegistros(String databaseName, String collectionName){
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        MongoCursor<Document> cursor = collection.find().iterator();
        List<String> registros = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                registros.add(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
        return registros;
    }

    public void vaciarColeccion(String databaseName, String collectionName){
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            collection.deleteOne(cursor.next());
        }
    }
}
