import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException{
		
		Scanner sc=new Scanner(System.in);
		System.out.println("How Many Souduku's are in this file");
		int num=sc.nextInt();
		if(num<=0){
			System.out.print("DONE...I Guess");
			System.exit(0);
		}
		System.out.print("Enter File Name of Souduku puzzle");
		String fileName=sc.next();
		Scanner fileread=new Scanner(new FileReader(fileName));
		String line="";
		Sudoku[] Sudokus=new Sudoku[num];
		for(int i=0;i<num;i++){
		//	System.out.println(num+" "+ i);
			Sudoku x=new Sudoku(i);
			Sudokus[i]=x;		
		}
	//	System.out.println(Sudokus[4].num);
		int length;
		int dashlen=0;
		int number;
		int total;
		int[] minlength=new int[num];
		ArrayList<Integer> Lastfilled = new ArrayList<Integer>(0);
		int sudoku_num = 0; //starting sudoku
		for(int i=0;i<num;i++){
			minlength[i]=9; //changed value; minlength decrements row for each sudoku
		}
		//String s=fileread.toString();
		//System.out.println(s);
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
		//	System.out.println(number+" ANOTHER:: "+(total/9));
			/*for(int i=0;i<length;i++){
				if(line.charAt(i)!='-'){
					if(line.charAt(i)=='0'){
						if((double)number==(double)(total/9)){*/
							/*if(Lastfilled.size()==0){
								for(int j=0;j<number;j++){
									if(Sudokus[j].start==false){
										Lastfilled.add(j);
									}
								}
								int n=0;
								while(number<n/9){
								for(int l=0;l<length;l++){
									if(int n<){
										
									}
								}
								}
							}*/
			if((double)number==(double)(total/9)){
				//System.out.println((double)(total/9));
			//	System.out.println(number);
				while(minlength[sudoku_num]<=0){
					sudoku_num+=1;
				}
				int i = 0;
				Lastfilled.clear();
				for(int start = 0; start < number; start++){
					
					//System.out.println("START: )" +start+" NUM: "+number);
					while(line.charAt(i)=='-'){
						//System.out.println("HERE "+i);
						i+=1;
					}
					while(line.charAt(i)!='-'){
					//	System.out.println("NOT "+i);
						//System.out.println(sudoku_num+start);
						Sudokus[sudoku_num+start].insert(line.charAt(i));
			//			if(Lastfilled.contains(sudoku_num+start)==false){
							//System.out.println("YOO:: "+(sudoku_num+start)+" nAH:: "+Lastfilled.contains(sudoku_num+start));
							//Lastfilled.add((sudoku_num+start));
						
						i+=1;
						if(i==length){
							break;
						}
					}
					//System.out.println(i);
					Lastfilled.add((sudoku_num+start));
				//	System.out.println("ARG:: "+Lastfilled.size());
					if(i==length){
		//				System.out.println("BEFORE: "+minlength[sudoku_num+start]+" ii: "+(sudoku_num+start));
						minlength[sudoku_num+start]-=1;								
			//			System.out.println("After: "+minlength[sudoku_num+start]+" ii: "+(sudoku_num+start));
						break;
					}
					//System.out.println("YOOO: "+i);
					while(line.charAt(i)=='-'){
						//System.out.println("YES "+i);
						i+=1;
						if(i==length){
		//					System.out.println("BEFORE: "+minlength[sudoku_num+start]+" ii: "+(sudoku_num+start));
							minlength[sudoku_num+start]-=1;								
		//					System.out.println("After: "+minlength[sudoku_num+start]+" ii: "+(sudoku_num+start));
							break;
						}
					}
					
					//may have to do something about last sudoku read in			  
		//			System.out.println("BEFORE: "+minlength[sudoku_num+start]+" ii: "+(sudoku_num+start));
					minlength[sudoku_num+start]-=1;								
			//		System.out.println("After: "+minlength[sudoku_num+start]+" ii: "+(sudoku_num+start));
								
				}
			}
							
							
						
			else{
				
				if(Lastfilled.size()==2){
				//	System.out.println("YOO: "+ Lastfilled.get(0) + "NO: "+ Lastfilled.get(1));
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
					
			//		System.out.println("INDEXXX: "+index+"max length "+max);
					
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
				//overlap
				}
			dashlen=0;

		}
		
		for(int i=0;i<Sudokus.length;i++){
			Sudokus[i].setGrid();
		}
		
		Samurai x=new Samurai(Sudokus);
	/*	boolean done=RecurSolve(x);
		if (done){
			boolean wrong=Check(x);
		}*/
		x.Solution();
	//	System.out.println(x.toString());
		boolean wrong=CheckWrong(x.Suds);
		boolean done =CheckDone(x.Suds);
		if(!wrong&&!done){
			Samurai z=Check(x.Suds);
			if(z==null){
				System.out.println("WAIT WHAT HAPPENED");
			}
			else{
				//Samurai r=new Samurai(Sudokus);
				//System.out.println(r.toString());
				System.out.println(z.toString());	
				//System.out.println(x.toString());
			}
		}
		else if(done){
			System.out.println(x.toString());
		}
		sc.close();
		fileread.close();
		
	}
/*	public static boolean Check(Samurai x){
		boolean wrong=false;
		boolean done=true;
		do{
//			done=true;
		//	int SudNum = 0;
		//	int min=10;
			for(int i=0;i<5;i++){
				done=true;
				boolean finish=x.Suds[i].filled();
				if(!finish){
				//	System.out.println("HI");	
					done=false;
					boolean temp=false;
					temp=x.Suds[i].CantFinish();
				//	System.out.println(temp);
					if(temp){
						//System.out.println("YOO");
						wrong=true;
						return wrong;
					}
				}
			}
		}while(!done);
		return wrong;
	}*/
/*	public static boolean RecurSolve(Samurai x){
		x.Solution();
		boolean wrong=Check(x);
		boolean x.done();
		return false;
		
	}*/
	public static boolean CheckWrong(Sudoku[] Sudokus){
//		boolean done=true;
		boolean wrong=false;
//			done=true;
			for(int i=0;i<5;i++){
//				done=true;
				boolean finish=Sudokus[i].filled();

				if(!finish){
				//	System.out.println("HI");	
	//				done=false;
					boolean temp=false;
					temp=Sudokus[i].CantFinish();
//					boolean temp2=Sudokus[i].wrong();
				//	System.out.println(temp);
					if(temp){
						//System.out.println("YOO");
						wrong=true;
						return wrong;
					}
					
				}
				
			}
		return wrong;
	}
	public static boolean CheckDone(Sudoku[] Sudokus){
		boolean done=true;
			for(int i=0;i<5;i++){
				//done=true;
				boolean finish=Sudokus[i].filled();
				if(!finish){
				//	System.out.println("HI");	
					done=false;
					return done;
				}
			}
			return done;
	}
	
/*	public static boolean Check(Sudoku[] Sudokus){
		boolean done=true;
		boolean wrong=false;
		do{
//			done=true;
			int SudNum = 0;
			int min=10;
			for(int i=0;i<5;i++){
				done=true;
				boolean finish=Sudokus[i].filled();

				if(!finish){
				//	System.out.println("HI");	
					done=false;
					boolean temp=false;
					temp=Sudokus[i].CantFinish();
				//	System.out.println(temp);
					if(temp){
						//System.out.println("YOO");
						wrong=true;
						return wrong;
					}
					int x=Sudokus[i].findfirst();
					if(x<min){
						min=0;
						SudNum=i;
			//			System.out.println("YO");
					}
					
				}
				
			}
			if(!done){
				Samurai x=new Samurai(Sudokus);
				x=Recur(Sudokus,SudNum);
				if(x==null){
					wrong=true;
					return wrong;
				}
				if(x!=null){
					Sudokus=x.Suds;
					done=true;
				}
			}
		}while(!done);
		
		return wrong;
	}*/
	
	public static Samurai Check(Sudoku[] Sudokus){
	//	boolean done=true;
		//boolean wrong=false;
//			done=true;
		int SudNum = 0;
		int min=10;
		for(int i=0;i<5;i++){
		//	System.out.println("HI");	
			boolean temp=false;
			temp=Sudokus[i].CantFinish();
		//	System.out.println(temp);
			if(temp){
				//System.out.println("YOO");
		//		wrong=true;
				return null;
			}
			int x=Sudokus[i].findfirst();
			if(x<min){
				min=0;
				SudNum=i;
	//			System.out.println("YO");
			}
				
			
		}
			Samurai x=new Samurai(Sudokus);
			
			x=Recur(Sudokus,SudNum);
			if(x==null){
			//	wrong=true;
				return null;
			}
			else {
				Sudokus=x.Suds;
				return x;
			//	done=true;
			}
//			return x;
		}
//	return null;	

/*	public static Samurai Recur(Sudoku[] sudokus, int SudNum){
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
		}
	//	copies.get(0).Suds[3].tiles[0][2].value=7;
	//	System.out.println("YOO:: "+copies.get(1).Suds[3].tiles[0][2].value+" NOOO:: "+copies.get(0).Suds[3].tiles[0][2].value);
	//	System.exit(0);
		boolean wrong=false;
		boolean cont=true;
		int index=5;
		//int index2=5;
		for(int i=0;i<copies.size();i++){
			if(cont){
				copies.get(i).Suds[SudNum].tiles[row][col].value=poss.get(i);
				copies.get(i).Suds[SudNum].tiles[row][col].possible.clear();
				copies.get(i).Solution();
				wrong=Check(copies.get(i).Suds);
				if(wrong && i==copies.size()-1){
					return null;
				}
				else if(!wrong){
					cont=false;
					index=i;
	//				return copies.get(i).Suds;
				}
			}
		}
		if(index==5){
		//	System.out.println("NO SOLUTION");
			return null;
		}
		else{
			System.out.println("DONE");
//			sudokus=copies.get(index).Suds;
			Samurai x1=new Samurai(copies.get(index).Suds);
			System.out.println(x1.toString());
			return x1;
		}
		
	}*/

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
		}
	//	copies.get(0).Suds[3].tiles[0][2].value=7;
	//	System.out.println("YOO:: "+copies.get(1).Suds[3].tiles[0][2].value+" NOOO:: "+copies.get(0).Suds[3].tiles[0][2].value);
	//	System.exit(0);
		//boolean wrong=false;
		boolean cont=true;
		int index=5;
		//int index2=5;
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
						return null;
					}
					/*else if(!fail&&CheckDone(wrong.Suds)){
						return wrong;
					}*/
					else if(!fail){
						cont=false;
					//	return wrong;
						index=i;
	//				return copies.get(i).Suds;
					}
				}
			}
		}
		if(index==5){
		//	System.out.println("NO SOLUTION");
			return null;
		}
		else{
			System.out.println("DONE");
//			sudokus=copies.get(index).Suds;
			Samurai x1=new Samurai(copies.get(index).Suds);
			System.out.println(x1.toString());
			return x1;
		}
		
	}

	public static Sudoku[] Copy(Sudoku[] sudokus){
		Sudoku[] duplicate=new Sudoku[5];
		//	ArrayList<ArrayList<tile>>=new ArrayList<ArrayList<tile>()>();
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
							//		System.out.println("HIII:: "+x.value);
							//		System.out.println(sudokus[k].tiles[i][j].value);
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
						//			System.out.println(sudokus[k].tiles[i][j].value);
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
						//			System.out.println(x.value);
									duplicate[k].insert(x);
								}
								else if(j>=6){
									tile x=temp2[i][j-6];
						//			System.out.println(x.value);
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
						//			System.out.println(sudokus[k].tiles[i][j].value);
									temp[i-6][j]=x;
									duplicate[k].insert(x);
									if(sudokus[k].tiles[i][j].value==0){
										ArrayList<Integer> poss=sudokus[k].tiles[i][j].possible;
										duplicate[k].tiles[i][j].inserPos(poss);
									}							}
								else if(j>=6){
									tile x=new tile(sudokus[k].tiles[i][j].value);
						//			System.out.println(sudokus[k].tiles[i][j].value);
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
						//			System.out.println(x.value);
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
							//		System.out.println(sudokus[k].tiles[i][j].value);
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
		//System.out.println("HELLO");
		//System.out.println(row+" "+col);
/*		for(int i=0;i<poss.size();i++){
			if(i<z.Suds[SudNum].tiles[row][col].possible.size()){
				//System.out.println("Hello");
				if(cont){
					z.Suds[SudNum].tiles[row][col].value=z.Suds[SudNum].tiles[row][col].possible.get(i);	
					z.Suds[SudNum].tiles[row][col].possible.clear();
					System.out.println("Here "+z.Suds[SudNum].tiles[row][col].value);
					z.Solution();
					System.out.println(z.toString());
					wrong=Check(z.Suds);
					if(wrong){
					//	System.out.println("HII");
						z.Suds[SudNum].tiles[row][col].value=0;
						z.Suds[SudNum].tiles[row][col].inserPos(poss);
						//System.out.println(poss.size());
						//System.out.println(duplicate[SudNum].tiles[row][col].possible.get(0)+" VAL: "+duplicate[SudNum].tiles[row][col].possible.get(1));
					}
					else{
						cont=false;
					}
				}
			}
		}*/
	
		
		
		
		/*	for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				System.out.print(temp[i][j].value);
			}
			System.out.println();
		}
	//	duplicate[1].tiles[8][0].value=8;
		System.out.println(duplicate[1].tiles[8][0].value);
		System.exit(0);*/
//		Samurai z=new Samurai(duplicate);
		
		
		
	//	System.out.println(poss.size());
	
	//	System.out.println(duplicate[SudNum].tiles[row][col].possible.get(0)+" VAL: "+duplicate[SudNum].tiles[row][col].possible.get(1));
		//System.exit(0);
	
//		z.Suds[3].tiles[0][2].possible.add(6);
//		System.out.println(z.Suds[3].tiles[0][2].possible.size()+ "  Another:  "+ sudokus[3].tiles[0][2].possible.size());
		//sudokus=z.Suds;
		
	}



