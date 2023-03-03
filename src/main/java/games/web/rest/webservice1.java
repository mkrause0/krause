package games.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ws1")
public class webservice1 {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        return "Nachricht von Schnittstelle 1";
    }

}
