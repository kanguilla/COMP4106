
public class SpaceFinding {
	public static void main (String[] args){
		
		Executor ex = new AstarExecutor();
		((AstarExecutor) ex).addHeuristic(new CountingHeuristic());
		((AstarExecutor) ex).addHeuristic(new VaultHeuristic());
		
		TileState base = new TileState(0, 0);
		base.addTile(" ", 0, 0);
		base.addTile("1", 0, 1);
		base.addTile("H", 0, 2);
		base.addTile("Y", 0, 3);
		base.addTile("5", 0, 4);
		base.addTile("6", 1, 0);
		base.addTile("M", 1, 1);
		base.addTile("O", 1, 2);
		base.addTile("9", 1, 3);
		base.addTile("D", 1, 4);
		base.addTile("B", 2, 0);
		base.addTile("C", 2, 1);
		base.addTile("A", 2, 2);
		base.addTile("E", 2, 3);
		base.addTile("F", 2, 4);
		base.addTile("G", 3, 0);
		base.addTile("3", 3, 1);
		base.addTile("Z", 3, 2);
		base.addTile("J", 3, 3);
		base.addTile("L", 3, 4);
		base.addTile("7", 4, 0);
		base.addTile("N", 4, 1);
		base.addTile("8", 4, 2);
		base.addTile("Q", 4, 3);
		base.addTile("P", 4, 4);
		base.addTile("W", 5, 0);
		base.addTile("X", 5, 1);
		base.addTile("4", 5, 2);
		base.addTile("I", 5, 3);
		base.addTile(" ", 5, 4);
		
		
		TileState goal = new TileState(0, 0);
		goal.addTile("1", 0, 0);
		goal.addTile(" ", 0, 1);
		goal.addTile("3", 0, 2);
		goal.addTile("4", 0, 3);
		goal.addTile("5", 0, 4);
		goal.addTile("6", 1, 0);
		goal.addTile("7", 1, 1);
		goal.addTile("8", 1, 2);
		goal.addTile("9", 1, 3);
		goal.addTile("A", 1, 4);
		goal.addTile("B", 2, 0);
		goal.addTile("C", 2, 1);
		goal.addTile("D", 2, 2);
		goal.addTile("E", 2, 3);
		goal.addTile("F", 2, 4);
		goal.addTile("G", 3, 0);
		goal.addTile("H", 3, 1);
		goal.addTile("I", 3, 2);
		goal.addTile("J", 3, 3);
		goal.addTile("L", 3, 4);
		goal.addTile("M", 4, 0);
		goal.addTile("N", 4, 1);
		goal.addTile("O", 4, 2);
		goal.addTile("P", 4, 3);
		goal.addTile("Q", 4, 4);
		goal.addTile("W", 5, 0);
		goal.addTile("X", 5, 1);
		goal.addTile("Y", 5, 2);
		goal.addTile("Z", 5, 3);
		goal.addTile(" ", 5, 4);
		
		ex.setBase(base);
		ex.setGoal(goal);
		//ex.setMaxDifference(20);
		//ex.setRelax(20);
		
		//ex.setMaxExamine(5000);
		ex.setSolutionCount(1);
		ex.execute();
		
	}
}