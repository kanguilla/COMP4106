
public class PegSolitaire {
	
	public static void main (String[] args){
		
		Astar<PegState> ex = new Astar<PegState>();
		ex.addHeuristic(new Central());
		//ex.addHeuristic(new AvoidIsolated());
		ex.addHeuristic(new ClearEdges());
		
		PegState base = new PegState(0, 0);
		
		base.setBoard(Layouts.euro);
		//base.setMoves(Layouts.triangleMoves);
		//base.triangle = true;
		
		ex.verbose(1);
		//ex.slow();
		ex.setBase(base);
		ex.setSolutionCount(1);

		
		
		ex.execute();
		
	}

}
