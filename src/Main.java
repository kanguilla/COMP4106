import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
	Map<State, Integer> record = new HashMap<State, Integer>();
	
	public static void main (String[] args){
		
		
		
		State base = new State();
		base.addEntity(1, 0);
		base.addEntity(3, 0);
		base.addEntity(5, 0);
		base.addEntity(8, 0);
		base.addEntity(13, 0);
		Node<State> root = new Node<State>(base);
		
		
		
	}
	
	public void setChildren(Node<State> node){
		
		for (State s : node.getData().findChildren()){
			if (record.containsKey(s)){
				if (record.get(s) > s.totalCost){
					//replace
					
					
					
				}else{
					//do nothing
				}
			
			}else{
				node.addChild(new Node<State>(s));
				record.put(node.data, node.data.totalCost);
			}
			
		}
	}

}
