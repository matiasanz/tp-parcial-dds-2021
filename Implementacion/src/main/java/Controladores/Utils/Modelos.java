package Controladores.Utils;
import Local.Local;
import MediosContacto.Notificacion;
import Pedidos.*;
import Platos.Combo;
import Platos.Plato;
import Platos.PlatoSimple;
import Usuarios.Cliente;
import com.sun.xml.internal.ws.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import Local.CategoriaLocal;

public interface Modelos {

    static List<String> getCategorias(){
        return Arrays.stream(CategoriaLocal.values())
            .map(Modelos::parseModel)
            .collect(Collectors.toList());
    }

    static String parseModel(Enum<?> unEnum){
        String string = unEnum.toString().toLowerCase().replace('_', ' ');
        return StringUtils.capitalize(string);
    }

    static String unparseEnum(String s){
        return s.toUpperCase()
            .replace(' ', '_')
            .replace('%', '_');
    }

    static Modelo parseModel(Cliente cliente){
        return new Modelo("mailCliente", cliente.getMail())
            .con("categoriaCliente", cliente.getCategoria().getNombre())
            .con("username", cliente.getUsername())
            .con("direcciones", cliente.getDireccionesConocidas())
            .con("apellidoCliente", cliente.getApellido())
            .con("nombreCliente", cliente.getNombre())
            .con("notificaciones", cliente.getNotificacionesPush())
        ;
    }


    static Modelo parseModel(Local local){
        return new Modelo("nombre", local.getNombre())
            .con("idLocal", local.getId())
            .con("categoriaLocal", parseModel(local.getCategoria()))
            .con("Platos", local.getMenu().stream().map(Modelos::parseModel).collect(Collectors.toList()))
            .con("Direccion", local.getDireccion())
            .con("puntuacion", local.getPuntuacionMedia())
            .con("precioPromedio", local.promedioDePrecios())
        ;
    }

    static Modelo parseModel(Plato plato){
        Modelo modelo = new Modelo("nombre", plato.getNombre())
            .con("precio", plato.getPrecio())
            .con("precioBase", plato.getPrecioBase())
            .con("idPlato", plato.getId())
            .con("descripcion", textoOpcional(plato.getDescripcion()))
            .con("tieneDescuento", plato.getDescuento()>0)
            .con("descuentoPlato", plato.getDescuento()*100.0)
        ;

        if(plato instanceof Combo){
            modelo.con("componentes", parseComponentes(((Combo) plato).getPlatos()));
        } else if(plato instanceof PlatoSimple){
            modelo.con("ingredientes", String.join(", ", ((PlatoSimple) plato).getIngredientes()));
        }

        return modelo;
    }

    static List<Modelo> parseComponentes(List<Plato> componentes){
        List<Modelo> modelos = new LinkedList<>();

        componentes
            .stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .forEach((componente, cantidad)-> {
                Modelo modeloComponente = parseModel(componente).con("cantidad", cantidad);
                modelos.add(modeloComponente);
            });

        return modelos;
    }

    static Modelo parseModel(Carrito carrito){
        String direccion = carrito.getDireccion();

        return new Modelo("local", carrito.getLocal().getNombre())
            .con("idLocal"       , carrito.getLocal().getId())
            .con("direccionCarrito", direccion)
            .con("items"         , carrito.getItems().stream().map(Modelos::parseModel).collect(Collectors.toList()))
            .con("precioBase"    , carrito.getPrecioBase())
            .con("dtoCategoria"  , carrito.descuentoPorCategoria())
            ;
    }

    static Modelo parseModel(Item item){
        return new Modelo("plato", item.getPlato().getNombre())
            .con("aclaraciones", textoOpcional(item.getAclaraciones()))
            .con("cantidad", item.getCantidad())
            .con("precioUnitario", item.precioUnitario())
            .con("precioTotal", item.getPrecio())
            .con("idPlato", item.getPlato().getId())
            ;
    }

    static Modelo parseModel(Pedido pedido){
        return new Modelo("local", pedido.getLocal().getNombre())
            .con("importe", pedido.getPrecioAbonado())
            .con("precioBase", pedido.precioBase())
            .con("items", pedido.getItems().stream().map(Modelos::parseModel).collect(Collectors.toList()))
            .con("estado", parseModel(pedido.getEstado()))
            .con(parseModel(pedido.getFechaInicio()))
            .con("direccion", pedido.getDireccion())
            .con("pendiente", pedido.getEstado()==EstadoPedido.PENDIENTE)
            .con("confirmado", pedido.getEstado()==EstadoPedido.CONFIRMADO)
            .con("entregado", pedido.getEstado()==EstadoPedido.ENTREGADO)
            .con("puntuacionPedido", pedido.getPuntuacion())
            .con("detallePuntuacion", textoOpcional(pedido.getDetallePuntuacion()))
            .con("idLocal", pedido.getLocal().getId())
            .con("descuentoPedido", pedido.getDescuento())
            ;
    }

    static List<Modelo> parseModel(List<Pedido> pedidos){
        int[] x = {1};
        List<Modelo> modelos = pedidos
            .stream()
            .map(Modelos::parseModel)
            .map(m -> m.con("numero", x[0]++))
            .collect(Collectors.toList());

        Collections.reverse(modelos);
        return modelos;
    }

    static Modelo parseModel(LocalDateTime fechaHora){
        return new Modelo(
            "fecha", conDosDigitos(fechaHora.getDayOfMonth())
                    +"/"+ conDosDigitos(fechaHora.getMonthValue())
                    +"/"+fechaHora.getYear())
        .con("hora", conDosDigitos(fechaHora.getHour())
                +":"+fechaHora.getMinute()
        );
    }

    static String conDosDigitos(Integer numero){
        String dosDigitos = numero.toString();
        if(dosDigitos.length()<2) dosDigitos = "0"+dosDigitos;

        return dosDigitos;
    }

    static Modelo parseModel(Notificacion notificacion) {
        return new Modelo("asunto", notificacion.getAsunto())
            .con("cuerpo", notificacion.getCuerpo())
            .con(parseModel(notificacion.getFechaHora()))
        ;
    }

    static String textoOpcional(String s){
        return ifEmpty(s, "-");
    }

    static String ifEmpty(String actual, String defaultValue){
        return actual==null || actual.isEmpty()? defaultValue: actual;
    }
    static <T> T ifNull(T object, T defaultValue){
        return (object==null)? defaultValue: object;
    }
}
