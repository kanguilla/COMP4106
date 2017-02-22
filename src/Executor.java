import java.util.HashMap;
import java.util.Map;

public abstract class Executor {
	public abstract Node<State> selectNode();
	public abstract void output(String s);
	public abstract void handleChild(Node<State> n);
	public abstract String introduce();
	public abstract void nodeAdd(Node<State> n);
	public abstract boolean nodesEmpty();
	
	State goal, base;
	Map<State, Integer> record = new HashMap<State, Integer>();
	
	protected void setBase(State s){
		this.base = s;
	}
	protected void setGoal(State s){
		this.goal = s;
	}
	
	public void execute(){
		
		if (goal == null || base == null){
			output("ERROR - NO STATES DEFINED");
			return;
		}else{
			output("** EXECUTION STARTED **.\n" + introduce() + "\n");
		}
		
		Node<State> root = new Node<State>(base, null);

		nodeAdd(root);
		
		int optimal = Integer.MAX_VALUE;
		Node<State> end = null;
		
		output(base.toString());
		
		int c = 0;
		int v = 0;
		int solutions = 0;
		
		while (!nodesEmpty()){

			Node<State> n = selectNode();
			
			c++;
			
			if (n.data.equals(goal)){
				
				solutions++;		
				if (n.data.totalCost < optimal){
					optimal = n.data.totalCost;
					end = n;
				}
			}
			
			for (State s : n.data.expand()){
				if (!record.containsKey(n.data)){
					Node<State> child = new Node<State>(s, n);
					handleChild(child);
				}else{
					v++;
				}
			}
			
			record.put(n.data, n.data.totalCost);
		}
		if (end != null){
			output(end.data.toString());
			output("** COMPLETE **");
			output("End cost:       |"+end.data.totalCost);
			output("Total examined: |"+c);
			output("Total skipped:  |"+v);
			output("Total solutions:|"+solutions);
			while (end != null){
				output(end.data.history);
				end = end.parent;
			}
		}else{
			output(goal.toString());
			output("** INCOMPLETE **");
			output("No solutions found.");
			output("Total examined: |"+c);
			output("Total skipped:  |"+v);
		}
	}
}
