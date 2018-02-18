
public class PegSolitaire {
	
	public static void main (String[] args){
		DFSExecutor<State> ex = new DFSExecutor<State>();
		//ex.addHeuristic(new Central());
		//ex.addHeuristic(new KeepTogether());
		//ex.addHeuristic(new Central());
		
		SmallPegState base = new SmallPegState(0, 0);
		base.log("Initiated");
		
		base.initC();
		
		//ex.verbose = true;
		//ex.slow = true;
		ex.setBase(base);
		
		ex.execute();
		
	}
	

}
