import java.util.HashMap;
import java.util.Map.Entry;

public abstract class Heuristic {
	public abstract int eval(State current, State other, State goal);
	public abstract String toString();
	
	
	
}

class DistanceHeuristic extends Heuristic{
	@Override
	public int eval(State current, State other, State goal) {
		return current.difference(goal);
	}

	@Override
	public String toString() {
		return "Distance Heuristic (Uses the states timeDifference formula)";
	}
}

class CountingHeuristic extends Heuristic{
	@Override
	public int eval(State current, State other, State goal) {
		int c = 0;
		TileState tCurrent = (TileState) current;
		TileState tGoal = (TileState) goal;
		for (Entry<TileState.Pair, String> e : tCurrent.nodeMap.entrySet()){
			if (e.getValue() != tGoal.nodeMap.get(e.getKey())){
				c++;
			}
		}
		return c;
	}

	@Override
	public String toString() {
		return "Counting Heuristic (TileState: Counts each tile that is the wrong place)";
	}
	
}

class LowWasteHeuristic extends Heuristic{

	@Override
	public int eval(State current, State other, State goal) {
		return ((BridgeState) current).timeDifference + current.totalCost;
	}

	@Override
	public String toString() {
		return "Low Waste (Attempts to minimize the timeDifference in travel times)";
	}
	
}

class DelayHeuristic extends Heuristic{

	@Override
	public int eval(State current, State other, State goal) {
		BridgeState bCurrent = (BridgeState) current;
		
		return bCurrent.moved1 + bCurrent.moved2;
	}

	@Override
	public String toString() {
		return "Delay (Attempts to save the most expensive operations for last)";
	}
	
}

class VaultHeuristic extends Heuristic{

	@Override
	public int eval(State current, State other, State goal) {
		int c = 0;
		
		TileState tCurrent = (TileState) current;
		TileState tGoal = (TileState) goal;
		
		TileState ns = new TileState(0, 0);
		ns.nodeMap = new HashMap<TileState.Pair, String>(tGoal.nodeMap);
		
		while (!ns.equals(tGoal)){
			for (Entry<TileState.Pair, String> e : tCurrent.nodeMap.entrySet()){
				TileState.Pair p1 = e.getKey();
				TileState.Pair p2 = null;
				for (Entry<TileState.Pair, String> f : tGoal.nodeMap.entrySet()){
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



