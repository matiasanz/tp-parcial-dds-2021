package Controladores.Utils;

import Platos.Plato;

public interface URIs {
    //Compartidas
    String LOGIN = "/";
    String HOME = "/home";
    String SIGNUP = "/signup";
    String PEDIDOS = "/pedidos";
    String NOTIFICACIONES = "/notificaciones";
    static String PEDIDO(int nro) {
        return PEDIDOS+"/"+nro;
    }

    //Cliente
    static String LOCAL(Long idLocal) {
        return LOCALES+"/"+idLocal;
    }
    String LOCALES = "/locales";
    static String PLATO(Long idPlato, Long idLocal) {
        return PLATOS(idLocal)+"/"+idPlato;
    }
    static String PLATOS(Long idLocal){
        return LOCAL(idLocal)+"/platos";
    }

    //Locales
    String CREACION_COMBO = "/platos/nuevo-combo";
    static String CREAR_PLATO(Long idLocal){
        return PLATOS(idLocal)+"/nuevo";
    }
}
