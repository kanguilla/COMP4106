import java.util.ArrayList;
import java.util.Collections;
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
	public abstract void explain(T s);
	
	protected T goal, base;
	private HashSet<String> record = new HashSet<String>();
	
	private boolean detailed = false;
	private int desiredSolutions = Integer.MAX_VALUE;
	private int maxExamine = Integer.MAX_VALUE;
	private int startOver = Integer.MAX_VALUE;
	private int maxDifference = Integer.MAX_VALUE;
	private int maxDepth = Integer.MAX_VALUE;
	private int maxRelax = 0;
	private int relaxCount = 0;
	private Integer findCost;
	private Scanner s = new Scanner(System.in);
	public boolean forget, slow, verbose = false;
	private int interval = 1;
	
	
	protected void setStartOver(int i){
		startOver = i;
	}
	
	protected void setMaxDepth(int i){
		maxDepth = i;
	}
	
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
		Node<T> end = null;	
		
		int c = 0;
		int v = 0;
		int solutions = 0;

		
		while (!nodesEmpty()){
			
			if (c == startOver){
				System.out.println("RESETTING");
				reset();
				nodeAdd(new Node<T>(base, null));
				//record.clear();
				c = 0;
				solutions = 0;
			}
			
			Node<T> n = selectNode();
			
			T data = n.data;
			
			ArrayList<State> moves = data.expand();
			
			c++;
			
			
			if (data.isWinning()){
				
				solutions++;	
				if (solutions >= desiredSolutions){
					end = n;
					break;
				}
				
			}

			int u= 0;
			for (State s : moves){

				if (record.contains(data.code())){
					v++;
					continue;
				}
			
//				if (s.expand().size() == 0 && !s.isWinning()){
//					v++;
//					continue;
//				}
				Node<T> child = new Node<T>((T) s, n);
				handleChild(child);
				u++;
			}
			if(verbose){
				if (c%interval == 0){
					System.out.println(u + " children added");
				}
			}
			record.add(data.code());
			
			if(verbose){
				if (c%interval == 0){
					System.out.println(
							"\n------------------------" +
							"\nExamined:" + c +
							"\nNodes in list: " + numNodes() + 
							"\nNode depth: " + data.depth + 
							"\nKnown unique states: " + record.size() + 
							"\nSkipped states: " + v + 
							"\nCurrent possible moves: " + moves.size());
					System.out.println(data.toString());
					explain(data);
					if(slow){s.nextLine();		
					
					}
				}
			}
			
		}
	
		
		if (end != null) output(end.data.toString());
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
				if(detailed)history.add(end.data.toString());
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
	
	public void forgetful() {
		forget = true;
	}
	
	public void visualSolution() {
		detailed = true;
	}

}
