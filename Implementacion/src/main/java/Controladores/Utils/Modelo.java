package Controladores.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class Modelo extends HashMap<String, Object>{
    public Modelo(String key, Object value){
        super();
        con(key, value);
    }

    public Modelo(){}

    public Modelo con(String key, Object value){
        put(key, value);
        return this;
    }

    public Modelo con(Modelo modelo){
        this.putAll(modelo);
        return this;
    }
}
