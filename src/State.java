import java.util.ArrayList;

public abstract class State {
	public abstract ArrayList<State> expand();
	
	String history = "";
	int totalCost = 0;
	int depth;
	int distance;
}
