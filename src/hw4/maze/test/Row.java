package hw4.maze.test;

import java.util.ArrayList;

public class Row {
	
	//list to hold the cells in each row
	private ArrayList<Cell> cells;

	/**
	 * Parameterized constructor for Row class.
	 * @param cells - the list of cells being added to the row.
	 */
	public Row(ArrayList<Cell> cells) {
		this.cells = cells;
	}

	/**
	 * Getter for the list of cells for a row.
	 * @return
	 */
	public ArrayList<Cell> getCells() {
		return cells;
	}

	/**
	 * Setter for the list of cells for a row.
	 * @param cells
	 */
	public void setCells(ArrayList<Cell> cells) {
		this.cells = cells;
	}

	/**
	 * ToString for Row class.
	 */
	@Override
	public String toString() {
		return "Row [cells=" + cells + "]";
	}

}
