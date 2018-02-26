import java.util.ArrayDeque;

public class BFS<T extends State> extends Executor<T> {
	
	ArrayDeque<Node<T>> nodeList = new ArrayDeque<Node<T>>();
	
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
		nodeList.addLast(child);
	}

	@Override
	public String introduce() {
		return "Breadth-First Search";
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
