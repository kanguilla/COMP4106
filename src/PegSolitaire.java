
public class PegSolitaire {
	
	public static void main (String[] args){
		
		Astar<PegState> ex = new Astar<PegState>();
		//ex.addHeuristic(new Central());
		//ex.addHeuristic(new AvoidIsolated());
		ex.addHeuristic(new ClearEdges());
		
		PegState base = new PegState(0, 0);
		
		base.setBoard(PegLayouts.euro);
		
		ex.verbose(100);
		//ex.slow();
		ex.setBase(base);
		ex.setSolutionCount(1);

		
		
		ex.execute();
		
	}

}
