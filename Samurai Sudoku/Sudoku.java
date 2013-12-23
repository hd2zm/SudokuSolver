import java.util.ArrayList;


public class Sudoku {
	public int num;
	public boolean start;
	public tile[][] tiles=new tile[9][9];
	public subGrid[] grids=new subGrid[9];
	int row=0;
	int column=0;
	public Sudoku(int val){
		num=val;
		for(int i=0;i<9;i++){
			subGrid x= new subGrid(i);
			grids[i]=x;
		}
	}
	
	public void insert(int val){
		tile x=new tile(val);
		x.insertRowCol(row,column);
		x.Setsudoku(num);
	//	System.out.println("ROW: "+row+" Col: "+column+" num: "+num);
		tiles[row][column]=x;

		column++;
		if(column==9){
			column=0;
			row++;
		}
	}
	
	public void insert(tile val){
		val.insertRowCol(row, column);
		val.Setsudoku(num);
	//	System.out.println("ROWX: "+row+" ColX: "+column+" numX: "+num);
		tiles[row][column]=val;
		column++;
		if(column==9){
			column=0;
			row++;
		}
	}
	public void setGrid(){
		int k;
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(i<=2){
					if(j<=2){
						k=0;
						tiles[i][j].SetgridNum(k);
						grids[k].insert(tiles[i][j]);
					}	
					else if(j>=3 & j<=5){
						k=1;
						tiles[i][j].SetgridNum(k);
						grids[k].insert(tiles[i][j]);
					}
					else{
						k=2;
						tiles[i][j].SetgridNum(k);
						grids[k].insert(tiles[i][j]);
					}
			}
				else if(i>=3 & i<=5){
					if(j<=2){
						k=3;
						tiles[i][j].SetgridNum(k);
						grids[k].insert(tiles[i][j]);
					}	
					else if(j>=3 & j<=5){
						k=4;
						tiles[i][j].SetgridNum(k);
						grids[k].insert(tiles[i][j]);
					}
					else{
						k=5;
						tiles[i][j].SetgridNum(k);
						grids[k].insert(tiles[i][j]);
					}
				}
				else {
					if(j<=2){
						k=6;
						tiles[i][j].SetgridNum(k);
						grids[k].insert(tiles[i][j]);
					}	
					else if(j>=3 & j<=5){
						k=7;
						tiles[i][j].SetgridNum(k);
						grids[k].insert(tiles[i][j]);
					}
					else{
						k=8;
						tiles[i][j].SetgridNum(k);
						grids[k].insert(tiles[i][j]);
					}
				}
			}
		}
	}
	
	public boolean DetermineCollumn(int x, int y, boolean change){
		for(int i=0;i<9;i++){
			if((tiles[x][y]).possible.contains(tiles[i][y].value)){
				//System.out.println("HHEHEHE"+ (tiles[i][y].value));
				(tiles[x][y]).possible.remove((tiles[x][y].possible.lastIndexOf(tiles[i][y].value)));
				change=true;
			}
			
		}
		return change;
	}
	public boolean DetermineRow(int x, int y, boolean change){
		for(int i=0;i<9;i++){
			if((tiles[x][y]).possible.contains(tiles[x][i].value)){
				//System.out.println("HHEHEHE"+ (tiles[x][i].value));
				(tiles[x][y]).possible.remove((tiles[x][y].possible.lastIndexOf(tiles[x][i].value)));
				change= true;
			}
			
			
		}
		return change;
		
	}
	public boolean DetermineGrid(int x, int y,boolean change){
		int z;
//		boolean change;
		if(x<=2){
			if(y<=2){
				z=0;
			}	
			else if(y>=3 & y<=5){
				z=1;
			}
			else{
				z=2;
			}
		}
		else if(x>=3 & x<=5){
			if(y<=2){
				z=3;
			}	
			else if(y>=3 & y<=5){
				z=4;
			}
			else{
				z=5;
			}
		}
		else {
			if(y<=2){
				z=6;
			}	
			else if(y>=3 & y<=5){
				z=7;
			}
			else{
				z=8;
			}
		}
		subGrid solve=grids[z];
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if((tiles[x][y]).possible.contains(solve.grid[i][j].value)){
					//System.out.println("HHEHEHE"+ (tiles[x][i].value));
					(tiles[x][y]).possible.remove((tiles[x][y].possible.lastIndexOf(solve.grid[i][j].value)));
					change=true;
				}
			}
		}
		return change;		
	}

	public boolean Change(int x, int y, boolean change){
		boolean row=false;
		boolean column=false;
		boolean grid=false;
		for(int j=0;j<(tiles[x][y].possible.size());j++){
			if(j<(tiles[x][y].possible.size())){
				row=compareRow(tiles[x][y].possible.get(j), x, y);
				if(!row){
					column=CompareColumn(tiles[x][y].possible.get(j), x, y);
					if(!column){
						grid=CompareGrid(tiles[x][y].possible.get(j), x, y);
					}
				}
			}

		}
		if(row||column||grid){
			change= true;
		}
		return change;
	}

	private boolean CompareGrid(int num, int x, int y) {
		int gridNum;
//		boolean change;
		if(x<=2){
			if(y<=2){
				gridNum=0;
			}	
			else if(y>=3 & y<=5){
				gridNum=1;
			}
			else{
				gridNum=2;
			}
		}
		else if(x>=3 & x<=5){
			if(y<=2){
				gridNum=3;
			}	
			else if(y>=3 & y<=5){
				gridNum=4;
			}
			else{
				gridNum=5;
			}
		}
		else{
			if(y<=2){
				gridNum=6;
			}	
			else if(y>=3 & y<=5){
				gridNum=7;
			}
			else{
				gridNum=8;
			}
		}
		boolean gridChange=true;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(grids[gridNum].grid[i][j]!=tiles[x][y]){
					if(grids[gridNum].grid[i][j].possible.contains(num)){
						gridChange=false;
					}
				}
			}
		}
		if(gridChange){
			tiles[x][y].value=num;
			tiles[x][y].possible.clear();
		}
		return gridChange;
	}

	private boolean CompareColumn(int num, int x, int y) {
		boolean colChange=true;
		for(int i=0;i<9;i++){
			if(tiles[x][i].value==0 && i!=y){
				if(tiles[x][i].possible.contains(num)){
					colChange=false;
				}
			}
		}
		if(colChange){
			tiles[x][y].value=num;
			tiles[x][y].possible.clear();
		}
		return colChange;
	
	}

	private boolean compareRow(int num,int x,int y) {
		boolean rowChange=true;
		for(int i=0;i<9;i++){
			if(tiles[i][y].value==0 && i!=x){
				if(tiles[i][y].possible.contains(num)){
					rowChange=false;
				}
			}
		}
		if(rowChange){
			tiles[x][y].value=num;
			tiles[x][y].possible.clear();
		}
		return rowChange;
	}
	
	public boolean filled(){
		boolean fill=true;
		boolean auto=false;
		for(int i=0; i<9;i++){
			for(int j=0;j<9;j++){
				if(tiles[i][j].value==0){
					if(tiles[i][j].possible.size()>0){
						fill=false;
					}
					/*else{
						System.out.println("WHYE HERE");
						System.exit(0);
						auto=true;
					}*/
				}
			}
		}
		if(auto){
			fill=true;
		}
		return fill;
	}
	public boolean CantFinish(){
		boolean wrong=false;
		for(int i=0; i<9;i++){
			for(int j=0;j<9;j++){
				if(tiles[i][j].value==0){
					if(tiles[i][j].possible.size()==0){
						wrong=true;
						//System.out.println("YOO");
					}
				}
			}
		}
		return wrong;
	}
	public int findfirst() {
		int min=10;
		for(int i=0; i<9;i++){
			for(int j=0;j<9;j++){
				if(tiles[i][j].value==0){
					if(tiles[i][j].possible.size()<min){
						min=0;
						System.out.println("MIN: "+ min+" NUM:: "+num);
					}
				}
			}
		}
		return min;
	}
	public ArrayList<Integer> findfirstLocation() {
		int min=10;
		ArrayList<Integer> x=new ArrayList<Integer>(2);
		x.add(0);
		x.add(0);
		for(int i=0; i<9;i++){
			for(int j=0;j<9;j++){
				if(tiles[i][j].value==0){
					//System.out.println(tiles[i][j].value);
					if(tiles[i][j].possible.size()<min){
						x.clear();
						min=0;
						System.out.println("SUD NUM:: "+num+"Min: "+min+" x: "+i+" y: "+j);
						x.add(i);
						x.add(j);
					}
				}
			}
		}
		return x;
	}

	public String toString(){
		String str="";
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				str=str+""+(tiles[i][j].value);
			}
			str=str+"\n";
		}
		
		return str;
	}


}

