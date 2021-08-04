package Repositorios.Templates.Colecciones;

import Repositorios.Templates.Identificado;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.List;

public class DB<T extends Identificado>
    implements Coleccion<T>
    , WithGlobalEntityManager, EntityManagerOps, TransactionalOps

    {
        private Class<T> clase;

        public DB(Class<T> clase){
            this.clase = clase;
        }

        public List<T> getAll(){
            return entityManager()
                .createQuery("from "+clase.getSimpleName())
                .getResultList();
        }

        public void agregar(T elem){
            entityManager().persist(elem);
        }
    }
