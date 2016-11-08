package labs.lab03;

import labs.lab01.GraphInterface;

public class FordFulkerson {

	int[][] c;
	int[][] f;
	
	public int resolve(GraphInterface graph, int s, int t) {
		
		int V = graph.nodesNumber();
		c = new int[V][V];
		f = new int[V][V];
		
		for(int i = 0; i < V; i++){
			for(int j = 0; j < V; j++){
				f[i][j] = 0;
			}
		}
		
		for(int i=0; i < V; i++){ 
			for(int j=0; j < V; j++) {
				graph.areNeighbours(i, j);
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return 9351;
		
	}
}
