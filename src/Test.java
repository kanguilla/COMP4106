import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		State s = new State(0, 0);
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(3);
		a.add(2);
		s.entitiesR = a;
		s.totalCost = 0;
		
		State s2 = new State(0, 0);
		ArrayList<Integer> a2 = new ArrayList<Integer>();
		a2.add(2);
		a2.add(3);
		s2.entitiesR = a2;
		s2.totalCost = 5;
		
		System.out.println(s.equals(s2));
	}

}
