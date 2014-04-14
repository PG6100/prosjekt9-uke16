package no.nith.pg6100;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class BookResourceTest extends AbstractBaseTest {

    private WebTarget target;

    @Before
    public void setup() {
        super.setup();
        target = ClientBuilder.newClient().target(Server.BASE_URI);
    }

    @Test
    public void testServerRepliesWithBookTitle() throws Exception {
        assertEquals("BOOK:Enterprise_programming", target.path("/books/reply/Enterprise_programming").request().get(String.class));
    }

    @Test
    public void testSavesBook() {
        MultivaluedMap<String, String> params = new MultivaluedHashMap<String, String>();
        params.put("title", Arrays.asList("Enterprise programmering"));
        params.put("author",Arrays.asList("Goncalo"));
        params.put("isbn",Arrays.asList("123-456-789"));
        assertEquals("ok", target.path("/books/saveBook").request().post(Entity.entity(params,MediaType.APPLICATION_FORM_URLENCODED), String.class));
    }
}
