package hw4.game.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hw4.maze.test.Cell;
import hw4.maze.test.CellComponents;
import hw4.maze.test.Grid;
import hw4.maze.test.Row;
import hw4.player.test.Player;

public class Game {

	Grid grid;
	int gridSize = 0;

	public Game(Grid grid) {
		this.grid = grid;
	}
	
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
		
//        System.out.println("Moving cell: Current cell = " + currentCell);
		
		for(int i = 0; i < player.getCurrentRow().getCells().size(); i++)
		{
			if(player.getCurrentRow().getCells().get(i).equals(currentCell))
			{
				index = i;
//                System.out.println("Current cell found at index: " + index);
			}
		}
		
		if(movement.equals(Movement.RIGHT) && currentCell.getRight().equals(CellComponents.APERTURE))
		{
//            System.out.println("Trying to move right");
			if (index + 1 < player.getCurrentRow().getCells().size()) { //need to check that the index + 1 is valid (index is not > than the # of cells in the row)
//                System.out.println("Valid move to the right");
				return player.getCurrentRow().getCells().get(index + 1);
			}
		}
		
		else if (movement.equals(Movement.LEFT) && currentCell.getLeft().equals(CellComponents.APERTURE)) { //checking that if we decrease a cell (move left) that we are within bounds 
//            System.out.println("Trying to move left");
            if (index - 1 >= 0) { 
//                System.out.println("Valid move to the left");
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
		
//        System.out.println("Moving row: Current row = " + currentRow);
		
		for(int i = 0; i < grid.getRows().size(); i++)
		{
			if(grid.getRows().get(i).equals(currentRow))
			{
				index = i;
//                System.out.println("Current row found at index: " + index);
			}
		}
		
		if(movement.equals(Movement.UP) && currentCell.getUp().equals(CellComponents.APERTURE))
		{
            System.out.println("Trying to move up");
			if (index - 1 >= 0) { //same boundary checks as in moveCell... EXCEPT when we are moving up a row, we are decrementing the index!!!
//                System.out.println("Valid move up to row " + (index - 1));
				return grid.getRows().get(index - 1);
			}
			
		}
		
		else if (movement.equals(Movement.DOWN) && currentCell.getUp().equals(CellComponents.APERTURE)) {
			
			System.out.println("Trying to move down");
			if (index + 1 < grid.getRows().size()) { //if we move down we are increasing the index!!!
//				System.out.println("Valid move down to row " + (index + 1));
				return grid.getRows().get(index + 1);
			}
		}
		System.out.println("Move not possible");
		return currentRow;
	}
	
	boolean play(Movement movement, Player player) {
		
		if(movement == null || player == null)
		{
			return false;
		}
		
		if(movement.equals(Movement.RIGHT))
		{
//			System.out.println("Moving right");
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
//			System.out.println("Moving left");
			Cell prevCell = player.getCurrentCell();
			Cell newCell = moveCell(player, movement);
			player.setCurrentCell(newCell);
			
			if (prevCell.getLeft().equals(CellComponents.EXIT)) { //if we reach the exit when we are moving left then return true.
//				System.out.println("But we found the exit! Exiting...");
				return true;
			}
			
			if (prevCell == newCell) { //if cell never changed then movement was incorrect so return false
				return false;
			}
			return true;
		}
		
		if(movement.equals(Movement.UP))
		{
//			System.out.println("Moving up");
			int cellIndex = player.getCurrentRow().getCells().indexOf(player.getCurrentCell());
			
			Row prevRow = player.getCurrentRow();
			
			Row newRow = moveRow(player, movement);
			player.setCurrentRow(newRow);
			
			if (cellIndex >= 0) {
				player.setCurrentCell(newRow.getCells().get(cellIndex));
//				System.out.println("Updated cell to column " + cellIndex + "in the new row");
			}
			if (prevRow == newRow) { //checking if the movement never happened then we return false
				return false;
			}
			
			return true;
		}
			
			
		if(movement.equals(Movement.DOWN))
		{
//			System.out.println("Moving down");
			int cellIndex = player.getCurrentRow().getCells().indexOf(player.getCurrentCell());
			
			Row prevRow = player.getCurrentRow();
			
			Row newRow = moveRow(player, movement);
			player.setCurrentRow(newRow);
			
			if (cellIndex >= 0) {
				player.setCurrentCell(newRow.getCells().get(cellIndex));
//				System.out.println("Updated cell to column " + cellIndex + "in the new row");
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
	
//	public Grid createRandomGrid(int gridSize) {
//		List<Cell> cells = new ArrayList<>();
//		
//		Random randExit = new Random();
//		Random randAperture = new Random();
		
//		for(int i = 0; i < (gridSize*gridSize); i++)
//		{
//			Cell newCell = new Cell(randomCellComponent(), randomCellComponent(), randomCellComponent(), randomCellComponent());
//			
//			if(newCell.getRight() == CellComponents.WALL &&
//					newCell.getLeft() == CellComponents.WALL &&
//					newCell.getDown() == CellComponents.WALL &&
//					newCell.getUp() == CellComponents.WALL )
//			{
//				int open = randAperture.nextInt(4);
//				
//				if(open == 0)
//				{
//					newCell.setRight(CellComponents.APERTURE);
//				}
//				
//				if(open == 1)
//				{
//					newCell.setLeft(CellComponents.APERTURE);
//				}
//				
//				if(open == 2)
//				{
//					newCell.setDown(CellComponents.APERTURE);
//				}
//				
//				if(open == 3)
//				{
//					newCell.setUp(CellComponents.APERTURE);
//				}
//			}
//			
//			cells.add(newCell);
//		}
		
//		PRINT OUT ALL OF THE CELLS
//		for(int i = 0; i < cells.size(); i++)
//		{
//			System.out.println(cells.get(i));
//			System.out.println("Cells Size: " + cells.size());
//		}
		
//		List<Row> rows = new ArrayList<>();
//		int exit = randExit.nextInt(gridSize);
//		
//		for(int i = 0; i < gridSize; i++)
//		{
//			Row row = new Row(new ArrayList<>());
//			List<Cell> rowCells = new ArrayList<>();
//			
//			for(int j = 0; j < gridSize; j++)
//			{
//				Cell addCell = cells.get(i + j);
//				
//				if(i == exit && j == 0)
//				{
//					addCell.setLeft(CellComponents.EXIT);
//				}
//				
//				rowCells.add(addCell);
//			}
//			
//			row.setCells(rowCells);
//			rows.add(row);
//		}
//		
//		for(int i = 0; i < gridSize; i++)
//		{
//			System.out.println(rows.get(i));
//		}
//		
//		Grid randomGrid = new Grid(rows);
//		return randomGrid;
//		
//	}

	@Override
	public String toString() {
		return "Game [grid=" + grid + "]";
	}
	
}
