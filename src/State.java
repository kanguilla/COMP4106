import java.util.ArrayList;

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
	
	public int fingerprint(){
		return
	}
	
	@Override
	public boolean equals(Object other) {
		
		
	}
}
