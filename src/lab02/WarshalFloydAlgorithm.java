package lab02;

import java.util.LinkedList;
import lab01.GraphInterface;


public class WarshalFloydAlgorithm {
	int V;
	int[][] D;
	int[][] P;
	LinkedList<Integer> path = new LinkedList<>();
	
	public int[][] warshalFloyd(GraphInterface g){
		V = g.nodesNumber();
		D = new int[V][V];
		P = new int[V][V];
		 
		for(int i = 0; i < V; i++){
			for(int j = 0; j < V; j++){
				P[i][j] = -1;
				int edge = g.getEdge(i, j);
				if(i == j){
					D[i][j] = 0;
				}else if(edge == 0){
					D[i][j] = 9999;
				}else{
					D[i][j] = edge;
					P[i][j] = j;
				}
			}
		}		
		for(int i = 0; i < V; i++){
			for(int j = 0; j < V; j++){
				for(int k = 0; k < V; k++){
					if(D[j][i] + D[i][k] < D[j][k]){
						D[j][k] = D[j][i] + D[i][k];
						P[j][k] = P[j][i];
					}
				}
			}
		}		
		return D;
	}
	
	public LinkedList<Integer> getPath(int source, int target){
		if(P[source][target] != -1){
			path.add(source);
			while(source != target){
				source = P[source][target];
				path.add(source);
			}
		}
		return path;
	}
}
