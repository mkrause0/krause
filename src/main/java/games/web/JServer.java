package games.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import games.web.gameoflife.GameOfLifeApplication;
import games.web.rest.webserviceApplication;

public class JServer {

    public static void main(String[] args) throws Exception {
    	
        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        server.setHandler(context);

        ServletHolder jerseyServletGameOfLife = new ServletHolder(new org.glassfish.jersey.servlet.ServletContainer(new GameOfLifeApplication()));
        jerseyServletGameOfLife.setInitOrder(0);
        context.addServlet(jerseyServletGameOfLife, "/gameoflife/*");

        ServletHolder jerseyServletWebservice = new ServletHolder(new org.glassfish.jersey.servlet.ServletContainer(new webserviceApplication()));
        jerseyServletWebservice.setInitOrder(0);
        context.addServlet(jerseyServletWebservice, "/ws/*");
        
        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }
}
