
public class BridgeCrossing {
	public static void main (String[] args){
		
		
		
		Executor<BridgeState> ex = new DFSExecutor<BridgeState>();
		//((AstarExecutor) ex).addHeuristic(new DistanceHeuristic());
		
		BridgeState base = new BridgeState(0, 0);
		
		for (int i = 3; i < 8; i++){
			base.addEntity(Utility.fibonacci(i), 1);
		}
		
		
		BridgeState goal = Utility.invertBridge(base);
		
		/** Parameters **/
		
		ex.setBase(base);
		ex.setGoal(goal);
		//ex.setSolutionCount(1);
		//ex.setFindCost(i);
		//ex.setMaxExamine(i);
		//ex.setMaxDifference(1);
		//ex.setRelax(10);
		
		//EXECUTE
		ex.execute();
	}
}