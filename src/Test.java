
public class Test {

	public static void main(String[] args) {
		PegState state = new PegState(0, 0);	
		state.initC();
		
		PegState state2 = new PegState(0,0);
		state2.initC();
		
		System.out.println(state.toString());
		
		System.out.println(state.equals(state2));
		
		
		System.out.println(state.code + " :: " + state2.code);
	}

}
 