import java.util.HashMap;
import java.util.Map.Entry;

public abstract class Heuristic<T> {
	public abstract int eval(T current, T other, T goal);
	public abstract String toString();
	
	
	
}

class DistanceHeuristic extends Heuristic<State>{
	@Override
	public int eval(State current, State other, State goal) {
		return current.difference(goal);
	}

	@Override
	public String toString() {
		return "Distance Heuristic (Uses the state's timeDifference formula)";
	}
}

class CountingHeuristic extends Heuristic<TileState>{
	
	@Override
	public String toString() {
		return "Counting Heuristic (TileState: Counts each tile that is the wrong place)";
	}

	@Override
	public int eval(TileState current, TileState other, TileState goal) {
		int c = 0;
		for (Entry<TileState.Pair, String> e : current.nodeMap.entrySet()){
			if (e.getValue() != goal.nodeMap.get(e.getKey())){
				c++;
			}
		}
		return c;
	}
	
}

class LowWasteHeuristic extends Heuristic<BridgeState>{

	@Override
	public int eval(BridgeState current, BridgeState other, BridgeState goal) {
		return current.timeDifference + current.totalCost;
	}

	@Override
	public String toString() {
		return "Low Waste (Attempts to minimize the timeDifference in travel times)";
	}
	
}

class DelayHeuristic extends Heuristic<BridgeState>{

	@Override
	public int eval(BridgeState current, BridgeState other, BridgeState goal) {
		BridgeState bCurrent = (BridgeState) current;
		
		return bCurrent.moved1 + bCurrent.moved2;
	}

	@Override
	public String toString() {
		return "Delay (Attempts to save the most expensive operations for last)";
	}
	
}

class VaultHeuristic extends Heuristic<TileState>{

	@Override
	public int eval(TileState current, TileState other, TileState goal) {
		int c = 0;
		
		TileState ns = new TileState(0, 0);
		ns.nodeMap = new HashMap<TileState.Pair, String>(goal.nodeMap);
		
		while (!ns.equals(goal)){
			for (Entry<TileState.Pair, String> e : current.nodeMap.entrySet()){
				TileState.Pair p1 = e.getKey();
				TileState.Pair p2 = null;
				for (Entry<TileState.Pair, String> f : goal.nodeMap.entrySet()){
					if (f.getValue().equals(e.getValue())){
						p2 = f.getKey();
					}
				}
				if (canVault(p1, p2)){
					c--;
				}
			}
		}
		
		return c;
	}

	@Override
	public String toString() {
		return "Vault (Prioritizes the vault move in attempt to be more effecient)";
	}
	
	private boolean canVault(TileState.Pair a, TileState.Pair b){
		if ((Math.abs(a.x - b.x) == 2) && (Math.abs(a.y - b.y) == 1))return true;
		if ((Math.abs(a.x - b.x) == 1) && (Math.abs(a.y - b.y) == 2))return true;
		return false;
	}
	
}


/** Peg Solitaire Heuristics **/


class KeepTogether extends Heuristic<PegState> {

	@Override
	public int eval(PegState current, PegState other, PegState goal) {
		int score = 0;
		for (int x = 0; x < current.s; x++) {
			for (int y = 0; y < current.s; y++) {
				if (current.board[x][y] == 1) {
					if (x > 0 && current.board[x-1][y] == 1) score--;
					if (x < 6 && current.board[x+1][y] == 1) score--;
					if (y < 6 && current.board[x][y+1] == 1) score--;
					if (y > 0 && current.board[x][y-1] == 1) score--;
				}
			}
		}
		return score;
	}

	@Override
	public String toString() {
		return "tries to keep all of the pegs close together";
	}
	
}

class Central extends Heuristic<PegState> {

	@Override
	public int eval(PegState current, PegState other, PegState goal) {
		int score = 0;
		for (int x = 0; x < current.s; x++) {
			for (int y = 0; y < current.s; y++) {
				if (current.board[x][y] == 1) {
					score = score + (Math.abs(x-3) + Math.abs(y-3))^3;
				}
			}
		}
		return score;
	}

	@Override
	public String toString() {
		return "tries to keep all of the pegs close together";
	}
	
}

class AvoidEdges extends Heuristic<PegState> {

	@Override
	public int eval(PegState current, PegState other, PegState goal) {
		int score = 0;
		if (current.board[0][2] == 1) score += 20;
		if (current.board[0][3] == 1) score += 20;
		if (current.board[0][4] == 1) score += 20;
		if (current.board[6][2] == 1) score += 20;
		if (current.board[6][3] == 1) score += 20;
		if (current.board[6][4] == 1) score += 20;

		if (current.board[2][0] == 1) score += 20;
		if (current.board[3][0] == 1) score += 20;
		if (current.board[4][0] == 1) score += 20;
		if (current.board[2][6] == 1) score += 20;
		if (current.board[3][6] == 1) score += 20;
		if (current.board[4][6] == 1) score += 20;
		
		System.out.println(score);
		return score;
	}

	@Override
	public String toString() {
		return "tries to keep all of the pegs close together";
	}
	
}



















