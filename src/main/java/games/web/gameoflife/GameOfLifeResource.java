package games.web.gameoflife;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("gameoflife")
public class GameOfLifeResource {

	int[][] board;
	boolean active = false;
	
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getGameOfLife() {
    	
    	if(!active)
    		board = GameOfLife.generateBoard(10, 10);
    	else
    	{
    		board = GameOfLife.getNextGeneration(board);
    	}
    	
        StringBuilder html = new StringBuilder();
        html.append("<html>"
        		+ "<head><meta http-equiv=\"refresh\" content=\"1\"></head>"
        		+ "<body><table>");
        for (int i = 0; i < board.length; i++) {
            html.append("<tr>");
            for (int j = 0; j < board[i].length; j++) {
                html.append("<td>");
                if (board[i][j] == 1) {
                    html.append("*");
                } else {
                    html.append(".");
                }
                html.append("</td>");
            }
            html.append("</tr>");
        }
        html.append("</table></body></html>");
        return html.toString();
    }
}
