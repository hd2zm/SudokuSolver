
public class Samurai {
	public Sudoku[] Suds;
	
	public Samurai(Sudoku[] sudokus) {
		Suds=sudokus;
	}
	public Samurai(){
		
	}
	public void Solution(){
		boolean done=false;
		boolean change=false;
		do{
			change=false;
			change=Decrement(change);
			for(int l=0;l<5;l++){
				for(int i=0;i<9;i++){
					for(int j=0;j<9;j++){
						if(Suds[l].tiles[i][j].value==0){
							if(Suds[l].tiles[i][j].possible.size()==1){
								Suds[l].tiles[i][j].value=Suds[l].tiles[i][j].possible.get(0);
								Suds[l].tiles[i][j].possible.clear();
								change=true;
							}
							else if(Suds[l].tiles[i][j].possible.size()==0){
								done=true;
							}
							
						}
					}
				}
			}
/*			if(done){
				//System.out.println("ERROR");
				//System.exit(0);	
			}*/
			if(change==false){
			
				//recur
			//	System.out.println("DONE OR ERROR");
				done=true;
	//			System.exit(0);
				}
		}while(!done);
			
	}
	
	public boolean Decrement(boolean change){
		
		for(int l=0;l<5;l++){
			for(int i=0;i<9;i++){
				for(int j=0;j<9;j++){
					if(Suds[l].tiles[i][j].value==0){
						change=Suds[l].DetermineRow(i,j,change);
						change=Suds[l].DetermineGrid(i,j,change);
						change=Suds[l].DetermineCollumn(i,j,change);
						change=Suds[l].Change(i,j,change);
					}
				}
				
			}
		}
		return change;
	}
	
	public String toString(){
		String str="";
		for(int i=0;i<21;i++){
			for(int j=0;j<21;j++){
				if(i<6){
					if(j<9){
						str+=""+(Suds[0].tiles[i][j].value);
					}
					else if(j>=9 & j<12){
						str+=" ";
					}
					else if(j>=12){
						str+=""+(Suds[1].tiles[i][(j-12)].value);
						if(j==20){
							str=str+"\n";
						}
					}
				}
				else if(i>=6 && i<9){
					if(j<9){
						str=str+""+(Suds[0].tiles[i][j].value);
					}
					else if(j>=9 && j<12){
						str=str+""+(Suds[2].tiles[(i-6)][(j-6)].value);
					}
					else if(j>=12){
						str=str+""+(Suds[1].tiles[i][(j-12)].value);
						if(j==20){
							str+="\n";
						}
					}
				}
				else if(i>=9 && i<12){
					if(j<6){
						str=str+" ";
					}
					else if(j>=6 && j<15){
						str=str+""+(Suds[2].tiles[(i-6)][(j-6)].value);
					}
					else if(j>=15){
						str=str+" ";
						if(j==20){
							str=str+"\n";
						}
					}
				}
				else if(i>=12 && i<15){
					if(j<9){
						str=str+""+(Suds[3].tiles[(i-12)][j].value);
					}
					else if(j>=9 && j<12){
						str=str+""+(Suds[2].tiles[(i-6)][(j-6)].value);
					}
					else if(j>=12){
						str=str+""+(Suds[4].tiles[i-12][(j-12)].value);
						if(j==20){
							str=str+"\n";
						}
					}
				}
				else if(i>=15){
					if(j<9){
						str=str+""+(Suds[3].tiles[(i-12)][j].value);
					}
					else if(j>=9 && j<12){
						str=str+" ";
					}
					else if(j>=12){
						str=str+""+(Suds[4].tiles[i-12][(j-12)].value);
						if(j==20){
							str=str+"\n";
						}
					}
				}
			}
		}
		return str;
		
	}


}
