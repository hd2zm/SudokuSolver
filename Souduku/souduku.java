
public class souduku {
	public int row;
	public int collum;
	public Grid[] puzzle=new Grid[9];
	
	public souduku(Grid[] val){
		puzzle=val;
	}
	public souduku(int num, Grid val){
		this.puzzle[num]=val;
	}
	
	public tile[][] solution(tile[][] tiles) {
		boolean going=false;
		do{
		tiles=Decrement(tiles);
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(tiles[i][j].value==0 && tiles[i][j].possible.size()==1){
					tiles[i][j].value=tiles[i][j].possible.get(0);
					tiles[i][j].possible.remove(0);
					System.out.println(tiles[i][j].possible.size()+"     "+i+"    "+ j);
					going=true;
				}
				
			}
		}
		going=false;
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(tiles[i][j].value==0 && tiles[i][j].possible.size()>0){
					going=true;
				}
				}
			}
		}while(going);
		
		
		return tiles;
	}

	public tile[][] Decrement(tile[][] tiles){
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(tiles[i][j].value==0){
					DetermineRow(tiles,i,j);
					DetermineGrid(tiles,i,j);
					DetermineCollumn(tiles,i,j);
				}
			}
			
		}
		return tiles;
	}
	public void DetermineCollumn(tile[][] tiles, int x, int y){
		for(int i=0;i<9;i++){
			if((tiles[x][y]).possible.contains(tiles[i][y].value)){
				//System.out.println("HHEHEHE"+ (tiles[i][y].value));
				(tiles[x][y]).possible.remove((tiles[x][y].possible.lastIndexOf(tiles[i][y].value)));
			}
			
		}
	}
	public void DetermineRow(tile[][] tiles, int x, int y){
		for(int i=0;i<9;i++){
			if((tiles[x][y]).possible.contains(tiles[x][i].value)){
				//System.out.println("HHEHEHE"+ (tiles[x][i].value));
				(tiles[x][y]).possible.remove((tiles[x][y].possible.lastIndexOf(tiles[x][i].value)));
			}
			
		}
		
	}
	public void DetermineGrid(tile[][] tiles, /*Grid[] grids,*/ int x, int y){
		int z=tiles[x][y].gridnum;
		Grid solve=puzzle[z];
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if((tiles[x][y]).possible.contains(solve.grid[i][j].value)){
					//System.out.println("HHEHEHE"+ (tiles[x][i].value));
					(tiles[x][y]).possible.remove((tiles[x][y].possible.lastIndexOf(solve.grid[i][j].value)));
				}
			}
		}		
	}

}
