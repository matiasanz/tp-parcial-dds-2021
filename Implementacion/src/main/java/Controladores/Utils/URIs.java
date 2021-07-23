package Controladores.Utils;

public interface URIs {
    String LOGIN = "/";
    String HOME = "/home";
    String LOCALES = "/locales";
    String SIGNUP = "/signup";

    static String LOCAL(Long idLocal) {
        return LOCALES+"/"+idLocal;
    }
}
