package Mongo;

import com.google.gson.Gson;
import org.bson.Document;
import org.junit.After;
import org.junit.Test;

import javax.print.Doc;
import java.util.List;

import static org.junit.Assert.*;

public class LoggerTest {
    static Logger logger = Loggers.mongoLogger("tests");

    @After
    public void clean(){
        logger.eliminarLogs();
    }

    @Test
    public void siNoLogueoNoHayNada(){
        assert(logger.getLogs().isEmpty());
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
        assertEquals(1.0, logLeido.get("numero"));
        assertEquals("prueba", logLeido.get("palabra"));
    }
}
