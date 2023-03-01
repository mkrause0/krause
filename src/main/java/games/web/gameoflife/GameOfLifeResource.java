package games.web.gameoflife;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("gameoflife")
public class GameOfLifeResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getGameOfLife() {
        StringBuilder html = new StringBuilder();
        html.append("<html><body><table>");
        int[][] board = GameOfLife.generateBoard(20, 20);
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
