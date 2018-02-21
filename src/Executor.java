import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public abstract class Executor<T extends State> {
	public abstract Node<T> selectNode();
	public abstract void output(String s);
	public abstract void handleChild(Node<T> n);
	public abstract String introduce();
	public abstract void nodeAdd(Node<T> n);
	public abstract boolean nodesEmpty();
	public abstract void reset();
	public abstract void clearNodes();
	public abstract int numNodes();
	
	protected T goal, base;
	private HashSet<String> record = new HashSet<String>();
	
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
			output("** EXECUTION STARTED **.\n" + introduce() + "\n");
		}
		if (base != null)output(base.toString());
		//if (goal != null)output(goal.toString());
		
		executeBody();
	}
	

	public void verbose(int i) {
		verbose = true;
		interval = i;
	}
	
	public void slow() {
		slow = true;
	}
	
	private void executeBody(){

		long l = System.currentTimeMillis();
		Node<T> root = new Node<T>(base, null);
		nodeAdd(root);
		int optimal = Integer.MAX_VALUE;
		Node<T> end = null;	
		
		int c = 0;
		int v = 0;
		int solutions = 0;
		
		while (!nodesEmpty() && c < maxExamine){
			Node<T> n = selectNode();
			T data = n.data;
			c++;
			if(verbose){
				if (c%interval == 0){
					System.out.println(
							"\nExamined:" + c +
							"\nNodes in list: " + numNodes() + 
							"\nNode depth: " + data.depth + 
							"\nKnown unique states: " + record.size() + 
							"\nSkipped states: " + v);
					System.out.println(data.toString());
					if(slow){s.nextLine();		
					
					}
				}
			}
			
			if (data.isWinning()){
				
				solutions++;	
				goal = data;
				if (solutions >= desiredSolutions){
					end = n;
					break;
				}
				
				if (data.totalCost < optimal){
					optimal = data.totalCost;
					end = n;
				}
				if (findCost != null && optimal == findCost){
					break;
				}
				
			}
			for (State s : data.expand()){
				if (!forget && record.contains(data.code())){
					v++;
					continue;
				}else{
					Node<T> child = new Node<T>((T) s, n);
					handleChild(child);
				}
			}
			record.add(data.code());
		}
	
		
		output(goal.toString());
		output("");
		output((end != null) ? "** COMPLETE **" : "** INCOMPLETE **");
		output("Desired Solutions  |"+((desiredSolutions < Integer.MAX_VALUE) ? desiredSolutions : "MAX"));
		output("Maximum Nodes      |"+((maxExamine < Integer.MAX_VALUE) ? maxExamine : "MAX"));
		output("Maximum Difference |"+((maxDifference < Integer.MAX_VALUE) ? maxDifference : "MAX"));
		output("Maximum Relax      |"+((maxRelax > 0) ? (maxDifference + " times"): "N/A"));
		output("Total examined:    |"+c);
		output("Total skipped:     |"+v);
		
		if (end != null){
			output("End cost:          |"+end.data.totalCost);
			output("Total solutions:   |"+solutions);
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
		System.out.println("Execution took "+ (System.currentTimeMillis() - l) +" ms");
	}
	
	public void forgetful() {
		forget = true;
	}

}
