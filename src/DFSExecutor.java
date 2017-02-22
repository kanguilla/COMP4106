
public class DFSExecutor extends Executor {
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
		nodeList.addFirst(child);
	}

	@Override
	public String introduce() {
		return "Depth-First Search";
	}
}
