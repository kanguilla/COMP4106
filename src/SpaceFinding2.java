
public class SpaceFinding2 {
	public static void main (String[] args){
		
		Executor<TileState> ex = new BFS<TileState>();
		//((AstarExecutor) ex).addHeuristic(new CountingHeuristic());
		//((AstarExecutor) ex).addHeuristic(new VaultHeuristic());
		
		TileState base = new TileState(0, 0);
		base.addTile("I", 0, 0);
		base.addTile("J", 0, 1);
		base.addTile("H", 0, 2);
		base.addTile("F", 0, 3);
		base.addTile("E", 0, 4);
		base.addTile("D", 1, 0);
		base.addTile("G", 1, 1);
		base.addTile("B", 1, 2);
		base.addTile("C", 1, 3);
		base.addTile("A", 1, 4);
		
		TileState goal = new TileState(0, 0);
		goal.addTile("A", 0, 0);
		goal.addTile("B", 0, 1);
		goal.addTile("C", 0, 2);
		goal.addTile("D", 0, 3);
		goal.addTile("E", 0, 4);
		goal.addTile("F", 1, 0);
		goal.addTile("G", 1, 1);
		goal.addTile("H", 1, 2);
		goal.addTile("I", 1, 3);
		goal.addTile("J", 1, 4);
		
		ex.setBase(base);
		ex.setGoal(goal);
		//ex.setMaxDifference(20);
		//ex.setRelax(20);
		
		//ex.setMaxExamine(5000);
		ex.setSolutionCount(1);
		ex.execute();
		
	}
}