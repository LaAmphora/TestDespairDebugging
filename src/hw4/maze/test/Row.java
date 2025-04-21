package hw4.maze.test;

import java.util.List;

public class Row {
	
	//list to hold the cells in each row
	private List<Cell> cells;

	public Row(List<Cell> cells) {
		this.cells = cells;
	}

	public List<Cell> getCells() {
		return cells;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}

	@Override
	public String toString() {
		return "Row [cells=" + cells + "]";
	}
	
	@Override
	public boolean equals(Object a) {
		
		List<Cell> a_cells = ((Row) a).getCells();
		boolean flag = true;
		
		for(int i = 0; i < cells.size(); i++)
		{
			if(!a_cells.get(i).equals(this.cells.get(i)))
			{
				flag = false;
			}		
		}
		
		return flag;
		
	}

}
