import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class BackTrackingExecutor<T extends State> {
	public  void output(String s){
		System.out.println(s);
	}
	
	protected T goal, base;
	private HashSet<String> record = new HashSet<String>();
	
	State sstate;
	
	int optimal = Integer.MAX_VALUE;
	Node<T> end = null;	
	
	int c = 0;
	int v = 0;
	int solutions = 0;
	
	private int desiredSolutions = Integer.MAX_VALUE;
	private int maxExamine = Integer.MAX_VALUE;
	private int maxDifference = Integer.MAX_VALUE;
	private int maxRelax = 0;
	private int relaxCount = 0;
	private Integer findCost;
	private Scanner s = new Scanner(System.in);
	public boolean forget, slow, verbose = false;
	private int interval = 1;
	
	protected void setRelax(int i){
		maxRelax = i;
	}
	
	protected void setFindCost(int i){
		findCost = i;
	}
	
	protected void setSolutionCount(int i){
		desiredSolutions = i;
	}
	
	protected void setMaxExamine(int i){
		maxExamine = i;
	}
	
	protected void setMaxDifference(int i){
		maxDifference = i;
	}
	
	protected void setBase(T s){
		this.base = s;
	}
	protected void setGoal(T s){
		this.goal = s;
	}
	
	public void execute(){
		if (base == null){
			output("ERROR - NO STATES DEFINED OR UNREACHABLE STATES");
			return;
		//}else if (base.difference(goal) < 0){
			//output("ERROR - UNREACHABLE STATE");
			//return;
		}else{
			output("** EXECUTION STARTED **.\n\n");
		}
		if (base != null)output(base.toString());
		//if (goal != null)output(goal.toString());
		long l = System.currentTimeMillis();
		goal = null;
		
		sstate = base;
		
		executeBody();
		
		output(goal.toString());
		output("");
		output((end != null) ? "** COMPLETE **" : "** INCOMPLETE **");
		output("Executor took "+(System.currentTimeMillis() - l)/1000f + " seconds.");
		output("Solutions          |("+solutions + "/"+((desiredSolutions < Integer.MAX_VALUE) ? desiredSolutions : "ALL") + ")");
		output("Maximum Nodes      |"+((maxExamine < Integer.MAX_VALUE) ? maxExamine : "MAX"));
		output("Maximum Difference |"+((maxDifference < Integer.MAX_VALUE) ? maxDifference : "MAX"));
		output("Maximum Relax      |"+((maxRelax > 0) ? (maxDifference + " times"): "N/A"));
		output("Total examined:    |"+c);
		output("Total skipped:     |"+v);

		if (end != null){
			output("End cost:          |"+end.data.totalCost);
			output("\nHISTORY");
			ArrayList<String> history = new ArrayList<String>();
			while (end != null){
				history.add(end.data.history);
				end = end.parent;
			}
			for (int i = history.size()-1; i >= 0; i--){
				output(history.get(i));
			}
		}else{	
			output("No solutions found.");
		}
	}
	

	public void verbose(int i) {
		verbose = true;
		interval = i;
	}
	
	public void slow() {
		slow = true;
	}
	
	private boolean executeBody(){
			
			if (solutions > 0)return false;
			
			record.add(sstate.code());
		
			c++;
			if(verbose){
				if (c%interval == 0){
					System.out.println(
							"\nExamined:" + c +
							"\nNode depth: " + sstate.depth + 
							"\nKnown unique states: " + record.size() + 
							"\nSkipped states: " + v);
					System.out.println(sstate.toString());
					if(slow){s.nextLine();		
					
					}
				}
			}
			
			
			if (sstate.isWinning()){
				solutions++;	
				goal = (T) sstate;
				return true;

			}
			
			State original = sstate;
			for (State s : sstate.expand()){
				if (record.contains(s.code())){
					v++;
					continue;
				}
				sstate = s;
				boolean b = executeBody();
				sstate = original;
			}
			
			return false;
		}
	

	
	public void forgetful() {
		forget = true;
	}

}
