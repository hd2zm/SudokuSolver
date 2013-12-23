import java.util.ArrayList;
public class tile {
	public ArrayList<Integer> row=new ArrayList<Integer>(0);
	public ArrayList<Integer> column=new ArrayList<Integer>(0);
	public int value=0;
	public ArrayList<Integer> gridnum=new ArrayList<Integer>(0);
	public ArrayList<Integer> sudokus=new ArrayList<Integer>(0);
	public ArrayList<Integer> possible=new ArrayList<Integer>(0);
	
	public tile(int val, ArrayList<Integer> poss){
		if(val>48){
			value=val-48;
		}
		else if(val>0 && val<=9){
			//System.out.println("YOO "+val);
			value=val;
		}
		else{
			for(int i=0;i<poss.size();i++){
				possible.add(poss.get(i));
			}
		}
	}
	public tile(int val){
		if(val>48){
			value=val-48;
		}
		else if(val>0 && val<=9){
		//	System.out.println("YOO "+val);
			value=val;
		}

		else{
			possible.add(1);
			possible.add(2);
			possible.add(3);
			possible.add(4);
			possible.add(5);
			possible.add(6);
			possible.add(7);
			possible.add(8);
			possible.add(9);
		}
	}
	public tile(ArrayList<Integer> r, ArrayList<Integer> c, int val){
		row=r;
		column=c;
		if(val>48){
			value=val-48;//48 is the ascii letter 0 that i have to subtract
//			System.out.println("YO:: "+value);
		}
		else{
			possible.add(1);
			possible.add(2);
			possible.add(3);
			possible.add(4);
			possible.add(5);
			possible.add(6);
			possible.add(7);
			possible.add(8);
			possible.add(9);
		}
	}
		public void SetgridNum(int num){
			gridnum.add(num);
		}
		public void Setsudoku(int num){
			sudokus.add(num);
		}
		public void insertRowCol(int r, int c){
			row.add(r);
			column.add(c);
		}
		public void inserPos(ArrayList<Integer> poss){
			possible.clear();
			for(int i=0;i<poss.size();i++){
				possible.add(poss.get(i));
			}
		}
}
