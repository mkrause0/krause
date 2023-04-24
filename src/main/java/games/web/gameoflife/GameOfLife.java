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
    	gosperGliderGunGrid(newGrid, 9, 0);
    	mirrorXAxis(newGrid);
    	mirrorYAxis(newGrid);
    	grid = newGrid;
    }

	private void gosperGliderGunGrid(boolean[][] grid, int x, int y) {
		grid[y+5][x+1]  = true;
    	grid[y+5][x+2]  = true;
    	grid[y+6][x+1]  = true;
    	grid[y+6][x+2]  = true;
    	grid[y+3][x+13] = true;
    	grid[y+3][x+14] = true;
    	grid[y+4][x+12] = true;
    	grid[y+4][x+16] = true;
    	grid[y+5][x+11] = true;
    	grid[y+5][x+17] = true;
    	grid[y+6][x+11] = true;
    	grid[y+6][x+15] = true;
    	grid[y+6][x+17] = true;
    	grid[y+6][x+18] = true;
    	grid[y+7][x+11] = true;
    	grid[y+7][x+17] = true;
    	grid[y+8][x+12] = true;
    	grid[y+8][x+16] = true;
    	grid[y+9][x+13] = true;
    	grid[y+9][x+14] = true;
    	grid[y+1][x+25] = true;
    	grid[y+2][x+23] = true;
    	grid[y+2][x+25] = true;
    	grid[y+3][x+21] = true;
    	grid[y+3][x+22] = true;
    	grid[y+4][x+21] = true;
    	grid[y+4][x+22] = true;
    	grid[y+5][x+21] = true;
    	grid[y+5][x+22] = true;
    	grid[y+6][x+23] = true;
    	grid[y+6][x+25] = true;
    	grid[y+7][x+25] = true;
    	grid[y+3][x+35] = true;
    	grid[y+3][x+36] = true;
    	grid[y+4][x+35] = true;
    	grid[y+4][x+36] = true;
	}

	public void smiley() {
    	boolean[][] newGrid = new boolean[ROWS][COLUMNS];
    	
    	for (int x = 0; x < 14*8; x+=8) {
    		for (int y = 0; y < 8*9; y+=9) {
    			smileyGrid(newGrid, x+4, y+2);
			}
		}
    	grid = newGrid;
	}
	
	private void smileyGrid(boolean[][] grid, int x, int y) {
		// smiley
		grid[y][x] = true;
		grid[y][x+1] = true;
		grid[y][x+3] = true;
		grid[y][x+5] = true;
		grid[y][x+6] = true;
		grid[y+1][x+3] = true;
		grid[y+2][x+0] = true;
		grid[y+2][x+6] = true;
		grid[y+3][x+1] = true;
		grid[y+3][x+2] = true;
		grid[y+3][x+3] = true;
		grid[y+3][x+4] = true;
		grid[y+3][x+5] = true;
		// stabilizing cells
		grid[y+6][x+0] = true;
		grid[y+6][x+1] = true;
		grid[y+6][x+2] = true;
		grid[y+6][x+4] = true;
		grid[y+6][x+5] = true;
		grid[y+6][x+6] = true;
	}
}
