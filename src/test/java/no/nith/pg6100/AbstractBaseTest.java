package no.nith.pg6100;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;

import javax.ws.rs.client.ClientBuilder;

public abstract class AbstractBaseTest {
    private HttpServer server;

    @Before
    public void setup() {
        server = Server.startServer();
    }

    @After
    public void tearDown() {
        server.shutdownNow();
    }
}
