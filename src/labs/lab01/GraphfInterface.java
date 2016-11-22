package labs.lab01;

/**
 * Created by mtopor on 22.11.16.
 */
public interface GraphfInterface {


    public void addVertex(Vertex vertex);

    public void removeVertex(Vertex vertex);

    public void addEdge(Vertex initialVertex, Vertex finalVertex, Edge edge);

    public void removeEdge(Edge edge);

    public int getVertexCount();

    public boolean vertexExists(Vertex vertex);

    public boolean edgeExists(Vertex initialVertex, Vertex finalVertex);

    public Edge getEdge(Vertex initialVertex, Vertex finalVertex);
}
