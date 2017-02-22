import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AstarExecutor extends Executor {
	
	PriorityQueue<Node<State>> nodeList = new PriorityQueue<Node<State>>(1, new Comparator<Node<State>>(){
		@Override
		public int compare(Node<State> o1, Node<State> o2) {
			return o1.data.totalCost - o2.data.totalCost;
		}
	});
	
	
	@Override
	public Node<State> selectNode(ArrayList<Heuristic> h) {
		
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
		return "A-star Search";
	}

	@Override
	public void nodeAdd(Node<State> n) {
		nodeList.add(n);
	}

	@Override
	public boolean nodesEmpty() {
		return nodeList.isEmpty();
	}
}
