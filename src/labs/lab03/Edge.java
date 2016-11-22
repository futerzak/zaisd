package labs.lab03;

/**
 * Created by mtopor on 22.11.16.
 */
public class Edge {
    private int edgeId;
    private Vertex initialVertex;
    private Vertex finalVertex;
    private int edgeWeight;
    private Edge previousEdge;
    private Edge nextEdge;

    public int getEdgeId()
    {
        return this.edgeId;
    }

    public Edge setEdgeId(int edgeId)
    {
        this.edgeId = edgeId;

        return this;
    }

    public Vertex getInitialVertex()
    {
        return this.initialVertex;
    }

    public Edge setInitialVertex(Vertex initialVertex)
    {
        this.initialVertex = initialVertex;

        return this;
    }

    public Vertex getFinalVertex()
    {
        return this.finalVertex;
    }

    public Edge setFinalVertex(Vertex finalVertex)
    {
        this.finalVertex = finalVertex;

        return this;
    }

    public Edge getPreviousEdge()
    {
        return this.previousEdge;
    }

    public Edge setPreviousEdge(Edge previousEdge)
    {
        this.previousEdge = previousEdge;

        return this;
    }

    public Edge getNextEdge()
    {
        return this.nextEdge;
    }

    public Edge setNextEdge(Edge nextEdge)
    {
        this.nextEdge = nextEdge;

        return this;
    }

    public int getEdgeWeight()
    {
        return this.edgeWeight;
    }

    public Edge setEdgeWeight(int edgeWeight)
    {
        this.edgeWeight = edgeWeight;

        return this;
    }
}
