import java.util.ArrayList;
import java.util.Arrays;

public class PegState extends State{

	boolean triangle = false;
	byte w = 7;
	byte h = 7;
	byte pegs = 32;
	byte[][] board;
	int lastMove = 0;
	String code = "empty";
	String log = "";

	byte[][] moves = {{0,1},
			{1,0},
			{-1,0}, 
			{0,-1}};
	
	public PegState(int depth, int distance) {
		super(depth, distance);
		w = h = 7;
		pegs = 32;
		board = new byte[w][h];
		totalCost = pegs;
		// TODO Auto-generated constructor stub
	}
	
	public PegState(int depth, int distance, byte w, byte h) {
		super(depth, distance);
		this.w = w;
		this.h = h;
		pegs = 32;
		board = new byte[w][h];
		totalCost = pegs;
		// TODO Auto-generated constructor stub
	}

	public void log(String change){
		this.log = change;
	}
	
	public void setMoves(byte[][] moves) {
		this.moves = moves;
	}
	
	@Override
	public int difference(State other) {
		return ((PegState) other).pegs - this.pegs;
	}

	
	
	@Override
	public ArrayList<State> expand() {
		
		
		
		ArrayList<State> out = new ArrayList<State>();
		
		int d = 0;
		
		for (int y = h-1; y > -1; y--) {
			for (int x = w-1; x > -1; x--) {

				if (board[x][y] != 1)
					continue;
				for (int i = 0; i < moves.length; i++) {
					int xtarget = x + (2 * moves[i][0]);
					int ytarget = y + (2 * moves[i][1]);

					if (xtarget < 0 || xtarget >= w)
						continue;
					if (ytarget < 0 || ytarget >= h)
						continue;

					int xhopped = x + moves[i][0];
					int yhopped = y + moves[i][1];

					if (board[xtarget][ytarget] == 0 && board[xhopped][yhopped] == 1) {
						PegState ns = new PegState(this.depth + 1, d++, this.w, this.h);
						ns.triangle = triangle;
						ns.moves = moves;
						for (int a = 0; a < board.length; a++) {
							System.arraycopy(board[a], 0, ns.board[a], 0, board[0].length);
						}

						ns.board[xtarget][ytarget] = 1;
						ns.board[x][y] = 0;
						ns.board[xhopped][yhopped] = 0;
						
						ns.history = ("Move: " + x + "," + y + " to " + xtarget + "," + ytarget);
						ns.pegs = (byte) (pegs - 1);
						ns.totalCost = ns.pegs;
						out.add(ns);
					}

				}
				lastMove = (lastMove + 1) % 4;
			}
		}

		return out;
	}
	
	@Override
	public String code(){
		
		if (code != "empty")return code;
		String[] codes = {"","","","","","","",""};
		
		for (int x = 0; x < w; x++){
			for (int y = 0; y < h; y++){
				codes[0] += board[x][y];
				codes[1] += board[y][x];
			}
		}
		
		for (int x = w-1; x >= 0; x--){
			for (int y = 0; y < h; y++){
				codes[2] += board[x][y];
				codes[3] += board[y][x];
			}
		}
		
		for (int x = 0; x < w; x++){
			for (int y = h-1; y >= 0; y--){
				codes[4] += board[x][y];
				codes[5] += board[y][x];
			}
		}
		
		for (int x = w-1; x >= 0; x--){
			for (int y = h-1; y >= 0; y--){
				codes[6] += board[x][y];
				codes[7] += board[y][x];
			}
		}
		
		Arrays.sort(codes);
		code = codes[0];
		return code;
	}
	
	
	@Override
	public boolean equals(Object other) {
		PegState o = (PegState) other;
		if (o.pegs != this.pegs)return false;
		return o.code().equals(this.code());
		//return Arrays.deepEquals(o.board, this.board);
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

		if(!triangle) {
			for (int i = 0; i < w; i++){
				for (int j = 0; j < h; j++){
					
					
					switch (board[i][j]) {
					case 2: out += "- "; break;
					case 1: out += "O "; break;
					case 0: out += "  "; break;
					}
				}
				out += "\n";
			}
			out += "Pegs  left:" + this.pegs + "    Depth: " + this.depth + "\n";
			out += this.log;
			return out;
		}else {
			for (int i = 0; i < w; i++){
				
				int spaces = w-i;
				for (int k = 0; k < spaces; k++) {
					out += " ";
				}
				
				for (int j = 0; j < h; j++){
					
					switch (board[i][j]) {
					case 2: out += "- "; break;
					case 1: out += "O "; break;
					case 0: out += "  "; break;
					}
				}
				
				for (int k = 0; k < spaces; k++) {
					out += " ";
				}
				
				
				out += "\n";
			}
			out += "Pegs  left:" + this.pegs + "    Depth: " + this.depth + "\n";
			out += this.log;
			return out;
		}
		
	}
	

	@Override
	public boolean isWinning() {
		return (pegs == 1);
	}

	@Override
	public String speak() {
		return "pegs: " + pegs;
	}

	public void setBoard(byte[][] board) {
		this.board = board;
		this.w = (byte) board[1].length;
		this.h = (byte) board.length;
		pegs = 0;
		for (int i = 0; i < w; i++){
			for (int j = 0; j < h; j++){
				if(board[i][j] == 1)pegs++;
			}
		}
	}

}
