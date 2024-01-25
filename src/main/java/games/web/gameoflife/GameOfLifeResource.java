package games.web.gameoflife;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/")
public class GameOfLifeResource {
    private GameOfLife game;
    private Thread gameThread;
    private boolean isRunning;
    
    private static final int GRID_RELOAD_TIME_IN_MILLIS = 200;
    private static final int MIN_UPDATE_TIME_IN_MILLIS = 0;
    private static final int MAX_UPDATE_TIME_IN_MILLIS = 5000;
    private static final int TIME_IN_MILLIS_CHANGE_FACTOR = 50;
    private static final int INITIAL_UPDATE_TIME_IN_MILLIS = 200;
    private int updateTimeInMillis = INITIAL_UPDATE_TIME_IN_MILLIS;
    

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
        		+ "<script src=\"https://code.jquery.com/jquery-3.5.1.min.js\"></script>"
        		+ "<script>"
        		+ "	function start() {"
        		+ "		$.get(\"/gameoflife/start\");"
        		+ "	}"
        		+ "	function startgosperglidergun() {"
        		+ "		$.get(\"/gameoflife/startgosperglidergun\");"
        		+ "	}"
        		+ "	function startsmiley() {"
        		+ "		$.get(\"/gameoflife/startsmiley\");"
        		+ "	}"
        		+ "	function stop() {"
        		+ "		$.get(\"/gameoflife/stop\");"
        		+ "	}"
        		+ "	function slidercycle() {"
        		+ "		var sliderValue = $(this).val();"
        		+ "		console.log(sliderValue);"
        		+ "		$.get(\"/gameoflife/cycletime/\"+sliderValue);"
        		+"      location.reload();"	
        		+ "	}"
        		+ "	$(document).ready(function() {"
        		+ "		setInterval(function() {"
        		+ "         $.ajax( {"
        		+ "              url: \"gameoflife/grid\","
        		+ "              success: function(result) {"
        		+ "                $(\"#grid\").html(result);"
        		+ "              }"
        		+ "         });"
        		+ "		}, " + GRID_RELOAD_TIME_IN_MILLIS + ");"
        		+ "     $(\"#slidercycle\").on(\"change\", function() {"
        		+ "			var sliderValue = $(this).val();"
        		+ "			$.get(\"/gameoflife/cycletime/\"+sliderValue);"
        		+"      	location.reload();"	
        		+ "     });"
        		+ "})"
        		+ "</script>"
				+ "</head>"
        		+ "<body>"
                + "<button onclick=\"start()\">Start Zufall</button>&nbsp;"
                + "<button onclick=\"startgosperglidergun()\">Start Gosper Glider Gun</button>&nbsp;"
                + "<button onclick=\"startsmiley()\">Start Smiley</button>&nbsp;"
                + "<button onclick=\"stop()\">Stop</button>&nbsp;&nbsp;"
                + "<input id=\"slidercycle\" type=\"range\" min=\""+MIN_UPDATE_TIME_IN_MILLIS+"\" max=\""+MAX_UPDATE_TIME_IN_MILLIS+"\" step=\""+TIME_IN_MILLIS_CHANGE_FACTOR+"\" value=\""+updateTimeInMillis+"\">"
                + "Zyklenzeit in ms: " + updateTimeInMillis
				+ "<div id=\"grid\"></div>"
        		+ "</body>"
        		+ "</html>");

        return sb.toString();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/grid")    
    public String grid() {
    	StringBuilder sb = new StringBuilder();
        sb.append("<table>");
    	
        for (int i = 0; i < GameOfLife.ROWS; i++) {
	        sb.append("<tr>");
	        for (int j = 0; j < GameOfLife.COLUMNS; j++) {
	            sb.append("<td style=\"width:10px;height:10px;background-color:");
	            sb.append(game.getCell(i, j) ? "black" : "orange");
	            sb.append("\"></td>");
	        }
	        sb.append("</tr>");
	    }

        sb.append("</table>");
        
        return sb.toString();

    }
    
    @GET
    @Path("/start")
    public void start() {
    	if(!isRunning)
    	{
    		resetGameOfLife();
            game.randomizeGrid();
            startGameOfLife();
    	}
    }
    
    @GET
    @Path("/startgosperglidergun")
    public void startgosperglidergun() {
    	if(!isRunning)
    	{
    		resetGameOfLife();
            game.gosperGliderGun();
            startGameOfLife();
    	}
    }
    
    @GET
    @Path("/startsmiley")
    public void startsmiley() {
    	if(!isRunning)
    	{
    		resetGameOfLife();
            game.smiley();
            startGameOfLife();
    	}
    }
    
    @GET
    @Path("/stop")
    public void stop() {
    	 isRunning = false;
    }
    
    @GET
    @Path("/cycletime/{cycletime}")
    public void cycletime(@PathParam("cycletime") String cycletime) {
    	updateTimeInMillis = Integer.valueOf(cycletime);
    	
    	if (updateTimeInMillis < MIN_UPDATE_TIME_IN_MILLIS) {
			updateTimeInMillis = MIN_UPDATE_TIME_IN_MILLIS;
		}
    	
    	if (updateTimeInMillis > MAX_UPDATE_TIME_IN_MILLIS) {
			updateTimeInMillis = MAX_UPDATE_TIME_IN_MILLIS;
		}
    }
    
    private void resetGameOfLife() {
        if (gameThread != null) {
            isRunning = false;
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void startGameOfLife() {
        isRunning = true;
        gameThread = new Thread(() -> {
            while (isRunning) {
                try {
                    Thread.sleep(updateTimeInMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                game.updateGrid();
            }
        });
        gameThread.start();
    }
}
