package no.nith.pg6100;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Path("/books")
public class BookResource {
    static{
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Driver was not found in classpath");
        }
    }

    @GET
    @Path("/reply/{title}")
    public String replyWithTitle(@PathParam("title")String title) {
        return "BOOK:"+title;
    }

    @POST
    @Path("/saveBook")
    @Consumes("application/x-www-form-urlencoded")
    public String saveBook(
            @FormParam("title")String title,
            @FormParam("author")String author,
            @FormParam("isbn")String isbn
    ) {
        try {
            final Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/epBOOKS");
            try {
                final PreparedStatement ps = con
                        .prepareStatement("insert into books(title,author,isbn,id) " +
                                "values (?,?,?, next value for book_id_seq)");
                try {
                    ps.setString(1,title);
                    ps.setString(2,author);
                    ps.setString(3,isbn);
                    final int i = ps.executeUpdate();
                    if (i == 1) {
                        return "ok";
                    }
                    System.err.println("Inserted wrong number of rows: "+i);
                } finally {
                    ps.close();
                }
            } finally {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "error";
    }

}
