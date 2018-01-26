
public class Test {

	public static void main(String[] args) {

		int[] xmoves = {-1,0,1,0};
		int[] ymoves = {0,1,0,-1};
		
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				
				System.out.println("(" + x + "," + y + ")");
			
				
				for (int i = 0; i < xmoves.length; i++) {
	
					int xtarget = x + (2 * xmoves[i]);
					int ytarget = y + (2 * ymoves[i]);
					
					if (xtarget < 0 || xtarget > 6)continue;
					if (ytarget < 0 || ytarget > 6)continue;
					
					int xhopped = x + xmoves[i];
					int yhopped = y + ymoves[i];
					
					
					System.out.println("  (" + xtarget + "," + ytarget + ") hopping (" + xhopped + "," + yhopped + ")");

				}
			
			}
		}
	}

}
