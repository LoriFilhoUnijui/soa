package br.unijui.soa.pedidos;

import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.net.URI;

/**
 *
 * @author lorij
 */
public class Main {

    public static void main(String[] args) {
        String baseUri = "http://0.0.0.0:8080/";
        ResourceConfig rc = new ResourceConfig().packages("br.unijui.soa");
        HttpServer server = JdkHttpServerFactory.createHttpServer(URI.create(baseUri), rc);
        System.out.println("Service pedidos running on " + baseUri);
    }
}
