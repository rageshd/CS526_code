import java.util.List;

public class Node {
    char letter;
    List<Edge> edges;
    int distanceFromZ;

    @Override
    public String toString() {
        return "Node{" +
                "letter=" + letter +
                ", edges=" + edges +
                ", distanceFromZ=" + distanceFromZ +
                '}';
    }
}
