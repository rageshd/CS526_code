import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class GraphHelper {
    Map<Character, Node> charNodeMap = new HashMap<Character, Node>();

    public static void main(String[] args) throws IOException {
        GraphHelper gh = new GraphHelper();
        gh.loadGraph();
        Stack<Node> pathStack = gh.traverseGraph('G', 'Z', 2);
        List<Node> pathList = gh.stackToList(pathStack);
        System.out.println(gh.graphAsString(pathList));
        System.out.println(gh.findDistanceOfPath(pathList));

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
                        e.destNodeLetter = charArray[i-1];
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

    }


    /**
     * Traverse the graph from start node to end
     * @param start
     * @param end
     * @param algo
     * @return
     */
    public Stack<Node> traverseGraph(char start, char end, int algo){
        Node currentNode = charNodeMap.get(start);
        // Stack to keep trace of traversals
        Stack<Node> s = new Stack<Node>();
        // Keep looping till we get to the end node
        while(currentNode.letter != end){
//            System.out.println(currentNode);
            Node nextNode = null;
            // Set the current node to visited
            currentNode.visited = true;
            // Push node to stack
            s.push(currentNode);
            // Boolean variable to check if there are any visitable nodes from current node
            boolean hasVisitableChildren = false;
            int minDistance = Integer.MAX_VALUE;
            // Loop thru all the edges
            for (Edge e: currentNode.edges){
                char edgeLetter = e.destNodeLetter;
                Node edgeNode = charNodeMap.get(edgeLetter);
                // Consider the node the edge connects to in case its not already visited and its not the parent
                if (!edgeNode.visited && e.distance!=0) {
                    int distance=0;
                    if (algo==1)
                        distance = edgeNode.distanceFromZ;
                    else if (algo==2)
                        distance = e.distance + edgeNode.distanceFromZ;
                    // If the node is at a lesser ditance that the previous least then consider that
                    if (distance < minDistance) {
                        minDistance = distance;
                        nextNode = charNodeMap.get(edgeLetter);
                        hasVisitableChildren = true;
                    }
                }
            }
            // In case there are no visitable nodes
            if (!hasVisitableChildren){
                s.pop();
                if (!s.empty()){
                    currentNode = s.pop();
                }
                else
                    break;
            }
            else
                currentNode = nextNode;


        }
        if (currentNode.letter == end)
            s.push(currentNode);
        return s;
    }


    /**
     * print the node list as a connected graph
     * @param l
     */
    public String graphAsString(List<Node> l){
        String retString="";
        for (int i =0; i<l.size()-1;i++)
            retString+=l.get(i).letter + "->";

        retString+=l.get(l.size()-1).letter;
        return retString;
    }

    public List<Node> stackToList(Stack<Node> s){
        Stack<Node> temp = new Stack<>();
        List<Node> ret = new ArrayList<>();
        while(!s.isEmpty())
            temp.push(s.pop());
        while(!temp.isEmpty())
            ret.add(temp.pop());

        return ret;

    }

    public int findDistanceOfPath(List<Node> nodeList){
        int totalDist=0;
        for (int i=0;i<nodeList.size()-1;i++){
            Node nextNode = nodeList.get(i+1);
            Node currNode = nodeList.get(i);
            Edge nextEdge = currNode.getEdge(nextNode);
            totalDist+=nextEdge.distance;
        }
        return totalDist;
    }


}
