import java.util.Scanner;

public class BasicInput {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		while (true){
			System.out.println("Run a test:");
			String p = s.nextLine();
			if (s.equals("q"))break;
			
			Executor ex;
			String[] comp = p.split(",");
			
			switch(comp[0]){
			case "ASTAR":
				ex = new AstarExecutor();
				break;
			case "DFS":
				ex = new DFSExecutor();
				break;
			case "BFS":
				ex = new BFSExecutor();
				break;
			}
			
		
		}

		s.close();
	}
}