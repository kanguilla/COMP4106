
public class PegSolitaire {
	
	public static void main (String[] args){
		
		Astar<PegState> ex = new Astar<PegState>();
		//ex.addHeuristic(new Central());
		//ex.addHeuristic(new KeepTogether());
		
		PegState base = new PegState(0, 0);
		base.log("Initiated");
		
		base.setBoard(PegLayouts.english);
		
		ex.verbose(10000);
		//ex.slow();
		ex.setBase(base);
		ex.setSolutionCount(1);

		
		
		ex.execute();
		
	}

}
