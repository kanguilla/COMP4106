import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class DFST<T extends State> {
	public void output(String s) {
		System.out.println(s);
	}

	
	public String introduce() {
		return "Depth-First Search, Threaded";
	}

	boolean done = false;
	int threads = 0;
	
	protected T base;
	private HashSet<String> record = new HashSet<String>();
	
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
	Node<T> end;
	
	int c = 0;
	int v = 0;
	int solutions = 0;
	
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
		
		Node<T> n = new Node<T>(base, null);
		
		long l = System.currentTimeMillis();


		
		executeBody(n);
		

		output("");
		output((end != null) ? "** COMPLETE **" : "** INCOMPLETE **");
		output("Executor took " + (System.currentTimeMillis() - l) / 1000f + " seconds.");
		output("Solutions          |(" + solutions + "/"
				+ ((desiredSolutions < Integer.MAX_VALUE) ? desiredSolutions : "ALL") + ")");
		output("Maximum Nodes      |" + ((maxExamine < Integer.MAX_VALUE) ? maxExamine : "MAX"));
		output("Maximum Difference |" + ((maxDifference < Integer.MAX_VALUE) ? maxDifference : "MAX"));
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

	private boolean executeBody(Node<T> n) {
		
		if (solutions > 0)return false;
		
		T data = n.data;
		ArrayList<State> moves = data.expand();
		c++;

		if (data.isWinning()) {
			solutions++;
			if (solutions >= desiredSolutions) {
				end = n;
			}
			return true;
		}

		for (State s : moves) {

			if (record.contains(data.code())) {
				v++;
				continue;
			}

			Node<T> child = new Node<T>((T) s, n);
			
			if(threads < 3) {
				threads++;
				new Thread(new Runnable() {
					@Override
					public void run() {
						executeBody(child);
						threads--;
					}
				}, "thread #" + threads).start();
			}else {
				executeBody(child);
			}
			
			
		}
		record.add(data.code());

		if (verbose) {
			if (c % interval == 0) {
				System.out.println("\n------------------------" + "\nExamined:" + c + "\nThreads: " + threads
						+ "\nNode depth: " + data.depth + "\nKnown unique states: " + record.size()
						+ "\nSkipped states: " + v + "\nCurrent possible moves: " + moves.size());
				System.out.println(data.toString());
				if (slow) {
					s.nextLine();

				}
			}
		}
		return false;
		
	}

}
