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


class KeepTogetherHeuristic extends Heuristic<PegState> {

	@Override
	public int eval(PegState current, PegState other, PegState goal) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return "Tries to keep all of the pegs close together";
	}
	
}



















