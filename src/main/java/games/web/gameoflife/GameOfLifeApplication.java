package games.web.gameoflife;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/gameoflife")
public class GameOfLifeApplication extends ResourceConfig {
    public GameOfLifeApplication() {
        packages("games.web.gameoflife");
    }
}
