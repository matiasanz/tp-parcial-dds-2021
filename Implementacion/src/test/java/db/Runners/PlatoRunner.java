package db.Runners;

import Platos.Combo;
import Platos.PlatoSimple;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Arrays;

public class PlatoRunner {
    public static void main(String[] args){
        PlatoSimple platoSimple = new PlatoSimple();
        platoSimple.setPrecioBase(897.5);
        platoSimple.setNombre("hamburguesa");
        platoSimple.setDescripcion("Medallon de carne entre panes");
        platoSimple.setIngredientes(Arrays.asList("pan", "carne"));

        PlatoSimple platoSimple2 = new PlatoSimple();
        platoSimple2.setPrecioBase(90.5);
        platoSimple2.setNombre("papas noisette");
        platoSimple2.setDescripcion("papas redondas");
        platoSimple2.setIngredientes(Arrays.asList("papas sin cascara"));

        Combo combo = new Combo();
        combo.setNombre("hamburguesa con papas");
        combo.setPlatos(Arrays.asList(platoSimple,platoSimple2));


        EntityManager en = PerThreadEntityManagers.getEntityManager();
        EntityTransaction transaction = en.getTransaction();

        transaction.begin();
        //en.persist(platoSimple);
        en.persist(combo);
        transaction.commit();
    }

}
