package hw4.maze.test;

import java.util.ArrayList;

public class Grid {
	
	private ArrayList<Row> rows; //list to hold each row within a grid

	public Grid(ArrayList<Row> rows) {
		super();
		this.rows = rows;
	}

	public ArrayList<Row> getRows() {
		return rows;
	}

	public void setRows(ArrayList<Row> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "Grid [rows=" + rows + "]";
	}

}
