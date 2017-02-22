
public class BFSExecutor extends Executor {
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
}
