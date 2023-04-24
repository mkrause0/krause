package games.web.gameoflife;

import java.util.Random;

public class GameOfLife {
    private boolean[][] grid;
    public static final int ROWS = 74;
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
    
	private void mirrorYAxis(boolean[][] newGrid) {
		for (int i = 0; i < newGrid.length; i++) {
    	    for (int j = 0; j < newGrid[i].length / 2; j++) {
    	        boolean temp = newGrid[i][j];
    	        newGrid[i][newGrid[i].length - 1 - j] = temp;
    	    }
    	}
	}

	private void mirrorXAxis(boolean[][] newGrid) {
		for (int i = 0; i < newGrid.length / 2; i++) {
    	    for (int j = 0; j < newGrid[i].length; j++) {
    	        boolean temp = newGrid[i][j];
    	        newGrid[newGrid.length - 1 - i][j] = temp;
    	    }
    	}
	}
    
    public void gosperGliderGun()
    {
    	boolean[][] newGrid = new boolean[ROWS][COLUMNS];
    	
    	// Initialisiere das Grid mit Nullen
    	for (int i = 0; i < grid.length; i++) {
    	    for (int j = 0; j < grid[i].length; j++) {
    	        newGrid[i][j] = false;
    	    }
    	}

    	// Setze die Zellen für die Gosper-Glider-Kanone
    	gosperGliderGunGrid(newGrid, 9, 0);
    	
    	mirrorXAxis(newGrid);
    	mirrorYAxis(newGrid);
    	
    	grid = newGrid;
    }



	private void gosperGliderGunGrid(boolean[][] grid, int x1, int y1) {
		grid[y1+5][x1+1]  = true;
    	grid[y1+5][x1+2]  = true;
    	grid[y1+6][x1+1]  = true;
    	grid[y1+6][x1+2]  = true;
    	grid[y1+3][x1+13] = true;
    	grid[y1+3][x1+14] = true;
    	grid[y1+4][x1+12] = true;
    	grid[y1+4][x1+16] = true;
    	grid[y1+5][x1+11] = true;
    	grid[y1+5][x1+17] = true;
    	grid[y1+6][x1+11] = true;
    	grid[y1+6][x1+15] = true;
    	grid[y1+6][x1+17] = true;
    	grid[y1+6][x1+18] = true;
    	grid[y1+7][x1+11] = true;
    	grid[y1+7][x1+17] = true;
    	grid[y1+8][x1+12] = true;
    	grid[y1+8][x1+16] = true;
    	grid[y1+9][x1+13] = true;
    	grid[y1+9][x1+14] = true;
    	grid[y1+1][x1+25] = true;
    	grid[y1+2][x1+23] = true;
    	grid[y1+2][x1+25] = true;
    	grid[y1+3][x1+21] = true;
    	grid[y1+3][x1+22] = true;
    	grid[y1+4][x1+21] = true;
    	grid[y1+4][x1+22] = true;
    	grid[y1+5][x1+21] = true;
    	grid[y1+5][x1+22] = true;
    	grid[y1+6][x1+23] = true;
    	grid[y1+6][x1+25] = true;
    	grid[y1+7][x1+25] = true;
    	grid[y1+3][x1+35] = true;
    	grid[y1+3][x1+36] = true;
    	grid[y1+4][x1+35] = true;
    	grid[y1+4][x1+36] = true;
	}
}
