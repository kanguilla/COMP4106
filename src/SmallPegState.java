import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SmallPegState extends State{

	int w = 7;
	int h = 7;
	int pegs = 32;
	int[][] board;
	
	String code = "empty";
	
	int[][] moves = {
			{0,1},
			{1,0},
			{-1,0}, 
			{0,-1}};
	
	
	String log = "";

	public SmallPegState(int depth, int distance) {
		super(depth, distance);
		w = h = 7;
		pegs = 32;
		board = new int[w][h];
		totalCost = pegs;
		// TODO Auto-generated constructor stub
	}

	public void log(String change){
		this.log = change;
	}
	
	@Override
	public int difference(State other) {
		return ((SmallPegState) other).pegs - this.pegs;
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
						SmallPegState ns = new SmallPegState(this.depth + 1, d++);

						for (int a = 0; a < board.length; a++) {
							System.arraycopy(board[a], 0, ns.board[a], 0, board[0].length);
						}

						ns.board[xtarget][ytarget] = 1;
						ns.board[x][y] = 0;
						ns.board[xhopped][yhopped] = 0;

						ns.history = ("Move: " + x + "," + y + " to " + xtarget + "," + ytarget);
						ns.pegs = pegs - 1;
						out.add(ns);
					}

				}

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
		SmallPegState o = (SmallPegState) other;
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
	}

	@Override
	public boolean isWinning() {
		
		if (Arrays.deepEquals(this.board, Layouts.euroReduced)){
			return true;
		}
		
		
		return (pegs == 1);
		
		
		
	}

	@Override
	public String speak() {
		return "pegs: " + pegs;
	}

	public void setBoard(int[][] board) {
		this.board = board;
		this.w = board[1].length;
		this.h = board.length;
		pegs = 0;
		for (int i = 0; i < w; i++){
			for (int j = 0; j < h; j++){
				if(board[i][j] == 1)pegs++;
			}
		}
	}

}
