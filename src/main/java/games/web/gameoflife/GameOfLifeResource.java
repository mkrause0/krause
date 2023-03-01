package games.web.gameoflife;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.POST;

@Singleton
@Path("/gameoflife")
public class GameOfLifeResource {
    private GameOfLife game;
    private Thread gameThread;
    private boolean isRunning;
    
    public static final int TIME_IN_MILLIS = 100;

    public GameOfLifeResource() {
        game = new GameOfLife();
        isRunning = false;
        resetGameOfLife();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getGameOfLife() {
        StringBuilder sb = new StringBuilder();

        sb.append("<html>"
        		+ "<head><meta http-equiv=\"refresh\" content=\"0."+(TIME_IN_MILLIS/100)+"\"></head>"
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

        sb.append("</table><form method=\"POST\" action=\"/reset\"><input type=\"submit\" value=\"Reset\"></form></body></html>");

        return sb.toString();
    }

    @GET
    @Path("/reset")
    public void resetGameOfLife() {
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
                    Thread.sleep(TIME_IN_MILLIS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                game.updateGrid();
            }
        });
        gameThread.start();
    }
}
