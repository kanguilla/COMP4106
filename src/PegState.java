import java.util.ArrayList;

public class PegState extends State{

	int s = 7;
	int p = 32;
	int[][] board = new int[s][s];
	
	int[] xmoves = {-1,0,1,0};
	int[] ymoves = {0,1,0,-1};
	
	
	String log = "";
	
	public void init(){
		for (int i = 0; i < s; i++){
			for (int j = 0; j < s; j++){
				board[i][j] = 1;
				if (i <= 1 && j <= 1)board[i][j] = 2;
				if (i <= 1 && j >= 5)board[i][j] = 2;
				if (i >= 5 && j >= 5)board[i][j] = 2;
				if (i >= 5 && j <= 1)board[i][j] = 2;
			}
		}
		board[3][3] = 0;
	}

	public PegState(int depth, int distance) {
		super(depth, distance);
		// TODO Auto-generated constructor stub
	}

	public void log(String change){
		this.log = change;
	}
	
	@Override
	public int difference(State other) {
		return ((PegState) other).p - this.p;
	}

	
	
	@Override
	public ArrayList<State> expand() {
		ArrayList<State> out = new ArrayList<State>();

		int d = 0;

		for (int x = 0; x < s; x++) {
			for (int y = 0; y < s; y++) {
				
				if (board[x][y] != 1)continue;
				
				for (int i = 0; i < xmoves.length; i++) {
					
					int xtarget = x + (2 * xmoves[i]);
					int ytarget = y + (2 * ymoves[i]);
					
					if (xtarget < 0 || xtarget >= s)continue;
					if (ytarget < 0 || ytarget >= s)continue;
					
					int xhopped = x + xmoves[i];
					int yhopped = y + ymoves[i];
					
					if (board[xtarget][ytarget] == 0 && board[xhopped][yhopped] == 1){
						PegState ns = new PegState(this.depth+1, d++);
						for(int a=0; a<s; a++){
							for(int b=0; b<s; b++){
								ns.board[a][b]=board[a][b];
							}
						}
						
						ns.board[xtarget][ytarget] = 1;
						ns.board[x][y] = 0;
						ns.board[xhopped][yhopped] = 0;
						
						ns.history = ("Move: " + x + "," + y + " to " + xtarget + "," + ytarget);
						ns.p = p-1;
						out.add(ns);
					}

				}
			
			}
			
		}
		
		return out;
	}

	@Override
	public boolean equals(Object other) {
		return board.equals(((PegState) other).board);
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
				
				
				switch (board[i][j]) {
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
	
	

}
