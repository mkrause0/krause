package games.web.gameoflife;

import java.util.Random;

public class GameOfLife {
    private boolean[][] grid;
    public static final int ROWS = 70;
    public static final int COLUMNS = 120;

    public int getRows() {
		return ROWS;
	}

	public int getColumns() {
		return COLUMNS;
	}

	public GameOfLife() {
        this.grid = new boolean[ROWS][COLUMNS];
    }

	public void printGrid() {
	    for (int row = 0; row < ROWS; row++) {
	        for (int col = 0; col < COLUMNS; col++) {
	            System.out.print(grid[row][col] ? "X " : ". ");
	        }
	        System.out.println();
	    }
	}
	
    public boolean[][] getGrid() {
        return grid;
    }

    public void setGrid(boolean[][] grid) {
        this.grid = grid;
    }
    
    public boolean getCell(int row, int col) {
        return grid[row][col];
    }

    public void randomizeGrid() {
        Random random = new Random();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                grid[i][j] = random.nextBoolean();
            }
        }
    }

    public void updateGrid() {
        boolean[][] newGrid = new boolean[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                int neighbors = countNeighbors(i, j);
                if (grid[i][j]) {
                    newGrid[i][j] = (neighbors == 2 || neighbors == 3);
                } else {
                    newGrid[i][j] = (neighbors == 3);
                }
            }
        }
        grid = newGrid;
    }

    private int countNeighbors(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int row = (x + i + ROWS) % ROWS;
                int column = (y + j + COLUMNS) % COLUMNS;
                if (grid[row][column] && !(i == 0 && j == 0)) {
                    count++;
                }
            }
        }
        return count;
    }
}
