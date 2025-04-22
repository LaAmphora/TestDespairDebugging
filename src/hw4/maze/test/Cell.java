package hw4.maze.test;

import java.util.List;

public class Cell {
	
	private CellComponents up;
	private CellComponents down;
	private CellComponents right;
	private CellComponents left;
	
	public Cell(CellComponents left, CellComponents right, CellComponents up, CellComponents down) {
		
		if (up != null) { //if the provided component is not null, then set it as is.
			this.up = up;
		} else { //if we are provided with null as one of the components, set it the the WALL (outer edge) to avoid errors
			this.up = CellComponents.WALL;
		}
		
		if (down != null) {
			this.down = down;
		} else {
			this.down = CellComponents.WALL;
		}
		
		if (right != null) {
			this.right = right;
		} else {
			this.right = CellComponents.WALL;
		}
		
		if (left != null) {
			this.left = left;
		} else {
			this.left = CellComponents.WALL;
		}
		
	}

	public CellComponents getUp() {
		return up;
	}

	public void setUp(CellComponents up) {
		if (up != null) { //need to set to edge (WALL) if we are setting the up component to be null
			this.up = up;
		} else {
			this.up = CellComponents.WALL;
		}
	}

	public CellComponents getDown() {
		return down;
	}

	public void setDown(CellComponents down) {
		if (down != null) { //need to set to edge (WALL) if we are setting the down component to be null
			this.down = down;
		} else {
			this.down = CellComponents.WALL;
		}
	}

	public CellComponents getRight() {
		return right;
	}

	public void setRight(CellComponents right) {
		if (right != null) { //need to set to edge (WALL) if we are setting the right component to be null
			this.right = right;
		} else {
			this.right = CellComponents.WALL;
		}
	}

	public CellComponents getLeft() {
		return left;
	}

	public void setLeft(CellComponents left) {
		if (left != null) { //need to set to edge (WALL) if we are setting the left component to be null
			this.left = left;
		} else {
			this.left = CellComponents.WALL;
		}
	}

	@Override
	public String toString() {
	    return "Cell [left=" + left + ", right=" + right + ", up=" + up + ", down=" + down + "]";
	}

}
