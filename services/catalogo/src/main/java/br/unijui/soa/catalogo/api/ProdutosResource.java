package br.unijui.soa.catalogo.api;

import br.unijui.soa.catalogo.domain.Produto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.*;

/**
 *
 * @author lorij
 */
@Path("/api/produtos")
@Produces(MediaType.APPLICATION_JSON)
public class ProdutosResource {

    private static final Map<String, Produto> DB = new HashMap<>();

    static {
        DB.put("P001", new Produto("P001", "Monitor 27", 1799.90));
        DB.put("P002", new Produto("P002", "Teclado Mecânico", 399.00));
    }

    @GET
    public Collection<Produto> listar() {
        return DB.values();
    }

    @GET
    @Path("/{id}")
    public Produto obter(@PathParam("id") String id) {
        var p = DB.get(id);
        if (p == null) {
            throw new NotFoundException("Produto não encontrado");
        }
        return p;
    }
}
