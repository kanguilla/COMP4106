
public class Test {

	public static void main(String[] args) {
		TileState base = new TileState(0, 0);
		base.addTile("1", 0, 0);
		base.addTile("2", 0, 1);
		base.addTile("4", 0, 2);
		base.addTile("3", 1, 0);
		base.addTile(" ", 1, 1);
		base.addTile("6", 1, 2);
		base.addTile("7", 2, 0);
		base.addTile("8", 2, 1);
		base.addTile("9", 2, 2);
		
		TileState goal = new TileState(0, 0);
		goal.addTile("1", 0, 0);
		goal.addTile("2", 0, 1);
		goal.addTile("3", 0, 2);
		goal.addTile("4", 1, 0);
		goal.addTile(" ", 1, 1);
		goal.addTile("6", 1, 2);
		goal.addTile("9", 2, 0);
		goal.addTile("8", 2, 1);
		goal.addTile("7", 2, 2);
		
		System.out.println(base.difference(goal));

	}

}
