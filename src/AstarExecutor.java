import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AstarExecutor extends Executor {
	
	private ArrayList<Heuristic> heuristics = new ArrayList<Heuristic>();
	
	private Comparator<Node<State>> comparator = new Comparator<Node<State>>(){
		@Override
		public int compare(Node<State> s1, Node<State> s2) {
			if (heuristics.isEmpty()){
				return s1.data.totalCost - s2.data.totalCost;
			}
			
			double avg1 = 0;
			for (Heuristic h : heuristics){
				avg1 = avg1 + h.eval(s1.data, s2.data, goal);
			}
			avg1 = avg1/heuristics.size();
			
			double avg2 = 0;
			for (Heuristic h : heuristics){
				avg2 = avg2 + h.eval(s2.data, s1.data, goal);
			}
			avg2 = avg2/heuristics.size();
			
			return (int) (avg1 - avg2);
		}
	};
	
	private PriorityQueue<Node<State>> nodeList = new PriorityQueue<Node<State>>(1, comparator);
	
	public void addHeuristic(Heuristic h){
		heuristics.add(h);
	}
	
	public void clearHeuristics(){
		heuristics.clear();
	}
	
	@Override
	public Node<State> selectNode() {
		
		return nodeList.poll();
	}

	@Override
	public void output(String s) {
		System.out.println(s);
	}
	
	@Override
	public void handleChild(Node<State> child){
		nodeList.add(child);
	}

	@Override
	public String introduce() {
		String s = "A-star Search\n";
		if (heuristics.isEmpty()) s += " - No heuristics, least cost";
		for (Heuristic h : heuristics){
			s += (" - with " + h.toString() + "\n");
		}
		return s;
	}

	@Override
	public void nodeAdd(Node<State> n) {
		nodeList.add(n);
	}

	@Override
	public boolean nodesEmpty() {
		return nodeList.isEmpty();
	}

	@Override
	public void reset() {
		nodeList = new PriorityQueue<Node<State>>(1, comparator);
	}
}
