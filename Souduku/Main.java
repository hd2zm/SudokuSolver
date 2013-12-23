//Hari Devanathan (hd2zm) and Sai Peddy (ssp5en)
//main method--executes program
import java.io.*;
import java.util.*;

public class Main {

	/**
	 * @param args
	 */
	
	private static Samurai solution;
	private static boolean occured_once;
	
	//Execute program--input if you want to run regular or samurai sudoku puzzle
	
	public static void main(String[] args) throws FileNotFoundException{
		// TODO Auto-generated method stub
		try{
			occured_once = true;
			
			Scanner sc=new Scanner(System.in);
			System.out.println("What kind of puzzle? \n1)Regular \n2)Samurai");
			int type = sc.nextInt();
			int num=0;
			if(type<1 || type > 2){
				System.out.print("DONE...I Guess");
				System.exit(0);
			}
			else if(type == 1){
				num = 1;
			}
			else if(type == 2){
				num = 5;
			}
			System.out.print("Enter File Name of Souduku puzzle");
			String fileName=sc.next();
			
			//read in sudoku file
			Scanner fileread=new Scanner(new FileReader(fileName));
			String line="";
			Sudoku[] Sudokus=new Sudoku[num];
			for(int i=0;i<num;i++){
				Sudoku x=new Sudoku(i);
				Sudokus[i]=x;		
			}
			int length;
			int dashlen=0;
			int number;
			int total;
			int[] minlength=new int[num];
			ArrayList<Integer> Lastfilled = new ArrayList<Integer>(0);
			int sudoku_num = 0;
			for(int i=0;i<num;i++){
				minlength[i]=9; 
			}
			while(fileread.hasNext()){
				line=fileread.next();
				length=line.length();
				for(int i=0;i<length;i++){
					if(line.charAt(i)=='-'){
						dashlen++;
					}
				}
				total=length-dashlen;
				number=(int)Math.ceil((double)(total)/9);
				if((double)number==(double)(total/9)){
					while(minlength[sudoku_num]<=0){
						sudoku_num+=1;
					}
					int i = 0;
					Lastfilled.clear();
					for(int start = 0; start < number; start++){
						while(line.charAt(i)=='-'){
							i+=1;
						}
						while(line.charAt(i)!='-'){
							Sudokus[sudoku_num+start].insert(line.charAt(i));						
							i+=1;
							if(i==length){
								break;
							}
						}
						Lastfilled.add((sudoku_num+start));
						if(i==length){
							minlength[sudoku_num+start]-=1;								
							break;
						}
						while(line.charAt(i)=='-'){
							i+=1;
							if(i==length){
								minlength[sudoku_num+start]-=1;								
								break;
							}
						}		  
						minlength[sudoku_num+start]-=1;								
								
					}
				}
								
								
							
				else{
					
					if(Lastfilled.size()==2){
						int x=Lastfilled.get(0);
						int y=Lastfilled.get(1);
						Lastfilled.clear();
						for(int i=0;i<6;i++){
							Sudokus[x].insert(line.charAt(i));
						}
						int max=minlength[0];
						int index=0;
						for(int j=1;j<minlength.length;j++){
							if(max<minlength[j]){
								max=minlength[j];
								index=j;
							}
						}
						
						
						for(int i=6;i<9;i++){
							int z=line.charAt(i);
							tile l=new tile(z);
							Sudokus[x].insert(l);
							Sudokus[index].insert(l);
						}
						for(int i=9;i<12;i++){
							Sudokus[index].insert(line.charAt(i));				
						}
	
						for(int i=12;i<15;i++){
							int z=line.charAt(i);
							tile l=new tile(z);
							Sudokus[index].insert(l);
							Sudokus[y].insert(l);
					
						}
						for(int i=15;i<length;i++){
							Sudokus[y].insert(line.charAt(i));
										
						}
						minlength[x]-=1;
						minlength[y]-=1;
						minlength[index]-=1;
						Lastfilled.add(x);
						Lastfilled.add(index);
						Lastfilled.add(y);
						
					}
					else if(Lastfilled.size()==3){
						int x=Lastfilled.get(0);
						int y=Lastfilled.get(1);
						int z=Lastfilled.get(2);
						Lastfilled.clear();
						for(int i=0;i<6;i++){
							Sudokus[x].insert(line.charAt(i));
						}
						for(int i=6;i<9;i++){
							int k=line.charAt(i);
							tile l=new tile(k);
							Sudokus[x].insert(l);
							Sudokus[y].insert(l);
						}
						for(int i=9;i<12;i++){
							Sudokus[y].insert(line.charAt(i));
						}
	
						for(int i=12;i<15;i++){
							int k=line.charAt(i);
							tile l=new tile(k);
							Sudokus[y].insert(l);
							Sudokus[z].insert(l);
					
						}
						for(int i=15;i<length;i++){
							Sudokus[z].insert(line.charAt(i));
					
						}
						minlength[x]-=1;
						minlength[y]-=1;
						minlength[z]-=1;
						Lastfilled.add(x);
						Lastfilled.add(y);
						Lastfilled.add(z);
	
					}
					else if(Lastfilled.size()==1){
						int y=Lastfilled.get(0);
						int x=3;
						int z=4;
						Lastfilled.clear();
						for(int i=0;i<6;i++){
							Sudokus[x].insert(line.charAt(i));
						}
						for(int i=6;i<9;i++){
							int k=line.charAt(i);
							tile l=new tile(k);
							Sudokus[x].insert(l);
							Sudokus[y].insert(l);
						}
						for(int i=9;i<12;i++){
							Sudokus[y].insert(line.charAt(i));
		
						}
	
						for(int i=12;i<15;i++){
							int k=line.charAt(i);
							tile l=new tile(k);
							Sudokus[y].insert(l);
							Sudokus[z].insert(l);
					
						}
						for(int i=15;i<length;i++){
							Sudokus[z].insert(line.charAt(i));
					
						}
						minlength[x]-=1;
						minlength[y]-=1;
						minlength[z]-=1;
						Lastfilled.add(x);
						Lastfilled.add(y);
						Lastfilled.add(z);
	
					}
					}
				dashlen=0;
	
			}
			
			for(int i=0;i<Sudokus.length;i++){
				Sudokus[i].setGrid();
			}
			
			//solve puzzle--regular is type 1, samurai is type 2
			if (type == 1){
				boolean going=false;
				boolean change=false;
				do{
				change=false;
				going=false;
				change=Sudokus[0].Decrement(change);
				for(int i=0;i<9;i++){
					for(int j=0;j<9;j++){
						if(Sudokus[0].tiles[i][j].value==0 && Sudokus[0].tiles[i][j].possible.size()==1){
							Sudokus[0].tiles[i][j].value=Sudokus[0].tiles[i][j].possible.get(0);
							Sudokus[0].tiles[i][j].possible.remove(0);
							going=true;
						}
						
					}
				}
				}while(going||change);
				if(!CheckDone(Sudokus)){
					Sudokus=RecurSud(Sudokus);
				}
				if (Sudokus[0].check()){
					System.out.println(Sudokus[0].toString()); }
				else{
					System.out.println("ERROR: INPUT HAS INCORRECT VALUES. CANNOT BE SOLVED");
				}
			}
			if (type == 2){
				Samurai x=new Samurai(Sudokus);
				x.Solution();
				boolean wrong=CheckWrong(x.Suds);
				boolean done =CheckDone(x.Suds);
				boolean indiv = true;
				if(!wrong&&!done){
					Samurai z=Check(x.Suds);
					if(z==null){
						System.out.println("WAIT WHAT HAPPENED");
					}
					else{
						int e = 0;
						while (e < solution.Suds.length)
						{
							if(solution.Suds[e].check()==false)
							{
								indiv = false;
								break;
							}
							e++;
						}
						if(indiv){
							System.out.println(solution.toString());
						}
						else{
							System.out.println("ERROR: INPUT HAS INCORRECT VALUES. CANNOT BE SOLVED");
						}
					}
				}
				else if(done){
					System.out.println(x.toString());
				}
				else{
					System.out.println("ERROR: INPUT HAS INCORRECT VALUES. CANNOT BE SOLVED");
				}
				sc.close();
				fileread.close();
			}
		}
		catch(Exception e){
			System.out.println("ERROR: INPUT HAS INCORRECT VALUES. CANNOT BE SOLVED");
		}
	}
	
	
	//Recursive method for regular sudoku; if there are multiple solutions, it selects the  
	//first solution it comes across
	public static Sudoku[] RecurSud(Sudoku[] sudokus){
		ArrayList<Integer> x=sudokus[0].findfirstLocation();
		ArrayList<Integer> poss = new ArrayList<Integer>(0);
		ArrayList<Sudoku> copies=new ArrayList<Sudoku>(0);
		ArrayList<Integer> indexs=new ArrayList<Integer>(0);
		int row=x.get(0);
		int col=x.get(1);
		for(int i=0;i<sudokus[0].tiles[row][col].possible.size();i++){
			poss.add(sudokus[0].tiles[row][col].possible.get(i));
		}
		for(int i=0;i<sudokus[0].tiles[row][col].possible.size();i++){
			Sudoku[] duplicate=CopySud(sudokus);
			copies.add(duplicate[0]);
			indexs.add(i);
		}
		boolean cont=true;
		for(int i=0;i<copies.size();i++){
			if(cont){
				copies.get(i).tiles[row][col].value=poss.get(i);
				copies.get(i).tiles[row][col].possible.clear();

				boolean going=false;
				boolean change=false;
				do{
				change=false;
				going=false;
				change=copies.get(i).Decrement(change);
				for(int i1=0;i1<9;i1++){
					for(int j=0;j<9;j++){
						if(copies.get(i).tiles[i1][j].value==0 && copies.get(i).tiles[i1][j].possible.size()==1){
							copies.get(i).tiles[i1][j].value=copies.get(i).tiles[i1][j].possible.get(0);
							copies.get(i).tiles[i1][j].possible.remove(0);
							going=true;
						}
						
						
					}
				}
				}while(going||change);
				
				Sudoku[] s = {copies.get(i)};
				boolean fail=CheckWrong( s );
				if(!fail){
					if(CheckDone(s)&&s[0].check()){
						return s;//if it didnt fail then this is a solution
					}
					else{
						Sudoku[] z= RecurSud(s);
						if(!CheckWrong(z)&&CheckDone(z)){
							return z;//other wise it has to go further and what ever reutrn is solution
						}
					}
				}
			}
		}
		return null;
	}

	
	
	public static boolean CheckWrong(Sudoku[] Sudokus){//just checks if the solution so far is wrong or not
		boolean wrong=false;
		if (Sudokus == null){
			return true;
		}
		for(int i=0;i<Sudokus.length;i++){
			boolean finish=Sudokus[i].filled();
			if(!finish){
				boolean temp=false;
				temp=Sudokus[i].CantFinish();
				if(temp){
					wrong=true;
					return wrong;
				}
					
			}
				
		}
		return wrong;
	}
	public static boolean CheckDone(Sudoku[] Sudokus){//Checks if we are done or have more to go
		boolean done=true;
			for(int i=0;i<Sudokus.length;i++){
				boolean finish=Sudokus[i].filled();
				if(!finish){
					done=false;
					return done;
				}
			}
			return done;
	}
	
	//Check if Samurai puzzle is accurate; the solution has no empty spaces and no conflicts in
	//rows, columns, and grids for each of the 5 sudoku puzzles
	public static Samurai Check(Sudoku[] Sudokus){
		int SudNum = 0;
		int min=10;
		for(int i=0;i<5;i++){	
			boolean temp=false;
			temp=Sudokus[i].CantFinish();
			if(temp){
				return null;
			}
			int x=Sudokus[i].findfirst();
			if(x<min){
				min=0;
				SudNum=i;
			}
				
			
		}
		Samurai x=new Samurai(Sudokus);
			
		x=Recur(Sudokus,SudNum);
		if(x==null){
			return null;
		}
		else {
			Sudokus=x.Suds;
			return x;
		}
	}
	
	//Recur method for Samurai--if you can't find solution for current sudoku puzzle, 
	//move on the next puzzle and solve that given updated information. If you run into
	//no solutions, backtrack and see if you chose the wrong value. If there are multiple
	//solutions, pick first one you run into.
	public static Samurai Recur(Sudoku[] sudokus, int SudNum){
		ArrayList<Integer> x=sudokus[SudNum].findfirstLocation();
		ArrayList<Integer> poss = new ArrayList<Integer>(0);
		ArrayList<Samurai> copies=new ArrayList<Samurai>(0);
		ArrayList<Integer> indexs=new ArrayList<Integer>(0);
		int row=x.get(0);
		int col=x.get(1);
		for(int i=0;i<sudokus[SudNum].tiles[row][col].possible.size();i++){
			poss.add(sudokus[SudNum].tiles[row][col].possible.get(i));
		}
		for(int i=0;i<sudokus[SudNum].tiles[row][col].possible.size();i++){
			Sudoku[] duplicate=Copy(sudokus);
			Samurai z=new Samurai(duplicate);
			copies.add(z);
			indexs.add(i);
		}//makes copies as to not update the main one wrongly
		boolean cont=true;
		int index=5;
		for(int i=0;i<copies.size();i++){
			if(cont){
				copies.get(i).Suds[SudNum].tiles[row][col].value=poss.get(i);
				copies.get(i).Suds[SudNum].tiles[row][col].possible.clear();
				copies.get(i).Solution();
				boolean fail=CheckWrong(copies.get(i).Suds);
				if(!fail){
					Samurai wrong=new Samurai();
					wrong=Check(copies.get(i).Suds);
					
					fail=CheckWrong(copies.get(i).Suds);
					if(fail && wrong ==null &&i==copies.size()-1){
						return null;//false solution
					}
					else if(!fail){
						cont=false;
						index=i;
					}
				}
			}
		}
		if(index==5){
			return null;
		}
		else{
			Samurai x1=new Samurai(copies.get(index).Suds);
			if(occured_once){
				solution = x1;
				occured_once = false;
			}
			return x1;
		}
		
	}

	public static Sudoku[] CopySud(Sudoku[] sudokus){//Just duplicates the sudokus so that the originals arn't messed up
		Sudoku[] duplicate=new Sudoku[1];
				for(int i=0;i<9;i++){
					for(int j=0;j<9;j++){
						if(i==0&&j==0){
							Sudoku x=new Sudoku(0);
							duplicate[0]=x;
						}
						if(sudokus[0].tiles[i][j].value==0){
							tile x=new tile(sudokus[0].tiles[i][j].value);
							duplicate[0].insert(x);
							ArrayList<Integer> poss=sudokus[0].tiles[i][j].possible;
							duplicate[0].tiles[i][j].inserPos(poss);
						}
						else{
							tile x=new tile(sudokus[0].tiles[i][j].value);
							duplicate[0].insert(x);
						}
					}
				}
			duplicate[0].setGrid();
			return duplicate;
	}

	
	
	public static Sudoku[] Copy(Sudoku[] sudokus){//Same as previous had to make one again for samurai and regular sudoku due to problems
		Sudoku[] duplicate=new Sudoku[5];
			tile[][] temp=new tile[3][3];
			tile[][] temp2=new tile[3][3];
			for(int k=0;k<5;k++){
				for(int i=0;i<9;i++){
					for(int j=0;j<9;j++){
						if(i==0&&j==0){
							Sudoku x=new Sudoku(k);
							duplicate[k]=x;
						}
						if(k==0){
							if(i>=6){
								if(j>=6){
									tile x=new tile(sudokus[k].tiles[i][j].value);
									temp[i-6][j-6]=x;
									duplicate[k].insert(x);
									if(sudokus[k].tiles[i][j].value==0){
										ArrayList<Integer> poss=sudokus[k].tiles[i][j].possible;
										duplicate[k].tiles[i][j].inserPos(poss);
									}
								}
								else{
									duplicate[k].insert(sudokus[k].tiles[i][j].value);
									if(sudokus[k].tiles[i][j].value==0){
										ArrayList<Integer> poss=sudokus[k].tiles[i][j].possible;
										duplicate[k].tiles[i][j].inserPos(poss);
									}
								}
							}
							else{
								duplicate[k].insert(sudokus[k].tiles[i][j].value);
								if(sudokus[k].tiles[i][j].value==0){
									ArrayList<Integer> poss=sudokus[k].tiles[i][j].possible;
									duplicate[k].tiles[i][j].inserPos(poss);
								}
							}
						}
						if(k==1){
							if(i>=6){
								if(j<3){
									tile x=new tile(sudokus[k].tiles[i][j].value);
									temp2[i-6][j]=x;
									duplicate[k].insert(x);
									if(sudokus[k].tiles[i][j].value==0){
										ArrayList<Integer> poss=sudokus[k].tiles[i][j].possible;
										duplicate[k].tiles[i][j].inserPos(poss);
									}
								}
								else{
									duplicate[k].insert(sudokus[k].tiles[i][j].value);								
									if(sudokus[k].tiles[i][j].value==0){
										ArrayList<Integer> poss=sudokus[k].tiles[i][j].possible;
										duplicate[k].tiles[i][j].inserPos(poss);
									}
								}
							}
							else{
								duplicate[k].insert(sudokus[k].tiles[i][j].value);
								if(sudokus[k].tiles[i][j].value==0){
									ArrayList<Integer> poss=sudokus[k].tiles[i][j].possible;
									duplicate[k].tiles[i][j].inserPos(poss);
								}
							}
						}
						if(k==2){
							if(i<3){
								if(j<3){
									tile x=temp[i][j];
									duplicate[k].insert(x);
								}
								else if(j>=6){
									tile x=temp2[i][j-6];
									duplicate[k].insert(x);
								}
								else{
									duplicate[k].insert(sudokus[k].tiles[i][j].value);								
									if(sudokus[k].tiles[i][j].value==0){
										ArrayList<Integer> poss=sudokus[k].tiles[i][j].possible;
										duplicate[k].tiles[i][j].inserPos(poss);
									}
								}
							}
							else if(i>=6){
								if(j<3){
									tile x=new tile(sudokus[k].tiles[i][j].value);
									temp[i-6][j]=x;
									duplicate[k].insert(x);
									if(sudokus[k].tiles[i][j].value==0){
										ArrayList<Integer> poss=sudokus[k].tiles[i][j].possible;
										duplicate[k].tiles[i][j].inserPos(poss);
									}							}
								else if(j>=6){
									tile x=new tile(sudokus[k].tiles[i][j].value);
									temp2[i-6][j-6]=x;
									duplicate[k].insert(x);
									if(sudokus[k].tiles[i][j].value==0){
										ArrayList<Integer> poss=sudokus[k].tiles[i][j].possible;
										duplicate[k].tiles[i][j].inserPos(poss);
									}
								}
								else{
									duplicate[k].insert(sudokus[k].tiles[i][j].value);								
									if(sudokus[k].tiles[i][j].value==0){
										ArrayList<Integer> poss=sudokus[k].tiles[i][j].possible;
										duplicate[k].tiles[i][j].inserPos(poss);
									}
								}
							}
							else{
								duplicate[k].insert(sudokus[k].tiles[i][j].value);
								if(sudokus[k].tiles[i][j].value==0){
									ArrayList<Integer> poss=sudokus[k].tiles[i][j].possible;
									duplicate[k].tiles[i][j].inserPos(poss);
								}
							}
						}
						if(k==3){
							if(i<3){
								if(j>=6){
									tile x=temp[i][j-6];
									duplicate[k].insert(x);							}
								else{
									duplicate[k].insert(sudokus[k].tiles[i][j].value);
									if(sudokus[k].tiles[i][j].value==0){
										ArrayList<Integer> poss=sudokus[k].tiles[i][j].possible;
										duplicate[k].tiles[i][j].inserPos(poss);
									}
								}
							}
							else{
								duplicate[k].insert(sudokus[k].tiles[i][j].value);
								if(sudokus[k].tiles[i][j].value==0){
									ArrayList<Integer> poss=sudokus[k].tiles[i][j].possible;
									duplicate[k].tiles[i][j].inserPos(poss);
								}
							}
						}
						if(k==4){
							if(i<3){
								if(j<3){
									tile x=temp2[i][j];
									duplicate[k].insert(x);							}
								else{
									duplicate[k].insert(sudokus[k].tiles[i][j].value);
									if(sudokus[k].tiles[i][j].value==0){
										ArrayList<Integer> poss=sudokus[k].tiles[i][j].possible;
										duplicate[k].tiles[i][j].inserPos(poss);
									}
								}
							}
							else{
								duplicate[k].insert(sudokus[k].tiles[i][j].value);
								if(sudokus[k].tiles[i][j].value==0){
									ArrayList<Integer> poss=sudokus[k].tiles[i][j].possible;
									duplicate[k].tiles[i][j].inserPos(poss);
								}
							}
						}

					}
				}
				
			}
			for(int i=0;i<5;i++){
				duplicate[i].setGrid();
			}
			return duplicate;
	}
	}



	


