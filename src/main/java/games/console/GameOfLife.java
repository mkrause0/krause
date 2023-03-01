package games.console;

import java.util.Random;

public class GameOfLife {
    private int rows;
    private int columns;
    private boolean[][] cells;

    public GameOfLife(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.cells = new boolean[rows][columns];
    }

    public void initializeRandom() {
        Random random = new Random();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                cells[row][column] = random.nextBoolean();
            }
        }
    }

    public void update() {
        boolean[][] newCells = new boolean[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int count = countLiveNeighbors(row, column);

                if (cells[row][column]) {
                    newCells[row][column] = count == 2 || count == 3;
                } else {
                    newCells[row][column] = count == 3;
                }
            }
        }

        cells = newCells;
    }

    private int countLiveNeighbors(int row, int column) {
        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int r = row + i;
                int c = column + j;

                if (r >= 0 && r < rows && c >= 0 && c < columns && !(i == 0 && j == 0)) {
                    if (cells[r][c]) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public void print() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                System.out.print(cells[row][column] ? "X " : "- ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        GameOfLife game = new GameOfLife(50, 50);
        game.initializeRandom();
        game.print();

        while(true) {
        	Thread.sleep(100);
//        for (int i = 0; i < 10; i++) {
            game.update();
            game.print();
        }
    }
}
