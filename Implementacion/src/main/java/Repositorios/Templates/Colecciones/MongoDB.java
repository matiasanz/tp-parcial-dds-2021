package Repositorios.Templates.Colecciones;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MongoDB<T> implements Coleccion<T>{
    private final String dataBase = "pedi2YaDBMongo";
    private MongoClient mongoClient = new MongoClient("localhost",27017);
    private String nombreColeccion;
    private Class<T> clase;

    public MongoDB(String nombreColeccion, Class<T> clase){
        this.nombreColeccion = nombreColeccion;
        this.clase = clase;
    }

    @Override
    public void agregar(T elem) {
        coleccion().insertOne(toDocument(elem));
    }

    @Override
    public List<T> getAll() {
        MongoCursor<Document> cursor = coleccion().find().iterator();

        List<Document> registros = new LinkedList<>();
        try {
            while (cursor.hasNext()) {
                registros.add(cursor.next());
            }
        } finally {
            cursor.close();
        }

        return registros
            .stream()
            .map(this::fromDocument)
            .collect(Collectors.toList());
    }

    private Document toDocument(T elem){
        String json = new Gson().toJson(elem, clase);
        return Document.parse(json);
    }

    private T fromDocument(Document doc){
        String json = doc.toJson();
        return new Gson().fromJson(json, clase);
    }

    public void eliminar(T registro){
        coleccion().deleteOne(toDocument(registro));
    }

    public void borrarTodo(){
        coleccion().drop();
    }

    private MongoCollection<Document> coleccion(){
        return database().getCollection(nombreColeccion);
    }

    private MongoDatabase database(){
        return mongoClient.getDatabase(dataBase);
    }

    public String getNombre() {
        return nombreColeccion;
    }
}
