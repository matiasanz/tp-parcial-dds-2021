package Mongo;

import org.bson.Document;
import org.junit.After;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LoggerTest {
    static EventLogger logger = new EventLogger("test");

    @After
    public void clean(){
        logger.vaciar();
    }

    @Test
    public void logueoYSeGuarda(){
        Document log = new Document("numero", 1).append("palabra", "prueba");
        logger.loguear(log);

        List<Document> logs = logger.getLogs();

        assertEquals(1, logs.size());
        Document logLeido = logs.get(0);

        assertEquals(3, logLeido.size());
        assertNotNull(logLeido.get("_id"));
        assertEquals(1, logLeido.get("numero"));
        assertEquals("prueba", logLeido.get("palabra"));
    }
}