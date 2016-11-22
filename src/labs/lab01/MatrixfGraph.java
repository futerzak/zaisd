package labs.lab01;

import labs.lab03.Edge;
import labs.lab03.Vertex;

import java.util.Arrays;

public class MatrixfGraph implements GraphfInterface
{
    private Edge[][] graph = new Edge[0][0];
    private int vertexCount = 0;
    private Vertex[] vertices = new Vertex[0];


    public void addEdge(Vertex initialVertex, Vertex finalVertex, Edge edge)
    {
        this.graph[initialVertex.getVertexId()][finalVertex.getVertexId()] = edge;
    }

    public Edge getEdge(Vertex initialVertex, Vertex finalVertex)
    {
        return this.graph[initialVertex.getVertexId()][finalVertex.getVertexId()];
    }

    public int getVertexCount()
    {
        return this.vertexCount;
    }

    public void addVertex(Vertex vertex) {
        if (!this.vertexExists(vertex)) {

            this.vertices = java.util.Arrays.copyOf(this.vertices, this.vertexCount + 1);
            this.vertices[this.vertexCount] = vertex;

            this.vertexCount++;
        }

    }

    public void removeVertex(Vertex vertex) {
        for(Vertex v : this.vertices) {
            if (v.getVertexId() == vertex.getVertexId()) {
                v = null;
                break;
            }
        }

    }

    public void removeEdge(Edge edge) {
        for(Edge[] e : this.graph) {
            for (Edge ee : e) {
                if (ee.getEdgeId() == edge.getEdgeId()) {
                    e = null;
                    break;
                }
            }
        }
    }

    public MatrixGraph() {
        this.graph = new Edge[2000][2000];
    }


    public boolean vertexExists(Vertex vertex) {
        boolean exists = false;

        for(Vertex v : this.vertices) {
            if (v.getVertexId() == vertex.getVertexId()) {
                exists = true;
                break;
            }
        }

        return exists;
    }

    public boolean edgeExists(Vertex initialVertex, Vertex finalVertex) {

        return this.graph[initialVertex.getVertexId()][finalVertex.getVertexId()] != null;

    }

//	public int getEdge(int initialVertexId, int finalVertexId) {
//		Edge edge = this.graph[initialVertexId][finalVertexId];
//		if (edge != null) {
//			return this.graph[initialVertexId][finalVertexId].getEdgeWeight();
//		} else {
//			return 0;
//		}
//
//	}

//
//	public int[][] toArray()
//	{
//		int[][] array = new int[1000][1000];
//
//		for(Edge[] e : this.graph) {
//			for (Edge ee : e) {
//
//				array.
//				if (ee.getEdgeId() == edge.getEdgeId()) {
//					e = null;
//					break;
//				}
//			}
//		}
//
//		return null;
//	}
}
