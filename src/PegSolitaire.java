
public class PegSolitaire {
	
	public static void main (String[] args){
		
		DFSExecutor<State> ex = new DFSExecutor<State>();
		//ex.addHeuristic(new Central());
		//ex.addHeuristic(new KeepTogether());
		//ex.addHeuristic(new Central());
		
		PegState base = new PegState(0, 0);
		base.log("Initiated");
		
		base.setBoard(PegLayouts.euro);
		
		
		ex.verbose(1);
		ex.slow();
		ex.setBase(base);
		ex.setSolutionCount(1);
		
		ex.execute();
		
	}

}
