package labs.lab03;

import labs.lab01.GraphInterface;
import labs.lab01.GraphfInterface;

import java.util.LinkedList;
import java.util.Queue;

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


	private int[] parent;
	private Queue<Integer> queue;
	private int numberOfVertices;
	private boolean[] visited;

	public FordFulkerson(int numberOfVertices)
	{
		this.numberOfVertices = numberOfVertices;
		this.queue = new LinkedList<Integer>();
		parent = new int[numberOfVertices + 1];
		visited = new boolean[numberOfVertices + 1];
	}

	public boolean bfs(int source, int goal, int graph[][])
	{
		boolean pathFound = false;
		int destination, element;

		for(int vertex = 1; vertex <= numberOfVertices; vertex++)
		{
			parent[vertex] = -1;
			visited[vertex] = false;
		}

		queue.add(source);
		parent[source] = -1;
		visited[source] = true;

		while (!queue.isEmpty())
		{
			element = queue.remove();
			destination = 1;

			while (destination <= numberOfVertices)
			{
				if (graph[element][destination] > 0 &&  !visited[destination])
				{
					parent[destination] = element;
					queue.add(destination);
					visited[destination] = true;
				}
				destination++;
			}
		}
		if(visited[goal])
		{
			pathFound = true;
		}
		return pathFound;
	}

	public int fordFulkerson(GraphfInterface graph, int source, int destination)
	{
		int u, v;
		int maxFlow = 0;
		int pathFlow;

		int[][] residualGraph = new int[numberOfVertices + 1][numberOfVertices + 1];
		for (int sourceVertex = 1; sourceVertex <= numberOfVertices; sourceVertex++)
		{
			for (int destinationVertex = 1; destinationVertex <= numberOfVertices; destinationVertex++)
			{
				Vertex iv = new Vertex().setVertexId(sourceVertex);
				Vertex jv = new Vertex().setVertexId(destinationVertex);
				Edge edge = graph.getEdge(iv, jv);
				int edgew = 0;

				if (edge != null) {
					edgew = edge.getEdgeWeight();
				}
				residualGraph[sourceVertex][destinationVertex] = edgew;
			}
		}

		while (bfs(source ,destination, residualGraph))
		{
			pathFlow = Integer.MAX_VALUE;
			for (v = destination; v != source; v = parent[v])
			{
				u = parent[v];
				pathFlow = Math.min(pathFlow, residualGraph[u][v]);
			}
			for (v = destination; v != source; v = parent[v])
			{
				u = parent[v];
				residualGraph[u][v] -= pathFlow;
				residualGraph[v][u] += pathFlow;
			}
			maxFlow += pathFlow;
		}

		return maxFlow;
	}

}
