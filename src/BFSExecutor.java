import java.util.ArrayDeque;

public class BFSExecutor extends Executor {
	
	ArrayDeque<Node<State>> nodeList = new ArrayDeque<Node<State>>();
	
	@Override
	public Node<State> selectNode() {
		return nodeList.pop();
	}

	@Override
	public void output(String s) {
		System.out.println(s);
	}
	
	@Override
	public void handleChild(Node<State> child){
		nodeList.addLast(child);
	}

	@Override
	public String introduce() {
		return "Breadth-First Search";
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
