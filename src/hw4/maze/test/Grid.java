package hw4.maze.test;

import java.util.ArrayList;

public class Grid {
	
	private ArrayList<Row> rows; //list to hold each row within a grid

	/**
	 * Parameterized constructor for grid class.
	 * @param rows - the list of rows being added to the grid.
	 * rows contain cells.
	 */
	public Grid(ArrayList<Row> rows) {
		super();
		this.rows = rows;
	}

	/**
	 * Getter for the row list within the grid.
	 * @return
	 */
	public ArrayList<Row> getRows() {
		return rows;
	}

	/**
	 * Setter for the row list within the grid.
	 * @param rows
	 */
	public void setRows(ArrayList<Row> rows) {
		this.rows = rows;
	}

	/**
	 * ToString method for Grid class.
	 */
	@Override
	public String toString() {
		return "Grid [rows=" + rows + "]";
	}

}
