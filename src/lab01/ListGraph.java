package lab01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class ListGraph implements GraphInterface {
	List[] list = null;
	int V;
	int E;
	
	public ListGraph(String path, int v, int e) throws IOException{
		V = v;
		E = e;
		list = new List[v];
		for(int i = 0; i < V; i++){
			list[i] = new List();
		}
		File f = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = "";
		for(line = br.readLine(); line != null; line = br.readLine() ){
			String[] t = line.split(";");
			list[Integer.parseInt(t[0].trim())].add(Integer.parseInt(t[1].trim()), Integer.parseInt(t[2].trim()));
		}
		br.close();
	}
	
	@Override
	public void addNode() {
		V++;
		list = java.util.Arrays.copyOf(list, V);
		list[V-1] = new List();
		System.out.println("New node number: " + V);
	}

	@Override
	public void deleteNode(int node) {
		for(List l : list){
			l.delete(node);
		}
		list[node] = null;
	}

	@Override
	public void addEdge(int node1, int node2, int edge) {
		if(!areNeighbours(node1, node2)){
			E++;
			list[node1].add(node2, edge);	
			System.out.println("Edge added");
		}else{
			System.out.println("Edge already exists");
		}
		
	}

	@Override
	public void deleteEdge(int node1, int node2) {
		if(areNeighbours(node1, node2)){
			E--;
			list[node1].delete(node2);	
			System.out.println("Edge deleted");
		}else{
			System.out.println("Edge does not exist");
		}
			
	}

	@Override
	public int[] neighbourNodes(int node) {
		int[] neighbours = new int[list[node].size()];
		for(int i = 0; i < list[node].size(); i++){
			neighbours[i] = list[node].get(i).nodeID;
		}
		return neighbours;
	}

	@Override
	public int[] incidentEdges(int node) {
		int[] edges = new int[list[node].size()];
		for(int i = 0; i < list[node].size(); i++){
			edges[i] = list[node].get(i).edgeWeidht;
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
		return list[node1].findNode(node2) != null;
	}

	@Override
	public void printGraph() {
		for(int i = 1; i <= list.length; i++){
			for(int j = 0; j < list[i - 1].size(); j++){
				System.out.println(i + "; " + list[i].get(j).nodeID + "; " + list[i].get(j).edgeWeidht );
			}
		}

	}

	@Override
	public int getEdge(int node1, int node2) {
		if(areNeighbours(node1, node2)){
			return list[node1].findNode(node2).edgeWeidht;
		}
		return 0;
	}

}