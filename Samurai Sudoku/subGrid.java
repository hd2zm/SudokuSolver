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
