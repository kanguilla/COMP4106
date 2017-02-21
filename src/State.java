import java.util.ArrayList;
import java.util.Collections;

public class State{
	ArrayList<Integer> entitiesR = new ArrayList<Integer>();
	ArrayList<Integer> entitiesL = new ArrayList<Integer>();
	
	int reqMove = 1; //1 forward (move right), -1 backward (move right)
	int totalCost = 0;
	
	public ArrayList<State> expand(){
		ArrayList<State> out = new ArrayList<State>();
		
		if (reqMove == 1){
			for (int i=0; i < entitiesL.size()-1; i++){
				for (int j=i+1; j < entitiesL.size(); j++){
					//System.out.println(entitiesL.get(i) + " and " + entitiesL.get(j));
					
					State ns = new State();
					ns.entitiesL = new ArrayList<Integer>(this.entitiesL);
					ns.entitiesR = new ArrayList<Integer>(this.entitiesR);
					
					Integer i1 = entitiesL.get(i);
					Integer i2 = entitiesL.get(j);
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
				//System.out.println(entitiesR.get(i));

				State ns = new State();
				ns.entitiesL = new ArrayList<Integer>(this.entitiesL);
				ns.entitiesR = new ArrayList<Integer>(this.entitiesR);

				Integer i1 = entitiesR.get(i);
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
		return (side == 1) ? entitiesR.add(i) : entitiesL.add(i);
	}
	
	public boolean removeEntity(Integer i, int side){
		return (side == 1) ? entitiesR.remove(i) : entitiesL.remove(i);
	}
	
	
	@Override
	public boolean equals(Object other) {
		Collections.sort(entitiesR);
		Collections.sort(((State)other).entitiesR);
		
		return this.entitiesR.equals(((State)other).entitiesR);
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
		String out = "";
		for (Integer i : entitiesL) out += (", " + i);
		out += " || ";
		for (Integer i : entitiesR) out += (", " + i);
		return out;
	}
}
