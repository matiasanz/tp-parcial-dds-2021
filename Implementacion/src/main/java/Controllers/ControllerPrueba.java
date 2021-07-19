package Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ControllerPrueba {
    private final String ARCHIVO_LOGIN = "login.html.hbs";

    public ModelAndView getLogin(Request request, Response response) {
        return new ModelAndView( generarModelo(request, response) , ARCHIVO_LOGIN);
    }

    private Map<String, Object> generarModelo(Request pedido, Response respuesta){
        Map<String, Object> modelo = new HashMap<>();

        return modelo;
    }

}
