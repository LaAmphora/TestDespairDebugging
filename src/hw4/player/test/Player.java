package hw4.player.test;

import hw4.maze.test.Cell;
import hw4.maze.test.Row;

public class Player {
	
	Cell currentCell;
	Row currentRow;
	
	@Override
	public String toString() {
		return "Player [currentCell=" + currentCell + ", currentRow=" + currentRow + "]";
	}
}
