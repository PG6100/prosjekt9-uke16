package no.nith.pg6100;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Server {

    public static final String BASE_URI = "http://localhost:8080/ep2/";

    public static void main(String[] args) {
        final HttpServer server = startServer();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.shutdownNow();
    }

    public static HttpServer startServer() {
        final ResourceConfig resourceConfig = new ResourceConfig().packages(true, "no.nith.pg6100");
        return GrizzlyHttpServerFactory
                .createHttpServer(URI.create(BASE_URI), resourceConfig);
    }
}
