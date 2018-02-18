import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class BackTrackerExecutor{

	public void output(String s) {
	}

	public String introduce() {
		return null;
	}

	public boolean nodesEmpty() {
		return false;
	}
	public void reset() {
	}
	public void clearNodes() {
	}
	
	protected State end, base;
	private Set<State> record = new HashSet<State>();
	
	private Scanner s = new Scanner(System.in);
	
	
	public boolean slow, verbose = false;
	private boolean done = false;
	private int c = 0;
	
	protected void setBase(State s){
		this.base = s;
	}

	
	public void execute(){
		if (base == null){
			output("ERROR - NO STATES DEFINED OR UNREACHABLE STATES");
			return;
		}
		
		output("** EXECUTION STARTED **.\n" + introduce() + "\n");
		

		executeBody(base, 0);
		
		
		output("");
		output((end != null) ? "** COMPLETE **" : "** INCOMPLETE **");
		output("Total examined:    |"+c);
		
		if (end != null){
			output("End cost:          |"+end.totalCost);
			output("\nHISTORY");
		}else{	
			output("No solutions found.");
		}
		
	}
	
	private void executeBody(State game, int depth){
		
		if(done) {
			return;
		}
		System.out.println(depth);
		if(slow){
			s.nextLine();	
		}
		if(verbose){
			System.out.println(game.toString());	
		}
		
		ArrayList<State> moves = game.expand();
		if (moves.size() == 0){
			System.out.println("terminated state");
		}
		
		for (State s : moves){
			
			if(record.contains(s)){
				System.out.println("found dup.");
				continue;
			}
			
			record.add(s);
			if(s.isWinning()) {
				done = true;
				end = s;
				c = depth;
			}else{
				System.out.println(s.toString());
			}
				
			executeBody(s, depth + 1);
				
		}
	}
}
 