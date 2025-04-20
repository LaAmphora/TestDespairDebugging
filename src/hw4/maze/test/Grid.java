package hw4.maze.test;

import java.util.List;

public class Grid {
	
	private List<Row> rows; //list to hold each row within a grid

	public Grid(List<Row> rows) {
		super();
		this.rows = rows;
	}

	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "Grid [rows=" + rows + "]";
	}

}
