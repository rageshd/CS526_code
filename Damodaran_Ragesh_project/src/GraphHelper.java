import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class GraphHelper {
    Map<Character, Node> charNodeMap = new HashMap<Character, Node>();

    public static void main(String[] args) throws IOException {
        GraphHelper gh = new GraphHelper();
        gh.loadGraph();
    }

    /**
     * Load the map of characters and nodes
     * @throws IOException
     */
    public void loadGraph() throws IOException {
        // Load the graph input file1
        File file1 = new File("/Users/ragesh/Documents/Boston/CS526/CS526_code/Damodaran_Ragesh_project/resources/graph_input.txt");
        BufferedReader br1 = new BufferedReader(new FileReader(file1));
        String st1;
        char[] charArray = new char[27];

        int linNum=0;
        while ((st1 = br1.readLine()) != null){
            String[] lineSplit = st1.trim().split("\\s+");
            if(linNum == 0) {
                for (int i=0; i<lineSplit.length; i++){
                    charArray[i] = lineSplit[i].charAt(0);
                }
            }
            else{
                Node n = new Node();
                List<Edge> edgeList = new ArrayList<Edge>();
                char letter = lineSplit[0].charAt(0);
                n.letter = letter;
                for (int i=1; i<lineSplit.length; i++){
                    if (Integer.parseInt(lineSplit[i])>0){
                        Edge e = new Edge();
                        e.distance = Integer.parseInt(lineSplit[i]);
                        char[] nodeLetters = new char[2];
                        nodeLetters[0] = letter;
                        nodeLetters[1] = charArray[i-1];
                        e.nodeLetters = nodeLetters;
                        edgeList.add(e);
                    }
                }
                n.edges = edgeList;
                charNodeMap.put(letter, n);
            }
            linNum++;
        }
        br1.close();

        // Load the direct distance file
        File file2 = new File("/Users/ragesh/Documents/Boston/CS526/CS526_code/Damodaran_Ragesh_project/resources/direct_distance.txt");
        BufferedReader br2 = new BufferedReader(new FileReader(file2));
        String st2;
        while ((st2 = br2.readLine()) != null){
            String[] lineSplit = st2.trim().split("\\s+");
            char nodeLetter = lineSplit[0].charAt(0);
            charNodeMap.get(nodeLetter).distanceFromZ = Integer.parseInt(lineSplit[1]);

        }
        br2.close();

//        for (Node n : charNodeMap.values())
//            System.out.println(n);

    }



    public void traverseGraphAlgo1(char start, char end){
        Node currentNode = charNodeMap.get(start);
        Stack<Node> s = new Stack<Node>();
        while(currentNode.letter != end){
            for ()
        }
    }

    public void traverseGraphAlgo2(char start, char end){

    }

}
