
public class Test {

	public static void main(String[] args) {
		
		PegState state = new PegState(0, 0);	
		state.setBoard(Layouts.triangle);
		
		System.out.println(state.toString());
		
	}

}
 