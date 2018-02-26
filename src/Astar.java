import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Astar<T extends State> extends Executor<T> {
	
	private ArrayList<Heuristic<T>> heuristics = new ArrayList<Heuristic<T>>();
	
	private Comparator<Node<T>> comparator = new Comparator<Node<T>>(){
		@Override
		public int compare(Node<T> s1, Node<T> s2) {
			if (heuristics.isEmpty()){
				return s1.data.totalCost - s2.data.totalCost;
			}
			
			double avg1 = 0;
			for (Heuristic<T> h : heuristics){
				avg1 = avg1 + h.eval(s1.data, s2.data, goal);
			}
			avg1 = avg1/heuristics.size();
			
			double avg2 = 0;
			for (Heuristic<T> h : heuristics){
				avg2 = avg2 + h.eval(s2.data, s1.data, goal);
			}
			avg2 = avg2/heuristics.size();
			
			return (int) (avg1 - avg2);
		}
	};
	
	private PriorityQueue<Node<T>> nodeList = new PriorityQueue<Node<T>>(1, comparator);
	
	public void addHeuristic(Heuristic<T> h){
		heuristics.add(h);
	}
	
	public void clearHeuristics(){
		heuristics.clear();
	}
	
	@Override
	public Node<T> selectNode() {
		return nodeList.poll();
	}

	@Override
	public void output(String s) {
		System.out.println(s);
	}
	
	@Override
	public void handleChild(Node<T> child){
		nodeList.add(child);
	}

	@Override
	public String introduce() {
		String s = "A-star Search\n";
		if (heuristics.isEmpty()) s += " - No heuristics, least cost";
		for (Heuristic<T> h : heuristics){
			s += (" - with " + h.toString() + "\n");
		}
		return s;
	}

	@Override
	public void nodeAdd(Node<T> n) {
		nodeList.add(n);
	}

	@Override
	public boolean nodesEmpty() {
		return nodeList.isEmpty();
	}

	@Override
	public void reset() {
		nodeList = new PriorityQueue<Node<T>>(1, comparator);
	}
	
	@Override
	public void clearNodes() {
		nodeList.clear();
	}

	@Override
	public int numNodes() {
		return nodeList.size();
	}
}

