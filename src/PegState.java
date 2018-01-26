import java.util.ArrayList;

public class PegState extends State{

	int s = 7;
	int p = 32;
	int[][] board = new int[s][s];
	
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

		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				
				if (board[i][j] != 1)continue;
				
				if (i-2 >= 0 && board[i-2][j] == 0 && board[i-1][j] == 1){
					PegState ns = new PegState(this.depth+1, d++);
					for(int x=0; x<s; x++){
						for(int y=0; y<s; y++){
							ns.board[x][y]=board[x][y];
						}
					}
					ns.board[i-2][j] = 1;
					ns.board[i][j] = 0;
					ns.board[i-1][j] = 0;
					ns.log("UP: " + i + "," + j + " to " + (i-2) + "," + j);
					ns.p = p-1;
					out.add(ns);
				}
				if (i+2 < 7 && board[i+2][j] == 0 && board[i+1][j] == 1){
					PegState ns = new PegState(this.depth+1, d++);
					for(int x=0; x<s; x++){
						for(int y=0; y<s; y++){
							ns.board[x][y]=board[x][y];
						}
					}
					ns.board[i+2][j] = 1;
					ns.board[i][j] = 0;
					ns.board[i+1][j] = 0;
					ns.log("DOWN: " + i + "," + j + " to " + (i+2) + "," + j);
					ns.p = p-1;
					out.add(ns);
				}
				if (j-2 >= 0 && board[i][j-2] == 0 && board[i][j-1] == 1){
					PegState ns = new PegState(this.depth+1, d++);
					for(int x=0; x<s; x++){
						for(int y=0; y<s; y++){
							ns.board[x][y]=board[x][y];
						} 
					}
					ns.board[i][j-2] = 1;
					ns.board[i][j] = 0;
					ns.board[i][j-1] = 0;
					ns.log("LEFT: " + i + "," + j + " to " + (i) + "," + (j-2));
					ns.p = p-1;
					out.add(ns);
				}
				if (j+2 <7 && board[i][j+2] == 0 && board[i][j+1] == 1){
					PegState ns = new PegState(this.depth+1, d++);
					for(int x=0; x<s; x++){
						for(int y=0; y<s; y++){
							ns.board[x][y]=board[x][y];
						}
					}
					ns.board[i][j+2] = 1;
					ns.board[i][j] = 0;
					ns.board[i][j+1] = 0;
					ns.log("RIGHT: " + i + "," + j + " to " + (i) + "," + (j+2));
					ns.p = p-1;
					out.add(ns);
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
				out += board[i][j] + " ";
			}
			out += "\n";
		}
		out += "Pegs  left:" + this.p + "    Depth: " + this.depth + "\n";
		out += this.log;
		return out;
	}

	@Override
	public boolean isFinal() {
		return (p == 1);
	}

	@Override
	public String speak() {
		return "pegs: " + p;
	}
	
	

}