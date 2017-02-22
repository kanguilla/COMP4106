
public class Agent {
	public static void main (String[] args){
		
		Executor ex = new DFSExecutor();
		
		State goal = new State(0, 0);
		goal.addEntity(1, 1);
		goal.addEntity(3, 1);
		goal.addEntity(5, 1);
		goal.addEntity(8, 1);
		goal.addEntity(13, 1);
		
		State base = new State(0, 0);
		base.addEntity(1, 0);
		base.addEntity(3, 0);
		base.addEntity(5, 0);
		base.addEntity(8, 0);
		base.addEntity(13, 0);
		
		ex.setBase(base);
		ex.setGoal(goal);
		
		ex.execute();
	}
}