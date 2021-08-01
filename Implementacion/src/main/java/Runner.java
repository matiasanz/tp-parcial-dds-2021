import Local.CategoriaLocal;
import Local.Duenio;
import Pedidos.Item;
import Pedidos.Pedido;
import Platos.Combo;
import Platos.PlatoSimple;
import Usuarios.Categorias.CategoriaCliente;
import Usuarios.Categorias.Frecuente;
import Usuarios.Cliente;
import Usuarios.Usuario;
import Utils.Factory.ProveedorDeClientes;
import Utils.Factory.ProveedorDeDuenios;
import Utils.Factory.ProveedorDeLocales;
import Utils.Factory.ProveedorDePlatos;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Arrays;
import java.util.List;

public class Runner {
    public static void main(String[] args){
        Cliente cliente = new Cliente();
        CategoriaCliente frecuente = new Frecuente();
        cliente.setCategoria(frecuente);
        cliente.setNombre("romina");
        cliente.setApellido("martinez");
        cliente.setUsername("rm");
        cliente.setPassword("12");
        cliente.setMail("romi@gmail.com");

        EntityManager en = PerThreadEntityManagers.getEntityManager();
        EntityTransaction transaction = en.getTransaction();

        transaction.begin();
        en.persist(cliente);
        transaction.commit();
    }
}
