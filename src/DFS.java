import java.util.ArrayDeque;
import java.util.Deque;

public class DFS<T extends State> extends Executor<T> {
	
	Deque<Node<T>> nodeList = new ArrayDeque<Node<T>>();
	
	@Override
	public Node<T> selectNode() {
		return nodeList.pop();
	}

	@Override
	public void output(String s) {
		System.out.println(s);
	}
	
	@Override
	public void handleChild(Node<T> child){
		nodeList.push(child);
	}

	@Override
	public String introduce() {
		return "Depth-First Search";
	}

	@Override
	public void nodeAdd(Node<T> n) {
		nodeList.push(n);
	}

	@Override
	public boolean nodesEmpty() {
		return nodeList.isEmpty();
	}

	@Override
	public void reset() {
		nodeList = new ArrayDeque<Node<T>>();
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
