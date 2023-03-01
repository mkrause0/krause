package games.web.gameoflife;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/")
public class GameOfLifeResource {
    private GameOfLife game;
    private Thread gameThread;
    private boolean isRunning;
    
    public static final int UPDATE_GRID_TIME_IN_MILLIS = 100;
    public static final int PAGE_RELOAD_TIME_IN_MILLIS = 100;

    public GameOfLifeResource() {
        game = new GameOfLife();
        isRunning = false;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getGameOfLife() {
        StringBuilder sb = new StringBuilder();

        sb.append("<html>"
        		+ "<head>"
//        		+ "<meta http-equiv=\"refresh\" content=\"0."+(TIME_IN_MILLIS/100)+"\">"
        		+ "<script src=\"https://code.jquery.com/jquery-3.5.1.min.js\"></script>"
        		+ "<script>"
        		+ "	$(document).ready(function() {"
        		+ "		setInterval(function() {"
        		+ "			window.location.reload();"
        		+ "		}, "+PAGE_RELOAD_TIME_IN_MILLIS+");"
        		+ "	});"
        		+ "	function start() {"
        		+ "		$.get(\"/gameoflife/start\");"
        		+ "	}"
        		+ "	function stop() {"
        		+ "		$.get(\"/gameoflife/stop\");"
        		+ "	}"
        		+ "</script>"
				+ "</head>"
        		+ "<body><table>");

        for (int i = 0; i < GameOfLife.ROWS; i++) {
            sb.append("<tr>");
            for (int j = 0; j < GameOfLife.COLUMNS; j++) {
                sb.append("<td style=\"width:10px;height:10px;background-color:");
                sb.append(game.getCell(i, j) ? "black" : "orange");
                sb.append("\"></td>");
            }
            sb.append("</tr>");
        }

        sb.append("<button onclick=\"start()\">Start</button>"
        		+ "<button onclick=\"stop()\">Stop</button>"
        		+ "</body></html>");

        return sb.toString();
    }

    @GET
    @Path("/start")
    public void start() {
    	System.out.println("start");
    	resetGameOfLife(true);
    }
    
    @GET
    @Path("/stop")
    public void stop() {
    	System.out.println("stop");
    	 isRunning = false;
    }
    
    private void resetGameOfLife(boolean delay) {
        if (gameThread != null) {
            isRunning = false;
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        game.randomizeGrid();
        startGameOfLife();
    }

    private void startGameOfLife() {
        isRunning = true;
        gameThread = new Thread(() -> {
            while (isRunning) {
                try {
                    Thread.sleep(UPDATE_GRID_TIME_IN_MILLIS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                game.updateGrid();
            }
        });
        gameThread.start();
    }
}
