import java.util.List;

public class Node {
    char letter;
    List<Edge> edges;
    int distanceFromZ;
    boolean visited;

    @Override
    public String toString() {
        return "Node{" +
                "letter=" + letter +
                ", edges=" + edges +
                ", distanceFromZ=" + distanceFromZ +
                ", visited=" + visited +
                '}';
    }

    public Edge getEdge(Node nextNode){
        Edge edge = null;
        for (Edge e : this.edges){
            if (e.destNodeLetter == nextNode.letter){
                edge = e;
                break;
            }
        }
        return edge;
    }
}
