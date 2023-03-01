package games.web.gameoflife;

import java.util.Random;

public class GameOfLife {

    public static int[][] generateBoard(int rows, int cols) {
        int[][] board = new int[rows][cols];
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = rand.nextInt(2);
            }
        }
        return board;
    }

    public static int[][] getNextGeneration(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        int[][] nextGen = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int liveNeighbors = countLiveNeighbors(board, i, j);
                if (board[i][j] == 1 && (liveNeighbors == 2 || liveNeighbors == 3)) {
                    nextGen[i][j] = 1;
                } else if (board[i][j] == 0 && liveNeighbors == 3) {
                    nextGen[i][j] = 1;
                } else {
                    nextGen[i][j] = 0;
                }
            }
        }
        return nextGen;
    }

    public static int countLiveNeighbors(int[][] board, int row, int col) {
        int count = 0;
        int rows = board.length;
        int cols = board[0].length;
        int[][] offsets = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
        for (int[] offset : offsets) {
            int r = row + offset[0];
            int c = col + offset[1];
            if (r >= 0 && r < rows && c >= 0 && c < cols && board[r][c] == 1) {
                count++;
            }
        }
        return count;
    }
}

