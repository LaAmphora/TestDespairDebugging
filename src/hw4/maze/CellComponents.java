package hw4.maze;

//enum for the cell components. each up, left, right, or down could be one of these
public enum CellComponents {
	
	WALL, //the outer edge of the grid
	APERTURE, //an open path through the grid. each cell must at least have one aperture.
	EXIT //the left most cell in the grid on left component only

}
