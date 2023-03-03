package games.web.rest;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/ws")
public class webserviceApplication extends ResourceConfig {
    public webserviceApplication() {
        packages("games.web.rest");
    }
}
