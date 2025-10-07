package br.unijui.soa.notif.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.time.Instant;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author lorij
 */
@Path("/api/notificacoes")
@Produces(MediaType.APPLICATION_JSON)
public class NotificacoesResource {

    private static final CopyOnWriteArrayList<String> LOG = new CopyOnWriteArrayList<>();

    @POST
    @Path("/pedido/{id}")
    public void novoPedido(@PathParam("id") String id) {
        LOG.add("Pedido " + id + " notificado em " + Instant.now());
    }

    @GET
    public Object historico() {
        return LOG;
    }
}
