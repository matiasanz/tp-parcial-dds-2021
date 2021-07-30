package Controladores.Utils;

import spark.Request;
import spark.Response;

public class ErrorHandler {
    private final String ERROR_TOKEN = "error";
    //TODO: Ver si se puede pasar algun redirect: hay bastante codigo repetido

    public void setMensaje(Request req, String mensaje){
        req.session().attribute(ERROR_TOKEN, mensaje);
    }

    public String getMensaje(Request req){
        String mensaje = req.session().attribute(ERROR_TOKEN);
        req.session().removeAttribute(ERROR_TOKEN);
        return mensaje;
    }
}
