
public class PegSolitaire {
	
	public static void main (String[] args){
		
		DFS<PegState> ex = new DFS<PegState>();
		//ex.addHeuristic(new Central());
		//ex.addHeuristic(new AvoidIsolated());
		//ex.addHeuristic(new ClearEdges());
		
		PegState base = new PegState(0, 0);
		
		base.setBoard(Layouts.english);
		//base.setMoves(Layouts.triangleMoves);
		//base.triangle = true;
		
		//ex.verbose(1);
		//ex.slow();
		ex.setBase(base);
		ex.setSolutionCount(1);

		
		
		ex.execute();
		
	}

}
