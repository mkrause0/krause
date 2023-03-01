package games.web;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import games.web.gameoflife.GameOfLifeApplication;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {

	public static void main(String[] args) {
	    Server server = new Server(8080);

	    ServletHandler handler = new ServletHandler();
	    handler.addServletWithMapping(ServletContainer.class, "/*");

	    ResourceConfig config = new ResourceConfig();
	    config.register(GameOfLifeApplication.class);

	    ServletHolder servlet = new ServletHolder(new ServletContainer(config));
	    handler.addServletWithMapping(servlet, "/*");

	    server.setHandler(handler);

	    try {
	        server.start();
	        server.join();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}

