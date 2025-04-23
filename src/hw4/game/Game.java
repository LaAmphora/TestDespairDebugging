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

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	public Cell moveCell(Player player, Movement movement)
	{
		Cell currentCell = player.getCurrentCell();
		int index = 0;
		
		for(int i = 0; i < player.getCurrentRow().getCells().size(); i++)
		{
			if(player.getCurrentRow().getCells().get(i).equals(currentCell))
			{
				index = i;
			}
		}
		
		if(movement.equals(Movement.RIGHT) && currentCell.getRight().equals(CellComponents.APERTURE))
		{
			if (index + 1 < player.getCurrentRow().getCells().size()) { //need to check that the index + 1 is valid (index is not > than the # of cells in the row)
				return player.getCurrentRow().getCells().get(index + 1);
			}
		}
		
		else if (movement.equals(Movement.LEFT) && currentCell.getLeft().equals(CellComponents.APERTURE)) { //checking that if we decrease a cell (move left) that we are within bounds 
            if (index - 1 >= 0) { 
    			return player.getCurrentRow().getCells().get(index - 1);
            }
		}
		System.out.println("Move not possible");
		return currentCell; //return the same cell if we can't move? I think
		
	}
	
	public Row moveRow(Player player, Movement movement)
	{
		Row currentRow = player.getCurrentRow();
		Cell currentCell = player.getCurrentCell();
		int index = 0;
		
		for(int i = 0; i < grid.getRows().size(); i++)
		{
			if(grid.getRows().get(i).equals(currentRow))
			{
				index = i;
			}
		}
		
		if(movement.equals(Movement.UP) && currentCell.getUp().equals(CellComponents.APERTURE))
		{
            System.out.println("Trying to move up");
			if (index - 1 >= 0) { //same boundary checks as in moveCell... EXCEPT when we are moving up a row, we are decrementing the index!!!
				return grid.getRows().get(index - 1);
			}
			
		}
		
		else if (movement.equals(Movement.DOWN) && currentCell.getUp().equals(CellComponents.APERTURE)) {
			
			System.out.println("Trying to move down");
			if (index + 1 < grid.getRows().size()) { //if we move down we are increasing the index!!!
				return grid.getRows().get(index + 1);
			}
		}
		System.out.println("Move not possible");
		return currentRow;
	}
	
	public boolean play(Movement movement, Player player) {
		
		if(movement == null || player == null)
		{
			return false;
		}
		
		if(movement.equals(Movement.RIGHT))
		{
			Cell prevCell = player.getCurrentCell(); //getting the cell before it gets changed
			Cell newCell = moveCell(player, movement);
			player.setCurrentCell(newCell);
			if (prevCell == newCell) { //if the cell never changed and the movement was incorrect then we need to return false!!!
				return false; 
			}
			return true;
		}
		
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
			return true;
		}
		
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
			
			return true;
		}
			
			
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
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Randomly generates either a WALL or APERTURE CellComponents enum.
	 * */
	public CellComponents randomCellComponent() {
		
		Random random = new Random();
		
		int randComponent = random.nextInt(100);
		
		if(randComponent % 2 == 0)
		{
			return CellComponents.APERTURE;
		}	
		
		return CellComponents.WALL;
	}
	
	public Grid createRandomGrid(int gridSize) {
		
		if(gridSize < 3 || gridSize > 7)
		{
			return null;
		}
		
		ArrayList<Cell> cells = new ArrayList<>();
		
		Random randExit = new Random();
		Random randAperture = new Random();
		
		for(int i = 0; i < (gridSize*gridSize); i++)
		{
			Cell newCell = new Cell(randomCellComponent(), randomCellComponent(), randomCellComponent(), CellComponents.WALL);
			
			if(newCell.getRight() == CellComponents.WALL &&
					newCell.getLeft() == CellComponents.WALL &&
					newCell.getDown() == CellComponents.WALL &&
					newCell.getUp() == CellComponents.WALL )
			{
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
			
			cells.add(newCell);
		}
		
		ArrayList<Row> rows = new ArrayList<>();
		
		for(int i = 0; i < gridSize; i++)
		{
			Row row = new Row(new ArrayList<>());
			ArrayList<Cell> rowCells = new ArrayList<>();
			
			for(int j = 0; j < gridSize; j++)
			{
				Cell addCell = cells.get((i * gridSize) + j);
				
				if(j == 0) {
					addCell.setLeft(CellComponents.WALL);
				}
				
				rowCells.add(addCell);		
			}
			
			row.setCells(rowCells);
			rows.add(row);
			
		}
		
		Grid randomGrid = new Grid(rows);
		
		for(int i=0; i<randomGrid.getRows().size(); i++) {
			for(int j=0; j< gridSize - 1; j++) {
				if(!randomGrid.getRows().get(i).getCells().get(j).getRight().equals(randomGrid.getRows().get(i).getCells().get(j + 1).getLeft())) {
					CellComponents shareLeftRight = randomCellComponent();
					
					randomGrid.getRows().get(i).getCells().get(j).setRight(shareLeftRight);
					randomGrid.getRows().get(i).getCells().get(j + 1).setLeft(shareLeftRight);
				}		
			}
		}
		
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
		
		int exit = randExit.nextInt(gridSize);
		randomGrid.getRows().get(exit).getCells().get(0).setLeft(CellComponents.EXIT);
		
		System.out.println("New Game");

		for(int i = 0; i < gridSize; i++)
		{
			System.out.println(randomGrid.getRows().get(i));
		}
		
		return randomGrid;
		
	}
	
	public void visualize(Player player) {
		
		System.out.println("Current Position: ");
		
		int x = 0, y = 0;
		int w = 0, z = 0;
		
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
		
		for(int i = 0; i < gridSize; i++)
		{
			String base = "";
			for(int j = 0; j < gridSize; j++)
			{
				if(i == x && j == y)
				{
					base = base + "\tA";
					continue;
				}
				
				if(i == w && j == z)
				{
					base = base + "\tE";
					continue;
				}
				
				base = base + "\tS";
			}
			System.out.println(base);
		}
		
		
	}

	@Override
	public String toString() {
		return "Game [grid=" + grid + "]";
	}
	
}
