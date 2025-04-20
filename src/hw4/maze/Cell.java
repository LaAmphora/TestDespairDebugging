package hw4.maze;

public class Cell {
	
	private CellComponents up;
	private CellComponents down;
	private CellComponents right;
	private CellComponents left;
	
	public Cell(CellComponents up, CellComponents down, CellComponents right, CellComponents left) {
		
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
			this.right = CellComponents.WALL;
		}
		
		if (left != null) {
			this.left = CellComponents.WALL;
		}
		
	}

	public CellComponents getUp() {
		return up;
	}

	public void setUp(CellComponents up) {
		this.up = up;
	}

	public CellComponents getDown() {
		return down;
	}

	public void setDown(CellComponents down) {
		this.down = down;
	}

	public CellComponents getRight() {
		return right;
	}

	public void setRight(CellComponents right) {
		this.right = right;
	}

	public CellComponents getLeft() {
		return left;
	}

	public void setLeft(CellComponents left) {
		this.left = left;
	}

}
