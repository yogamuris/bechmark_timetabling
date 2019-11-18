package okh;

public class Graph {
	int[][] graph;
	
	public Graph(int[][] conflict_matrix) {
		for(int i = 0; i < conflict_matrix.length; i++) {
			for(int j = 0; j < conflict_matrix.length; j++) {
				if(graph[i][j] > 0)
					graph[i][j] = 1;
			}
		}
	}
	
	
	
	
}
