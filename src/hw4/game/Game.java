package hw4.game;

import java.util.ArrayList;
import java.util.Random;

import hw4.maze.test.Cell;
import hw4.maze.test.CellComponents;
import hw4.maze.test.Grid;
import hw4.maze.test.Row;
import hw4.player.Movement;
import hw4.player.test.Player;

public class Game {

	Grid grid;
	int gridSize = 0;

	/**
	 * Parameterized constructor using a given grid.
	 * @param grid
	 *    This argument represents the grid which will be used to play the game.
	 * */
	public Game(Grid grid) {
		this.grid = grid;
		this.gridSize = grid.getRows().size();	
	}
	
	/**
	 * Parameterized constructor using an integer to generate a random
	 * grid with the same width and length.
	 * @param gridSize
	 *    This argument represents the size of both the length and width of the grid.
	 * */
	public Game(int gridSize)
	{
		this.gridSize = gridSize;
		this.grid = createRandomGrid(gridSize);
	}

	/**
	 * Gets the grid of the game.
	 * */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * Sets the grid for the game.
	 * */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	/**
	 * Attempts to move the cell of a player
	 * @param player
	 *    This argument represents the player of a game.
	 * @param movement
	 *    This argument represents the move the player is trying to make.
	 * */
	public Cell moveCell(Player player, Movement movement)
	{
		// Get players current cell
		Cell currentCell = player.getCurrentCell();
		int index = 0;
		
		// Get the index of the current cell
		for(int i = 0; i < player.getCurrentRow().getCells().size(); i++)
		{
			if(player.getCurrentRow().getCells().get(i).equals(currentCell))
			{
				index = i;
			}
		}
		
		// Move to the right / change cell, if right movement allowed
		if(movement.equals(Movement.RIGHT) && currentCell.getRight().equals(CellComponents.APERTURE))
		{
			if (index + 1 < player.getCurrentRow().getCells().size()) { //need to check that the index + 1 is valid (index is not > than the # of cells in the row)
				return player.getCurrentRow().getCells().get(index + 1);
			}
		}
		
		// Move to the left / change cell, if left movement allowed
		else if (movement.equals(Movement.LEFT) && currentCell.getLeft().equals(CellComponents.APERTURE)) { //checking that if we decrease a cell (move left) that we are within bounds 
            if (index - 1 >= 0) { 
    			return player.getCurrentRow().getCells().get(index - 1);
            }
		}
		System.out.println("Move not possible");
		return currentCell; //return the same cell if we can't move
		
	}
	
	/**
	 * Attempts to move the row of the player.
	 * @param player
	 *    This argument represents the player of the game.
	 * @param movement
	 *    This argument represents the move the player is trying to make.
	 * */
	public Row moveRow(Player player, Movement movement)
	{
		// Get the current row and cell of the player
		Row currentRow = player.getCurrentRow();
		Cell currentCell = player.getCurrentCell();
		int index = 0;
		
		// Find the index of the current cell of the player
		for(int i = 0; i < grid.getRows().size(); i++)
		{
			if(grid.getRows().get(i).equals(currentRow))
			{
				index = i;
			}
		}
		
		// Move up a row / change row if movement is allowed
		if(movement.equals(Movement.UP) && currentCell.getUp().equals(CellComponents.APERTURE))
		{
            System.out.println("Trying to move up");
			if (index - 1 >= 0) { //same boundary checks as in moveCell... EXCEPT when we are moving up a row, we are decrementing the index!!!
				return grid.getRows().get(index - 1);
			}
			
		}
		// Move down a row / change row if movement is allowed
		else if (movement.equals(Movement.DOWN) && currentCell.getUp().equals(CellComponents.APERTURE)) {
			
			System.out.println("Trying to move down");
			if (index + 1 < grid.getRows().size()) { //if we move down we are increasing the index!!!
				return grid.getRows().get(index + 1);
			}
		}
		System.out.println("Move not possible");
		return currentRow; // or return the current row
	}
	
	/**
	 * This function is how the player can move through the grid.
	 * @param player
	 *    This argument represents the player playing the game.
	 * @param movement
	 *    This argument represents the movement the user is trying to make.
	 * */
	public boolean play(Movement movement, Player player) {
		
		// If either movement or player null we cannot play
		if(movement == null || player == null)
		{
			return false;
		}
		
		// Attempt to move right
		if(movement.equals(Movement.RIGHT))
		{
			Cell prevCell = player.getCurrentCell(); //getting the cell before it gets changed
			Cell newCell = moveCell(player, movement);
			player.setCurrentCell(newCell);
			if (prevCell == newCell) { //if the cell never changed and the movement was incorrect then we need to return false!!!
				return false; 
			}
			
			visualize(player);
			return true;
		}
		
		// Attempt to move left
		if(movement.equals(Movement.LEFT))
		{
			Cell prevCell = player.getCurrentCell();
			Cell newCell = moveCell(player, movement);
			player.setCurrentCell(newCell);
			
			if (prevCell.getLeft().equals(CellComponents.EXIT)) { //if we reach the exit when we are moving left then return true.
				return true;
			}
			
			if (prevCell == newCell) { //if cell never changed then movement was incorrect so return false
				return false;
			}
			visualize(player);
			return true;
		}
		
		// Attempt to move up
		if(movement.equals(Movement.UP))
		{
			int cellIndex = player.getCurrentRow().getCells().indexOf(player.getCurrentCell());
			
			Row prevRow = player.getCurrentRow();
			
			Row newRow = moveRow(player, movement);
			player.setCurrentRow(newRow);
			
			if (cellIndex >= 0) {
				player.setCurrentCell(newRow.getCells().get(cellIndex));
			}
			if (prevRow == newRow) { //checking if the movement never happened then we return false
				return false;
			}
			visualize(player);
			return true;
		}
			
		// Attempt to move down
		if(movement.equals(Movement.DOWN))
		{
			int cellIndex = player.getCurrentRow().getCells().indexOf(player.getCurrentCell());
			
			Row prevRow = player.getCurrentRow();
			
			Row newRow = moveRow(player, movement);
			player.setCurrentRow(newRow);
			
			if (cellIndex >= 0) {
				player.setCurrentCell(newRow.getCells().get(cellIndex));
			}
			if (prevRow == newRow) { //checking for unchanged movement then we return false
				return false;
			}
			visualize(player);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Randomly generates either a WALL or APERTURE CellComponents enum.
	 * */
	public CellComponents randomCellComponent() {
		
		// Instantiate random number
		Random random = new Random();
		
		// Get random number
		int randComponent = random.nextInt(100);
		
		// If divisible by two then APERTURE
		if(randComponent % 2 == 0)
		{
			return CellComponents.APERTURE;
		}	
		
		// Otherwise WALL
		return CellComponents.WALL;
	}
	
	/**
	 * Creates a random grid to the users liking.
	 * @param gridSize
	 *    This argument represents the width and the length of the grid.
	 * */
	public Grid createRandomGrid(int gridSize) {
		
		// Grid must stay within these bounds
		if(gridSize < 3 || gridSize > 7)
		{
			return null;
		}
		
		///INITIALLIZING VARIABLEES////////
		ArrayList<Cell> cells = new ArrayList<>();
		
		Random randExit = new Random();
		Random randAperture = new Random();
		///////////////////////////////////
		
		// Create n x n cells
		for(int i = 0; i < (gridSize*gridSize); i++)
		{
			// All cells but the left component will have random component enumeration (only WALL or APERTURE)
			Cell newCell = new Cell(randomCellComponent(), randomCellComponent(), randomCellComponent(), CellComponents.WALL);
			
			// If all cell components are WALL then randomly change one to APERTURE
			if(newCell.getRight() == CellComponents.WALL &&
					newCell.getLeft() == CellComponents.WALL &&
					newCell.getDown() == CellComponents.WALL &&
					newCell.getUp() == CellComponents.WALL )
			{
				// Represents the component to change to APERTURE
				int open = randAperture.nextInt(4);
				
				if(open == 0)
				{
					newCell.setRight(CellComponents.APERTURE);
				}
				
				if(open == 1)
				{
					newCell.setLeft(CellComponents.APERTURE);
				}
				
				if(open == 2)
				{
					newCell.setDown(CellComponents.APERTURE);
				}
				
				if(open == 3)
				{
					newCell.setUp(CellComponents.APERTURE);
				}
			}
			
			// Add cells to the cell list
			cells.add(newCell);
		}
		
		// Initialize a list
		ArrayList<Row> rows = new ArrayList<>();
		
		// Add the cells to rows in order
		for(int i = 0; i < gridSize; i++)
		{
			Row row = new Row(new ArrayList<>());
			ArrayList<Cell> rowCells = new ArrayList<>();
			
			for(int j = 0; j < gridSize; j++)
			{
				// Add cells to the rows
				Cell addCell = cells.get((i * gridSize) + j);
				
				if(j == 0) {
					// If the cell is the leftmost cell
					// then set the left component to WALL
					addCell.setLeft(CellComponents.WALL);
				}
				
				rowCells.add(addCell);		
			}
			
			row.setCells(rowCells);
			rows.add(row);
			
		}
		
		// Add the rows to the new grid
		Grid randomGrid = new Grid(rows);
		
		// If left and right cells don't share the same enumeration
		// get a random enumeration and set them to the same enumeration
		for(int i=0; i<randomGrid.getRows().size(); i++) {
			for(int j=0; j< gridSize - 1; j++) {
				if(!randomGrid.getRows().get(i).getCells().get(j).getRight().equals(randomGrid.getRows().get(i).getCells().get(j + 1).getLeft())) {
					CellComponents shareLeftRight = randomCellComponent();
					
					randomGrid.getRows().get(i).getCells().get(j).setRight(shareLeftRight);
					randomGrid.getRows().get(i).getCells().get(j + 1).setLeft(shareLeftRight);
				}		
			}
		}
		
		// If up and down cells don't share the same enumeration
		// get a random enumeration and set them to the same enumeration
		for(int i=0; i<randomGrid.getRows().size(); i++) {
			for(int j = 0; j < gridSize - 1; j++)
			{
				if(!randomGrid.getRows().get(j).getCells().get(i).getDown().equals(randomGrid.getRows().get(j + 1).getCells().get(i).getUp())) {
					CellComponents shareUpDown = randomCellComponent();
					
					randomGrid.getRows().get(j).getCells().get(i).setDown(shareUpDown);
					randomGrid.getRows().get(j + 1).getCells().get(i).setUp(shareUpDown);
				}
			}
		}
		
		// Pick a random row to set the leftmost cell left component to EXIT
		int exit = randExit.nextInt(gridSize);
		randomGrid.getRows().get(exit).getCells().get(0).setLeft(CellComponents.EXIT);
		
		// Return the newly generated grid
		return randomGrid;	
	}
	
	/**
	 * Visualizes the current status of the game.
	 * @param player
	 *    This argument represents the player of the game.
	 * */
	public void visualize(Player player) {
		
		// Warns the user of a visualization
		System.out.println("Current Position: ");
		
		int x = 0, y = 0; // Coordinates of the current cell of the player
		int w = 0, z = 0; // Coordinates of the exit cell of the player
		
		// Finds the matching cell coordinates
		// for the coordinates listed above
		for(int i = 0; i < gridSize; i++)
		{
			for(int j = 0; j < gridSize; j++)
			{
				if(grid.getRows().get(i).getCells().get(j).equals(player.getCurrentCell()))
				{
					x = i;
					y = j;
				}
				
				if(grid.getRows().get(i).getCells().get(j).getLeft().equals(CellComponents.EXIT))
				{
					w = i;
					z = j;
				}
			}
		}
		
		// Print out the current status of agent
		// row by row
		for(int i = 0; i < gridSize; i++)
		{
			// Start with empty string
			String base = "";
			for(int j = 0; j < gridSize; j++)
			{
				// If current position cell print 'A'
				if(i == x && j == y)
				{
					base = base + "\tA";
					continue;
				}
				
				// If exit cell print 'E'
				if(i == w && j == z)
				{
					base = base + "\tE";
					continue;
				}
				
				// Otherwise print 'S'
				base = base + "\tS";
			}
			// Print string of row
			System.out.println(base);
		}
		
		
	}

	@Override
	/**
	 * Prints out the grid information of the game.
	 * */
	public String toString() {
		return "Game [grid=" + grid + "]";
	}
	
}
