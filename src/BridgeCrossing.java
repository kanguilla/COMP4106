
public class BridgeCrossing {
	public static void main (String[] args){
		
		Executor ex = new AstarExecutor();
		//((AstarExecutor) ex).addHeuristic(new DelayHeuristic());
		
		BridgeState base = new BridgeState(0, 0);
		
		for (int i = 3; i < 10; i++){
			base.addEntity(Utility.fibonacci(i), 1);
		}
		
		BridgeState goal = Utility.invertBridge(base);
		
		ex.setBase(base);
		ex.setGoal(goal);
		//ex.setSolutionCount(1);
		ex.execute();
	}
}