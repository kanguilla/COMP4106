import java.util.ArrayList;
import java.util.HashMap;

public class SmallPegState extends State{

	int s = 7;
	int p = 32;
	
	HashMap<Integer, Integer> board;

	
	String code;
	
	
	String log = "";
	
	public void initA(){
		s = 7;
		p = 36;
		board = new HashMap<Integer, Integer>();
		for (int i = 0; i < s; i++){
			for (int j = 0; j < s; j++){
				board.put((i * 10) + j, 1);
			}
		}
		
		board.put(00, 2);
		board.put(01, 2);
		board.put(05, 2);
		board.put(06, 2);
		
		board.put(10, 2);
		board.put(16, 2);
		
		board.put(50, 2);
		board.put(56, 2);
		
		board.put(60, 2);
		board.put(61, 2);
		board.put(65, 2);
		board.put(66, 2);
		
		board.put(23, 0);

	}
	public void initC(){
		s = 7;
		p = 14;
		board = new HashMap<Integer, Integer>();
		for (int i = 0; i < s; i++){
			for (int j = 0; j < s; j++){
				board.put((i * 10) + j, 0);
	
			}
		}
		
		board.put(00, 2);
		board.put(01, 2);
		board.put(05, 2);
		board.put(06, 2);
		
		board.put(10, 2);
		board.put(16, 2);
		
		board.put(50, 2);
		board.put(56, 2);
		
		board.put(60, 2);
		board.put(61, 2);
		board.put(65, 2);
		board.put(66, 2);
		
		board.put(20, 1);
		board.put(30, 1);
		board.put(40, 1);
		board.put(21, 1);
		board.put(31, 1);
		board.put(32, 1);
		board.put(43, 1);
		board.put(24, 1);
		board.put(34, 1);
		board.put(44, 1);
		board.put(54, 1);
		board.put(64, 1);
		board.put(15, 1);
		board.put(55, 1);
		
	}

	public SmallPegState(int depth, int distance) {
		super(depth, distance);
		s = 7;
		p = 32;
		board = new HashMap<Integer, Integer>();
		totalCost = p;

	}

	public void log(String change){
		this.log = change;
	}
	
	@Override
	public int difference(State other) {
		return ((SmallPegState) other).p - this.p;
	}

	
	
	@Override
	public ArrayList<State> expand() {
		ArrayList<State> out = new ArrayList<State>();

		int d = 0;

		int[] xmoves = {0,-1,1,0};
		int[] ymoves = {1,0,0,-1};
		
		for (int x = 0; x < s; x++) {
			for (int y = 0; y < s; y++) {
				
				if (board.get(x*10 + y) != 1)continue;
				
				for (int i = 0; i < xmoves.length; i++) {
					
					int xtarget = x + (2 * xmoves[i]);
					int ytarget = y + (2 * ymoves[i]);
					
					if (xtarget < 0 || xtarget >= s)continue;
					if (ytarget < 0 || ytarget >= s)continue;
					
					int xhopped = x + xmoves[i];
					int yhopped = y + ymoves[i];
					
					if (board.get(xtarget*10 + ytarget) == 0 && board.get(xhopped*10 + yhopped) == 1){
						SmallPegState ns = new SmallPegState(this.depth+1, d++);
						ns.board = new HashMap<Integer, Integer>(board);
						
						
						ns.board.put(xtarget*10 + ytarget, 1);
						ns.board.put(x*10 + y, 0);
						ns.board.put(xhopped * 10 + yhopped, 0);
						
						ns.history = ("Move: " + x + "," + y + " to " + xtarget + "," + ytarget);
						ns.p = p-1;
						//ns.codify();
						
						out.add(ns);
					}

				}
			
			}
			
		}
		
		return out;
	}

//	public void codify(){
//		String[] codes = {"","","","","","","",""};
//		
//		for (int x = 0; x < s; x++){
//			for (int y = 0; y < s; y++){
//				codes[0] += board[x][y];
//				codes[1] += board[y][x];
//			}
//		}
//		
//		for (int x = s-1; x >= 0; x--){
//			for (int y = 0; y < s; y++){
//				codes[2] += board[x][y];
//				codes[3] += board[y][x];
//			}
//		}
//		
//		for (int x = 0; x < s; x++){
//			for (int y = s-1; y >= 0; y--){
//				codes[4] += board[x][y];
//				codes[5] += board[y][x];
//			}
//		}
//		
//		for (int x = s-1; x >= 0; x--){
//			for (int y = s-1; y >= 0; y--){
//				codes[6] += board[x][y];
//				codes[7] += board[y][x];
//			}
//		}
//		
//		Arrays.sort(codes);
//		code = codes[0];
//		
//	}
	
	
	@Override
	public boolean equals(Object other) {
		
		
		
		SmallPegState p = (SmallPegState) other;
		
		return board.equals(p.board);
		
	}

	@Override
	public int hashCode() {
		 int result = 17;
	        result = 31 * result + board.hashCode();
	        return result;
	}

	@Override
	public String toString() {
		String out = "";
		
		for (int i = 0; i < s; i++){
			for (int j = 0; j < s; j++){
				
				
				switch (board.get(i*10+j)) {
				case 2: out += "- "; break;
				case 1: out += "O "; break;
				case 0: out += "  "; break;
				}
			}
			out += "\n";
		}
		out += "Pegs  left:" + this.p + "    Depth: " + this.depth + "\n";
		out += this.log;
		return out;
	}

	@Override
	public boolean isWinning() {
		return (p == 1);
	}

	@Override
	public String speak() {
		return "pegs: " + p;
	}
	@Override
	public String code() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
