
public class BridgeCrossing {
	public static void main (String[] args){
		
		Executor ex = new BFSExecutor();
		
		State goal = new State(0, 0);
		goal.addEntity(1, 0);
		goal.addEntity(3, 0);
		goal.addEntity(5, 0);
		goal.addEntity(8, 0);
		goal.addEntity(13, 0);
		
		State base = new State(0, 0);
		base.addEntity(1, 1);
		base.addEntity(3, 1);
		base.addEntity(5, 1);
		base.addEntity(8, 1);
		base.addEntity(13, 1);
		
		ex.setBase(base);
		ex.setGoal(goal);
		
		ex.execute();
	}
}