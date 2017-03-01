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
		
		return bCurrent.totalCost + bCurrent.moved1 + bCurrent.moved2;
	}

	@Override
	public String toString() {
		return "Delay (Attempts to save the most expensize operations for last)";
	}
	
}



