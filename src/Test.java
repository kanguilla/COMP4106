
public class Test {

	public static void main(String[] args) {
		PegState state = new PegState(0, 0);	
		state.board = PegLayouts.test2;
		
		PegState state2 = new PegState(0,0);
		state2.board = PegLayouts.test1;
		
		System.out.println(state.toString());
		System.out.println(state2.toString());
		
		System.out.println(state.equals(state2));
		
		
		System.out.println(state.code() + "\n" + state2.code());
	}

}
 