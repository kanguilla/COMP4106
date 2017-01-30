import java.util.ArrayList;
import java.util.Collections;

public class State{
	ArrayList<Integer> entitiesR = new ArrayList<Integer>();
	public ArrayList<State> findChildren(){
		ArrayList<State> out = new ArrayList<State>();
		
		
		
		return out;
	}

	ArrayList<Integer> entitiesL = new ArrayList<Integer>();
	
	int totalCost = 0;
	
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
}
