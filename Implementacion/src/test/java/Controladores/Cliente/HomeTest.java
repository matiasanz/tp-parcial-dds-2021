package Controladores.Cliente;

import Controladores.Utils.Modelos;
import Dominio.Local.*;
import Repositorios.RepoLocales;
import Repositorios.Templates.Colecciones.Coleccion;
import Repositorios.Templates.Colecciones.ColeccionMemoria;
import Repositorios.Templates.Colecciones.DB;
import Dominio.Utils.Factory.ProveedorDeLocales;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class HomeTest {
    static Coleccion<Local> coleccionMock = new ColeccionMemoria<>();
    static RepoLocales repoLocales = RepoLocales.getInstance();
    HomeController homeController = new HomeController(null, repoLocales);

    Local veganoSinPedidos = ProveedorDeLocales.localConNPedidos("Viva las Vegan", CategoriaLocal.VEGANO, 0);
    Local pizzeria2Pedidos = ProveedorDeLocales.localConNPedidos("Tomatitos", CategoriaLocal.PIZZERIA, 2);
    Local pizzeria3Pedidos = ProveedorDeLocales.localConNPedidos("El Litoral", CategoriaLocal.PIZZERIA, 3);
    Local arabe4Pedidos = ProveedorDeLocales.localConNPedidos("Hola Luis", CategoriaLocal.ARABE, 4);

    @BeforeClass
    public static void setup(){
        repoLocales.setFuente(coleccionMock);
    }

    @Before
    public void fixture(){
        coleccionMock.borrarTodo();
        repoLocales.agregar(veganoSinPedidos);
        repoLocales.agregar(pizzeria2Pedidos);
        repoLocales.agregar(pizzeria3Pedidos);
        repoLocales.agregar(arabe4Pedidos);
    }

    @Test
    public void repoNoTieneLocalesDeMas(){
        assertEquals(4, repoLocales.getAll().size());
    }

    @Test
    public void topsSeArmanBien(){
        Function<Integer, List<Integer>> listarNElementos =  (cantElems ->
            IntStream.range(0, cantElems).boxed().collect(Collectors.toList()));

        listarNElementos.apply(5).forEach(
            cantElems -> {
                List<Integer> menorAlLimite = listarNElementos.apply(cantElems);
                assertEquals(menorAlLimite, homeController.armarTop(menorAlLimite));
            }
        );

        assertEquals(listarNElementos.apply(5), homeController.armarTop(listarNElementos.apply(6)));
    }

    @Test
    public void pedidosPorCategoriaSeCalculanBien(){
        Map<CategoriaLocal, Integer> pedidosPorCategoria = homeController.pedidosPorCategoria();
        assertEquals(3, pedidosPorCategoria.size());
        assertEquals(0, pedidosPorCategoria.get(CategoriaLocal.VEGANO).intValue());
        assertEquals(4, pedidosPorCategoria.get(CategoriaLocal.ARABE).intValue());
        assertEquals(5, pedidosPorCategoria.get(CategoriaLocal.PIZZERIA).intValue());
    }

    @Test
    public void rankingDeLocalesSeArmaBien(){
        assertEquals(
            lista(arabe4Pedidos, pizzeria3Pedidos, pizzeria2Pedidos, veganoSinPedidos)
            , homeController.rankingLocales()
        );
    }

    @Test
    public void rankingDeCategoriasSeArmaBien(){
        assertEquals(lista(CategoriaLocal.PIZZERIA, CategoriaLocal.ARABE, CategoriaLocal.VEGANO)
            , homeController.rankingCategorias()
                .stream()
                .map(Modelos::unparseEnum)
                .map(CategoriaLocal::valueOf)
                .collect(Collectors.toList()));
    }

    @AfterClass
    public static void close(){
        repoLocales.setFuente(new DB<>(Local.class));
    }

    @SafeVarargs
    private final <T> List<T> lista(T... elems){
        return new ArrayList<T>(Arrays.asList(elems));
    }
}
