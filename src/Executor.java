import java.util.HashMap;
import java.util.Map;

public abstract class Executor {
	public abstract Node<State> selectNode();
	public abstract void output(String s);
	public abstract void handleChild(Node<State> n);
	public abstract String introduce();
	public abstract void nodeAdd(Node<State> n);
	public abstract boolean nodesEmpty();
	public abstract void reset();
	
	State goal, base;
	Map<State, Integer> record = new HashMap<State, Integer>();
	
	private int desiredSolutions = Integer.MAX_VALUE;
	private int maxAttempts = Integer.MAX_VALUE;
	private int maxDifference = Integer.MAX_VALUE;
	private int maxRelax = 0;
	int relaxCount = 0;
	
	protected void setRelax(int i){
		maxRelax = i;
	}
	
	protected void setSolutionCount(int i){
		desiredSolutions = i;
	}
	
	protected void setMaxAttempts(int i){
		maxAttempts = i;
	}
	
	protected void setMaxDIfference(int i){
		maxDifference = i;
	}
	
	protected void setBase(State s){
		this.base = s;
	}
	protected void setGoal(State s){
		this.goal = s;
	}
	
	public void execute(){
		if (goal == null || base == null){
			output("ERROR - NO STATES DEFINED OR UNREACHABLE STATES");
			return;
		}else if (base.difference(goal) < 0){
			output("ERROR - UNREACHABLE STATE");
			return;
		}else{
			output("** EXECUTION STARTED **.\n" + introduce() + "\n");
		}
		output(base.toString());
		executeBody();
	}
	
	public void executeBody(){

		Node<State> root = new Node<State>(base, null);
		nodeAdd(root);
		int optimal = Integer.MAX_VALUE;
		Node<State> end = null;	
		
		int c = 0;
		int v = 0;
		int solutions = 0;
		
		while (!nodesEmpty() && c < maxAttempts){
			
			Node<State> n = selectNode();
			
			if (n.data.equals(goal)){
				
				solutions++;
				if (n.data.totalCost < optimal){
					optimal = n.data.totalCost;
					end = n;
				}
				
				if (solutions >= desiredSolutions){
					break;
				}
			}
			
			for (State s : n.data.expand()){
				if (record.containsKey(n.data)){
					v++;
					continue;
				}
				if (n.data.difference(goal) > maxDifference){
					v++;
					continue;
				}
				Node<State> child = new Node<State>(s, n);
				handleChild(child);
			}
			
			record.put(n.data, n.data.totalCost);
			
			c++;
		}
		
		if ((end == null) && relaxCount < maxRelax){
			relaxCount++;
			output("No solution found. Relaxing to " + (maxDifference + 1) +"...");
			record = new HashMap<State, Integer>();
			reset();
			setMaxDIfference(maxDifference + 1);
			executeBody();
			return;
		}
		
		output(goal.toString());
		output((end != null) ? "** COMPLETE **" : "** INCOMPLETE **");
		output("Desired Solutions  |"+((desiredSolutions < Integer.MAX_VALUE) ? desiredSolutions : "MAX"));
		output("Maximum Nodes      |"+((maxAttempts < Integer.MAX_VALUE) ? maxAttempts : "MAX"));
		output("Maximum Difference |"+((maxDifference < Integer.MAX_VALUE) ? maxDifference : "MAX"));
		output("Maximum Relax      |"+((maxRelax > 0) ? (maxDifference + " times"): "N/A"));
		output("Total examined:    |"+c);
		output("Total skipped:     |"+v);
		
		if (end != null){
			output("End cost:          |"+end.data.totalCost);
			output("Total solutions:   |"+solutions);
			while (end != null){
				output(end.data.history);
				end = end.parent;
			}
		}else{	
			output("No solutions found.");
		}
	}
}
