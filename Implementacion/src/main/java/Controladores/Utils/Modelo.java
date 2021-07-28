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

    public Modelo con(String key, int value){
        put(key, value);
        return this;
    }

    public Modelo con(String key, Double value){
        put(key, redondear(value));
        return this;
    }

    public Modelo con(String key, double value){
        return con(key, new Double(value));
    }

    public Modelo con(String key, Object value){
        put(key, value);
        return this;
    }

    public Modelo con(Modelo modelo){
        this.putAll(modelo);
        return this;
    }

    private Double redondear(double precio){
        BigDecimal bd = BigDecimal.valueOf(precio);
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }
}
