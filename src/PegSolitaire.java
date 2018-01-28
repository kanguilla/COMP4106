
public class PegSolitaire {
	
	public static void main (String[] args){
		AstarExecutor<PegState> ex = new AstarExecutor<PegState>();
		//ex.addHeuristic(new Central());
		//ex.addHeuristic(new KeepTogether());
		ex.addHeuristic(new Central());
		
		PegState base = new PegState(0, 0);
		base.log("Initiated");
		
		base.init();
		
		ex.setMaxExamine(200);
		ex.setBase(base);
		ex.slow = false;
		ex.execute();
		
	}
	

}
