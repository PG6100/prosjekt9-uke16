package no.nith.pg6100;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/hello")
public class HelloWorldResource {

    @GET
    public String sayHello() {
        return "Hello world!";
    }

    @GET
    @Path("{name}")
    public String replyToName(@PathParam("name")String name) {
        return "Hello "+name+"!";
    }
}
