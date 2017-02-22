public class Pair {
	int x;
	int y;
	
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object other) {
		return (this.x == ((Pair)other).x && this.y == ((Pair)other).y);
	}

	@Override
	public int hashCode() {
        int result = 17;
        result = 31 * result + x + y;
        return result;
    }
}
