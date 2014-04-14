package no.nith.pg6100;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static org.junit.Assert.assertEquals;

public class HelloWorldTest extends AbstractBaseTest {
    private WebTarget target;

    @Before
    @Override
    public void setup() {
        super.setup();
        target = ClientBuilder.newClient().target(Server.BASE_URI);
    }

    @Test
    public void testSaysHelloWorld() throws Exception {
        assertEquals("Hello world!", target.path("/hello").request().get(String.class));
    }

    @Test
    public void testRepliesWithMyName() throws Exception {
        assertEquals("Hello naimdjon!", target.path("/hello/naimdjon").request().get(String.class));
    }

}
