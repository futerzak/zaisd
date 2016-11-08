package lab01;

public interface GraphInterface {

	void addNode();
	void deleteNode(int node);
	
	void addEdge(int node1, int node2, int edge);
	void deleteEdge(int node1, int node2);
	
	int[] neighbourNodes(int node);
	int[] incidentEdges(int node);
	
	int nodesNumber();
	int edgesNumber();
	
	boolean areNeighbours(int node1, int node2);
	int getEdge(int node1, int node2);
	
	void printGraph();
}