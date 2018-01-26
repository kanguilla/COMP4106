
public class PegSolitaire {
	
	public static void main (String[] args){
		Executor ex = new DFSExecutor();
		//((AstarExecutor) ex).addHeuristic(new DistanceHeuristic());
		
		PegState base = new PegState(0, 0);
		base.log("Initiated");
		
		base.init();
	
		
		ex.setBase(base);
		ex.slow = false;
		ex.execute();
		
	}
	

}
