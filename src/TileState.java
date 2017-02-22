import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class TileState extends State{

	Map<Pair, String> nodeMap = new HashMap<Pair, String>();
	
	String nullRegex = " ";
	
	public TileState(int depth, int distance) {
		super(depth, distance);
	}

	public void addTile(String i, int x, int y){
		nodeMap.put(new Pair(x, y), i);
	}
	
	
	@Override
	public ArrayList<State> expand() {
		ArrayList<State> out = new ArrayList<State>();
		
		int d = 0;
		for (Entry<Pair, String> e : nodeMap.entrySet()){
			//local x/y
			int x = e.getKey().x;
			int y = e.getKey().y;
			
			//find blank spaces for rule #1
			if (e.getValue() == nullRegex){
				//find adjacent spaces
				for (int i = -1; i<=1; i++){
					for (int j = -1; j<=1; j++){
						if (i != 0 || j != 0){
							String neighbour = nodeMap.get(new Pair(x + i, y + j));
							if (neighbour != null && neighbour != nullRegex){
								//there is a node there
								//swap values
								
								TileState ns = new TileState(this.depth+1, d++);
								ns.nodeMap = new HashMap<Pair, String>(this.nodeMap);
								ns.nodeMap.put(new Pair(x, y), neighbour);
								ns.nodeMap.put(new Pair(x + i, y + j), null);
								ns.history = "SWAP - blank("+x+","+y+") with " + neighbour + "("+(x+i)+","+(y+j)+")";
								
								out.add(ns);
							}
						}
					}
				}
			}else{
				
				//find chess moves for rule #2
				for (int i = -2; i<=2; i++){
					for (int j = -2; j<=2; j++){
						if (!(i == 0 || j == 0 || (i+j) == 0 || (i == j))){
							String neighbour = nodeMap.get(new Pair(x + i, y + j));
							if (neighbour != null && neighbour != nullRegex){
								TileState ns = new TileState(this.depth+1, d++);
								ns.nodeMap = new HashMap<Pair, String>(this.nodeMap);
								ns.nodeMap.put(new Pair(x, y), neighbour);
								ns.nodeMap.put(new Pair(x + i, y + j), e.getValue());
								ns.history = "VAULT - "+e.getValue()+"("+x+","+y+") with " + neighbour + "("+(x+i)+","+(y+j)+")";
								
								out.add(ns);
							}
						}
					}
				}
			}
		}
		return out;
	}

	@Override
	public boolean equals(Object other) {
		return nodeMap.equals(((TileState) other).nodeMap);
	}

	@Override
	public int hashCode() {
		int result = 17;
        result = 31 * result + nodeMap.hashCode();
        result = 31 * result + totalCost;
        return result;
	}

	@Override
	public String toString() {
		int maxX = 0;
		int maxY = 0;
		for (Entry<Pair, String> e : nodeMap.entrySet()){
			maxX = Math.max(maxX, e.getKey().x + 1);
			maxY = Math.max(maxY, e.getKey().y + 1);
		}
		for(int i = -1; i<maxX+1;i++){
			
			for(int j = -1; j<maxY+1;j++){
				
				if (i == -1 || i == maxX){
					System.out.print("--");
				}else if (j == -1 || j == maxY){
					System.out.print("| ");
				}else{		
					String value = nodeMap.get(new Pair(i, j));
					System.out.print(((value == null) ? "  " : (value + " ")));
				}
			}
			if (i < maxX)System.out.println();
		}
		return "";
	}

}
