
public class Test {

	public static void main(String[] args) {
		BridgeState base = new BridgeState(0, 0);
		base.addEntity(1, 1);
		base.addEntity(3, 1);
		base.addEntity(5, 1);
		base.addEntity(8, 1);
		base.addEntity(13, 1);
		
		BridgeState goal = Utility.invertBridge(base);
		System.out.println(goal.toString());
	}

}
