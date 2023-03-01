package games.web;

import org.glassfish.jersey.server.ResourceConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class JServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");
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


