
public class PegSolitaire {
	
	public static void main (String[] args){
		
		DFSExecutor<PegState> ex = new DFSExecutor<PegState>();
		//ex.addHeuristic(new Central());
		//ex.addHeuristic(new KeepTogether());
		
		PegState base = new PegState(0, 0);
		base.log("Initiated");
		
		base.setBoard(PegLayouts.euro);
		
		//ex.verbose(10000);
		//ex.slow();
		ex.setBase(base);
		ex.setSolutionCount(1);

		
		
		ex.execute();
		
	}

}
