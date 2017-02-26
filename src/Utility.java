
public class Utility {
	public static BridgeState invertBridge(BridgeState state){
		BridgeState out = new BridgeState(0, 0);
		for (Integer i : state.entitiesL)out.entitiesR.add(i);
		for (Integer i : state.entitiesR)out.entitiesL.add(i);
		return out;
	}
}
