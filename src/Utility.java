
public class Utility {
	public static BridgeState invertBridge(BridgeState state){
		BridgeState out = new BridgeState(0, 0);
		for (Integer i : state.entitiesL)out.entitiesR.add(i);
		for (Integer i : state.entitiesR)out.entitiesL.add(i);
		return out;
	}
	
	public static int fibonacci(int n){
		if (n <= 1)return 0;
		if (n == 2)return 1;
		
		int t = 0;
		int k = 1;
		
		while (n > 2){
			int c = k;
			k = t + k;
			t = c;
			n--;
		}
		
		return k;
	}
}
