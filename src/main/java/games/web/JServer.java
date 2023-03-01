package games.web;

import org.glassfish.jersey.server.ResourceConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JServer {

	private static final Logger LOGGER = LogManager.getLogger(JServer.class);
	
    public static void main(String[] args) throws Exception {
        System.setProperty("log4j.configurationFile", "log4j2.xml");

        LOGGER.debug("Starting server...");
        //TODO active log4j
    	
        Server server = new Server(9090);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
//        context.setContextPath("/");
        server.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        ResourceConfig config = new ResourceConfig();
        config.packages("my.package.containing.resources");

        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "my.package.containing.resources");

        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }
}
