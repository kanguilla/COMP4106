
public class PegSolitaire {
	
	public static void main (String[] args){
		BackTrackerExecutor ex = new BackTrackerExecutor();
		//ex.addHeuristic(new Central());
		//ex.addHeuristic(new KeepTogether());
		//ex.addHeuristic(new Central());
		
		PegState base = new PegState(0, 0);
		base.log("Initiated");
		
		base.initB();
		
		//ex.verbose = true;
		//ex.slow = true;
		ex.setBase(base);
		
		ex.execute();
		
	}
	

}
