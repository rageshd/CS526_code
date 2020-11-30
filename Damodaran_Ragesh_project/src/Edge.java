import java.util.Arrays;

public class Edge {
    int distance;
    char[] nodeLetters;

    @Override
    public String toString() {
        return "Edge{" +
                "distance=" + distance +
                ", nodeLetters=" + Arrays.toString(nodeLetters) +
                '}';
    }
}
