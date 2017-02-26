
public class BridgeCrossing {
	public static void main (String[] args){
		
		Executor ex = new AstarExecutor();
		//((AstarExecutor) ex).addHeuristic(new LowWasteHeuristic());
		
		BridgeState base = new BridgeState(0, 0);
		base.addEntity(1, 1);
		base.addEntity(3, 1);
		base.addEntity(5, 1);
		base.addEntity(8, 1);
		base.addEntity(13, 1);
		
		BridgeState goal = Utility.invertBridge(base);
		
		ex.setBase(base);
		ex.setGoal(goal);
		ex.setSolutionCount(1);
		ex.execute();
	}
}