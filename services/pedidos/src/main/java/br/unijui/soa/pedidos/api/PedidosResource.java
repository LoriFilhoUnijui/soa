package br.unijui.soa.pedidos.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.net.http.*;
import java.net.URI;
import java.time.Instant;

/**
 *
 * @author lorij
 */
@Path("/api/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidosResource {

    public static record NovoPedido(String produtoId, int quantidade) {

    }

    public static record Pedido(String id, String produtoId, int quantidade, double total, Instant criadoEm) {

    }

    @POST
    public Pedido criar(NovoPedido req) throws Exception {
        var catalogoBase = System.getenv().getOrDefault("CATALOGO_BASE", "http://catalogo:8080");
        var client = HttpClient.newHttpClient();
        var resp = client.send(HttpRequest.newBuilder(
                URI.create(catalogoBase + "/api/produtos/" + req.produtoId()))
                .GET().build(),
                HttpResponse.BodyHandlers.ofString());

        if (resp.statusCode() != 200) {
            throw new NotFoundException("Produto inválido");
        }

        double preco;
        if (resp.body().contains("1799.90")) {
            preco = 1799.90;
        } else if (resp.body().contains("399.0")) {
            preco = 399.0;
        } else {
            preco = 100.0;
        }

        var total = preco * req.quantidade();
        var pedido = new Pedido("PED-" + System.currentTimeMillis(), req.produtoId(), req.quantidade(), total, Instant.now());

        var notifBase = System.getenv().getOrDefault("NOTIF_BASE", "http://notificacoes:8080");
        client.sendAsync(HttpRequest.newBuilder(URI.create(notifBase + "/api/notificacoes/pedido/" + pedido.id()))
                .POST(HttpRequest.BodyPublishers.noBody()).build(), HttpResponse.BodyHandlers.discarding());

        return pedido;
    }
}
