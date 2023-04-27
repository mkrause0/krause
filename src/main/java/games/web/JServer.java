package games.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class JServer {

    public static void main(String[] args) throws Exception {
    	
        Server server = new Server(8080);
        server.setHandler(new ServletContextHandler(ServletContextHandler.NO_SESSIONS));

//        ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
//        jerseyServlet.setInitOrder(0);
//
//        ResourceConfig config = new ResourceConfig();
//        config.packages("my.package.containing.resources");

//        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "my.package.containing.resources");

        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }
}
