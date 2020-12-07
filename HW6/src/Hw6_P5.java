import java.io.*;
import java.util.*;

public class Hw6_P5 {
    private static final String FRIEND_INPUT = "/Users/ragesh/Documents/Boston/CS526/metcs526_Assignment6/friends_input.txt";

    public static void main(String[] args) throws IOException {
        HashMap<String,HashSet<String>> friendMap = new HashMap<String, HashSet<String>>();
        HashSet<String> uniquePeople = new HashSet<String>();
        File file = new File(FRIEND_INPUT);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null){
            String[] lineArray = line.split(",");
            String firstPerson = lineArray[0].trim();
            String secondPerson = lineArray[1].trim();
            // Add to unique persons hashset
            uniquePeople.add(firstPerson);
            uniquePeople.add(secondPerson);
            // Check if the key already exists in map if so add to the value hashset
            if (friendMap.containsKey(firstPerson)){
                friendMap.get(firstPerson).add(secondPerson);
            }
            // Else if key does not exist create new value hashset and add to map
            else{
                HashSet<String> friendSet = new HashSet<String>();
                friendSet.add(secondPerson);
                friendMap.put(firstPerson,friendSet);
            }
        }

        // Convert uniquePeople hashset to label array
        ArrayList<String> peopleLable = new ArrayList<String>(uniquePeople);
        Collections.sort(peopleLable);

        // Create a person to array position label
        HashMap<String,Integer> personPos = new HashMap<String,Integer>();
        for (int i=0; i<peopleLable.size(); i++){
            personPos.put(peopleLable.get(i),i);
        }

        // Create the adjacency matrix
        int[][] adjacencyMatrix = new int[peopleLable.size()][peopleLable.size()];
        for (int i=0; i<adjacencyMatrix.length; i++)
            for (int j=0; j<adjacencyMatrix[i].length; j++)
                adjacencyMatrix[i][j]=0;


        for (Map.Entry<String,HashSet<String>> entry : friendMap.entrySet()){
            String key = entry.getKey();
            Integer rowIndex = personPos.get(key);
            HashSet<String> value = entry.getValue();
            for (String v : value){
                Integer colIndex = personPos.get(v);
                adjacencyMatrix[rowIndex][colIndex]=1;
                adjacencyMatrix[colIndex][rowIndex]=1;
            }
        }

        printAdjacency(adjacencyMatrix, peopleLable);

    }


    public static void printAdjacency(int[][] adjacencyMatrix, List<String> lables){
        System.out.print("          ");
        for (int i=0;i<lables.size() ; i++)
            System.out.print(String.format("%1$-" + 10 + "s",lables.get(i)));
        System.out.println();
        for (int i=0;i< adjacencyMatrix.length ; i++) {
            System.out.print(String.format("%1$-" + 10 + "s",lables.get(i) ));
            for (int j=0; j<adjacencyMatrix[i].length;j++)
                System.out.print(String.format("%1$-" + 10 + "s",adjacencyMatrix[i][j]==0?" ":1));
            System.out.println();
        }
    }
}
