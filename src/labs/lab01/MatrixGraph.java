package labs.lab01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class MatrixGraph implements GraphInterface {
	int[][] matrix;
	int V;
	int E;
	
	public MatrixGraph(String path, int v, int e) throws IOException{
		V = v;
		E = e;
		matrix = new int[v][v];
		File f = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = "";
		for(line = br.readLine(); line != null; line = br.readLine() ){
			String[] t = line.split(";");
			matrix[Integer.parseInt(t[0].trim())][Integer.parseInt(t[1].trim())] = Integer.parseInt(t[2].trim());
		}
		br.close();
	}

	@Override
	public void addNode() {
		V++;
		matrix = java.util.Arrays.copyOf(matrix, V);
		for(int i = 0; i < V - 1; i++){
			matrix[i] = java.util.Arrays.copyOf(matrix[i], V);
		}
		matrix[V-1] = new int[V];
		System.out.println("New node number: " + V);
	}

	@Override
	public void deleteNode(int node) {
		for(int i = 0; i < V; i++){
			matrix[node][i] = 0;
			matrix[i][node] = 0;
		}
	}

	@Override
	public void addEdge(int node1, int node2, int edge) {
		if(!areNeighbours(node1, node2)){
			matrix[node1][node2] = edge;
			E++;
			System.out.println("Edge added.");
		}else{
			System.out.println("Edge already exists.");
		}
		
	}

	@Override
	public void deleteEdge(int node1, int node2) {
		if(areNeighbours(node1, node2)){
			E--;
			matrix[node1][node2] = 0;
			System.out.println("Edge deleted");
		}else{
			System.out.println("Edge does not exist");
		}
		
	}

	@Override
	public int[] neighbourNodes(int node) {
		int size = 0;
		int[] neighbours = new int[size];
		for(int i = 0; i < V; i++){
			if(matrix[node - 1][i] != 0){
				size++;
				neighbours = java.util.Arrays.copyOf(neighbours, size);
				neighbours[size] = i + 1;
			}
		}
		return neighbours;
	}

	@Override
	public int[] incidentEdges(int node) {
		int size = 0;
		int[] edges = new int[size]; 
		for(int i = 0; i < V; i++){
			if(matrix[node][i] != 0){
				size++;
				edges = java.util.Arrays.copyOf(edges, size);
				edges[size - 1] = matrix[node][i];
			}
		}
		return edges;
	}

	@Override
	public int nodesNumber() {
		return V;
	}

	@Override
	public int edgesNumber() {
		return E;
	}

	@Override
	public boolean areNeighbours(int node1, int node2) {
		return matrix[node1][node2] != 0;
	}

	@Override
	public void printGraph() {
		for(int i = 0; i < V; i++){
			for(int j = 0; j < V; j++){
				if(areNeighbours(i, j)){
					System.out.println(i + "; " + j + "; " + matrix[i][j]);
				}
			}
		}
	}

	@Override
	public int getEdge(int node1, int node2) {
		return matrix[node1][node2];
	}

}