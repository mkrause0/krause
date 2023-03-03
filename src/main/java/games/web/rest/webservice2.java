package games.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@Path("/ws2")
public class webservice2 {

    private static final String SCHNITTSTELLE1_URL = "http://localhost:8080/ws/ws1";

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        Client client = ClientBuilder.newClient();
        String message = client.target(SCHNITTSTELLE1_URL)
                                .request(MediaType.TEXT_PLAIN)
                                .get(String.class);
        client.close();
        return "Nachricht von Schnittstelle 2: " + message;
    }

}
