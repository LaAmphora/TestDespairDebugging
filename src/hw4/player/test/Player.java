package hw4.player.test;

import hw4.maze.test.Cell;
import hw4.maze.test.Row;

public class Player {
	
	Cell currentCell;
	Row currentRow;
	
	/**
	 * This is a parameterized constructor for the Player class
	 * @param currentRow (Row)
	 *     This argument represents the current row of the player.
	 * @param currentCell (Cell)
	 *     This argument represents the current cell of the player.
	 * */
	public Player(Row currentRow, Cell currentCell) {
		this.currentCell = currentCell;
		this.currentRow = currentRow;
	}

	/** Gets the current cell of the player. */
	public Cell getCurrentCell() {
		return currentCell;
	}

	/** Sets the current cell of the player. */
	public void setCurrentCell(Cell currentCell) {
		this.currentCell = currentCell;
	}

	/** Gets the current row of the player. */
	public Row getCurrentRow() {
		return currentRow;
	}

	/** Sets the current row of the player. */
	public void setCurrentRow(Row currentRow) {
		this.currentRow = currentRow;
	}

	/**
	 * Displays information about the player.
	 * */
	@Override
	public String toString() {
		return "Player [currentCell=" + currentCell + ", currentRow=" + currentRow + "]";
	}
}
