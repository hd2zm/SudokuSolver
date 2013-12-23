//Hari Devanathan (hd2zm) and Sai Peddy (ssp53n)
//object subGrid--controls tiles in each grid. Primarily used for Samurai puzzles, when there are
//grids overlapping and we need to keep track of the same grid in two different Sudoku puzzles.
public class subGrid {
	public tile[][] grid=new tile[3][3];
	public int num;
	public int row=0;
	public int column=0;
	
	public subGrid(tile[][] OneGrid,int val){
		grid=OneGrid;
		num=val;
	}
	public subGrid(int val){
		num=val;
	}
	public void insert(tile val){
		grid[row][column]=val;
		column++;
		if(column==3){
			row++;
			column=0;
		}

	}
}
