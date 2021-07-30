package Controladores.Locales;

import Controladores.Autenticador;
import Controladores.Utils.Modelo;
import Local.Duenio;
import Pedidos.EstadoPedido;
import Pedidos.Pedido;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static Controladores.Utils.Modelos.parseModel;

public class PedidosLocalController {
    private Autenticador<Duenio> autenticador;
    public PedidosLocalController(Autenticador<Duenio> autenticador){
        this.autenticador=autenticador;
    }

    public ModelAndView getPedidos(Request req, Response res) {
        Duenio duenio = autenticador.getUsuario(req);

        LocalDate fechaInicio = getFechaParam("fechaInicio", req).orElse(LocalDate.MIN);
        LocalDate fechaFin = getFechaParam("fechaFin", req).orElse(LocalDate.MAX);

        List<Pedido> pedidos = duenio.getLocal().pedidosEntreFechas(fechaInicio, fechaFin);

        return new ModelAndView(
            new Modelo("pedidos", parseModel(pedidos)).con(armarEstadisticas(pedidos))
            , "pedidos-local.html.hbs"
        );
    }

    private Optional<LocalDate> getFechaParam(String param, Request req){
        return Optional
            .ofNullable(req.queryParams(param))
            .map(LocalDate::parse);
    }

    private Modelo armarEstadisticas(List<Pedido> pedidos) {
        int totales = pedidos.size();

        List<Pedido> entregados = pedidosEnEstado(pedidos, EstadoPedido.ENTREGADO);

        long cantidadRechazados = contarEnEstado(pedidos, EstadoPedido.RECHAZADO);

        return new Modelo("cantidadTotal", totales)
            .con("gananciaTotal", entregados.stream().mapToDouble(Pedido::getImporte).sum())
            .con("cantidadConfirmados", contarEnEstado(pedidos, EstadoPedido.CONFIRMADO))
            .con("cantidadEntregados", entregados.size())
            .con("cantidadRechazados", cantidadRechazados)
            .con("cantidadSinResponder", contarEnEstado(pedidos, EstadoPedido.PENDIENTE))
            .con("porcentajeEntregados", (double) entregados.size()/totales)
            .con("porcentajeRechazados", (double) cantidadRechazados/totales)
            ;

    }

    private List<Pedido> pedidosEnEstado(List<Pedido> pedidos, EstadoPedido estado){
        return pedidos.stream().filter(p->p.getEstado()==estado).collect(Collectors.toList());
    }
    private long contarEnEstado(List<Pedido> pedidos, EstadoPedido estado){
        return pedidosEnEstado(pedidos, estado).size();
    }

    public ModelAndView getPedido(Request request, Response response) {
        Duenio duenio = autenticador.getUsuario(request);

        try {
            int nroPedido = Integer.parseInt(request.params("nroPedido"));
            Pedido pedido = duenio.getLocal().getPedidosRecibidos().get(nroPedido - 1);
            return new ModelAndView(parseModel(pedido).con("numero", nroPedido), "pedido-local.html.hbs");

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            response.status(HttpURLConnection.HTTP_NOT_FOUND);
            response.redirect("/pedidos");
            return null;
        }
    }

    public ModelAndView cambiarEstadoPedido(Request req, Response response) {
        List<Pedido> pedidos = autenticador.getUsuario(req).getLocal().getPedidosRecibidos();

        getNumeroPedido(req, response).ifPresent(
            nroPedido->{
                try {
                    EstadoPedido estado = EstadoPedido.valueOf(req.queryParams("decisionPedido"));
                    pedidos.get(nroPedido - 1).setEstado(estado);
                } catch (NullPointerException | IllegalArgumentException e) {
                    response.status(HttpURLConnection.HTTP_BAD_REQUEST);
                }
                response.redirect("/pedidos/" + nroPedido);
            }
        );

        return null;
    }

    private Optional<Integer> getNumeroPedido(Request req, Response res) {
        try {
            int nroPedido = Integer.parseInt(req.params("nroPedido"));
            return Optional.of(nroPedido);
        } catch (NumberFormatException e) {

            res.status(HttpURLConnection.HTTP_BAD_REQUEST);
            res.redirect("/pedidos");
            return Optional.empty();
        }
    }
}
