package hw4.game.test;

import java.util.List;

import hw4.maze.test.Cell;
import hw4.maze.test.CellComponents;
import hw4.maze.test.Grid;
import hw4.maze.test.Row;
import hw4.player.test.Player;

public class Game {

	Grid grid;

	public Game(Grid grid) {
		this.grid = grid;
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	Cell moveCell(Player player, Movement movement)
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
		
		if(movement.equals(Movement.RIGHT))
		{
			if (index + 1 < player.getCurrentRow().getCells().size()) { //need to check that the index + 1 is valid (index is not > than the # of cells in the row)
				return player.getCurrentRow().getCells().get(index + 1);
			}
		}
		
		if (index - 1 >= 0) { //checking that if we decrease a cell (move left) that we are within bounds 
			return player.getCurrentRow().getCells().get(index - 1);
		}
		return currentCell; //return the same cell if we can't move? I think
		
	}
	
	Row moveRow(Player player, Movement movement)
	{
		Row currentRow = player.getCurrentRow();
		int index = 0;
		
		for(int i = 0; i < grid.getRows().size(); i++)
		{
			if(grid.getRows().get(i).equals(currentRow))
			{
				index = i;
			}
		}
		
		if(movement.equals(Movement.UP))
		{
			if (index + 1 < grid.getRows().size()) { //same boundary checks as in moveCell...
				return grid.getRows().get(index + 1);
			}
			
		}
		
		if (index - 1 >= 0) {
			return grid.getRows().get(index - 1);
		}
		
		return currentRow;
	}
	
	boolean play(Movement movement, Player player) {
		
		if(movement == null || player == null)
		{
			return false;
		}
		
		if(movement.equals(Movement.RIGHT) && player.getCurrentCell().getRight().equals(CellComponents.APERTURE))
		{
			Cell newCell = moveCell(player, movement);
			player.setCurrentCell(newCell);
			return true;
		}
		
		if(movement.equals(Movement.LEFT) && player.getCurrentCell().getLeft().equals(CellComponents.APERTURE))
		{
			Cell newCell = moveCell(player, movement);
			player.setCurrentCell(newCell);
			return true;
		}
		
		if(movement.equals(Movement.UP) && player.getCurrentCell().getUp().equals(CellComponents.APERTURE))
		{
			Row newRow = moveRow(player, movement);
			player.setCurrentRow(newRow);
			return true;
		}
			
			
		if(movement.equals(Movement.DOWN) && player.getCurrentCell().getDown().equals(CellComponents.APERTURE))
		{
			Row newRow = moveRow(player, movement);
			player.setCurrentRow(newRow);
			return true;
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "Game [grid=" + grid + "]";
	}
	
}
