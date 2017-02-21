import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
	static Map<State, Integer> record = new HashMap<State, Integer>();
	
	public static void main (String[] args){
		
		//0 = left, 1 = right
		
		State end = new State();
		end.addEntity(1, 1);
		end.addEntity(3, 1);
		end.addEntity(5, 1);
		end.addEntity(8, 1);
		end.addEntity(13, 1);
		
		State base = new State();
		base.addEntity(1, 0);
		base.addEntity(3, 0);
		base.addEntity(5, 0);
		base.addEntity(8, 0);
		base.addEntity(13, 0);
		Node<State> root = new Node<State>(base, null);
		
		ArrayDeque<Node<State>> nodeList = new ArrayDeque<Node<State>>();
		nodeList.add(root);
		
		
		System.out.println(base.toString());
		
		int c = 0;
		
		while (true){
			c++;
			Node<State> n = nodeList.pop();

			if (visited(n.data)){
				continue;
			}
			
			if (n.data.equals(end)){
				System.out.println(n.data.toString());
				System.out.println("DONE " + n.data.totalCost + " (" + c + ")");
				break;
			}
			
			for (State s : n.data.expand()){
				nodeList.add(new Node<State>(s, n));
			}
			
	
		}
		
	}
	
	
	public static boolean visited(State s){
		if (record.containsKey(s)){
			return true;
		}
		return false;
	}

}