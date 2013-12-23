
public class Grid {
	public int num;
	public tile[][] grid=new tile[3][3];
	public int row=0;
	public int collumn=0;
public Grid(tile[][] val,int num){
	this.num=num;
	this.grid=val;
}
public Grid(int num){
 	this.num=num;
}
	
public void insert(tile val){
	grid[row][collumn]=val;
	collumn++;
	if(collumn==3){
		row++;
		collumn=0;
	}

}

}
