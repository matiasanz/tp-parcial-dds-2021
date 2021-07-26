package Controladores.Utils;

import Platos.Plato;

public interface URIs {
    String LOGIN = "/";
    String HOME = "/home";
    String LOCALES = "/locales";
    String SIGNUP = "/signup";
    String PEDIDOS = "/pedidos";

    static String LOCAL(Long idLocal) {
        return LOCALES+"/"+idLocal;
    }

    static String PEDIDO(Long id) {
        return PEDIDOS+"/"+id;
    }

    static String PLATOS(Long idLocal){
        return LOCAL(idLocal)+"/platos";
    }

    static String CREACION_COMBO(Long idLocal) {
        return PLATOS(idLocal)+"/nuevo-combo";
    }

    static String CREAR_PLATO(Long idLocal){
        return PLATOS(idLocal)+"/nuevo";
    }

    static String PLATO(Long idPlato, Long idLocal) {
        return PLATOS(idLocal)+"/"+idPlato;
    }
}
