import java.util.ArrayList;
import java.util.Collections;

public class RiverState extends State{
	ArrayList<Integer> entitiesR = new ArrayList<Integer>();
	ArrayList<Integer> entitiesL = new ArrayList<Integer>();
	
	int reqMove = 1; //1 forward (move right), -1 backward (move right)
	
	public RiverState(int depth, int distance){
		this.depth = depth;
		this.distance = distance;
	}
	
	public ArrayList<State> expand(){
		ArrayList<State> out = new ArrayList<State>();
		
		int d = 0;
		
		
		if (reqMove == 1){
			for (int i=0; i < entitiesL.size()-1; i++){
				for (int j=i+1; j < entitiesL.size(); j++){
					
					RiverState ns = new RiverState(this.depth+1, d++);
					ns.entitiesL = new ArrayList<Integer>(this.entitiesL);
					ns.entitiesR = new ArrayList<Integer>(this.entitiesR);
					
					Integer i1 = entitiesL.get(i);
					Integer i2 = entitiesL.get(j);
					ns.history = ("-> (" + i1 + "," + i2 + ")");
					ns.entitiesL.remove(i1);
					ns.entitiesL.remove(i2);
					ns.entitiesR.add(i1);
					ns.entitiesR.add(i2);
					ns.totalCost = this.totalCost + Math.max(i1, i2);
					ns.reqMove = -1;
					
					out.add(ns);
				}
			}
		}
		
		if (reqMove == -1){

			for (int i=0; i < entitiesR.size(); i++){

				RiverState ns = new RiverState(this.depth+1, d++);
				ns.entitiesL = new ArrayList<Integer>(this.entitiesL);
				ns.entitiesR = new ArrayList<Integer>(this.entitiesR);

				Integer i1 = entitiesR.get(i);
				ns.history = ("<- (" + i1 + ")");
				ns.entitiesR.remove(i1);
				ns.entitiesL.add(i1);
				ns.totalCost = this.totalCost + i1;
				ns.reqMove = 1;

				out.add(ns);
			}
		}
		
		
		return out;
	}

	public boolean addEntity(Integer i, int side){
		return (side == 0) ? entitiesR.add(i) : entitiesL.add(i);
	}
	
	public boolean removeEntity(Integer i, int side){
		return (side == 0) ? entitiesR.remove(i) : entitiesL.remove(i);
	}
	
	
	@Override
	public boolean equals(Object other) {
		Collections.sort(entitiesR);
		Collections.sort(((RiverState)other).entitiesR);
		
		return this.entitiesR.equals(((RiverState)other).entitiesR);
	}
	@Override
	public int hashCode() {
        int result = 17;
        result = 31 * result + entitiesR.hashCode();
        result = 31 * result + entitiesL.hashCode();
        result = 31 * result + entitiesL.hashCode();
        result = 31 * result + reqMove + totalCost;
        return result;
    }
	
	@Override
	public String toString(){
		String out = "(";
		for (int i = 0; i < entitiesL.size(); i++) out += (((i>0)?", ":"")+ entitiesL.get(i));
		out += ") - (";
		for (int i = 0; i < entitiesR.size(); i++) out += (((i>0)?", ":"") + entitiesR.get(i));
		return out + ")";
	}
}
