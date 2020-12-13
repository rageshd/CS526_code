import java.io.*;
import java.util.*;

public class Hw6_P5 {
    private static final String FRIEND_INPUT = "/Users/ragesh/Documents/Boston/CS526/metcs526_Assignment6/friends_input.txt";
    ArrayList<String> peopleLable;
    int[][] adjacencyMatrix;
    HashMap<String,Integer> personPos;
    /**
     * Main method
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Hw6_P5 h6 = new Hw6_P5();
        // Load the adjacency matrix
        h6.loadAdjacencyMatrix();
        // print the adjacency matrix
        h6.printAdjacency(h6.adjacencyMatrix, h6.peopleLable);

        // Loop through and prompt user input
        while (true){
            try {
                // Prompt and get user input
                Scanner userInput = new Scanner(System.in);
                System.out.println("Main Menu");
                System.out.println("Search options:\n1. Friends of a person\n2. Friend or not?\n3. Exit\n");
                System.out.print("Enter your selection : ");
                int userSel = userInput.nextInt();

                // User selects 1
                if (userSel == 1){
                    HashSet<String> personsFriends = new HashSet<String>();
                    System.out.print("Enter name : ");
                    String userName = userInput.next();
                    // Check if user entered exists in the system and proceed if so
                    if (h6.personPos.containsKey(userName)){

                        int personIndex = h6.personPos.get(userName);
                        for (int i=0; i<h6.adjacencyMatrix[personIndex].length;i++)    {
                            if (h6.adjacencyMatrix[personIndex][i] == 1){
                                personsFriends.add(h6.peopleLable.get(i));
                                for (int j=0; j<h6.adjacencyMatrix[i].length;j++){
                                    if (h6.adjacencyMatrix[i][j] == 1 && j!=personIndex)
                                        personsFriends.add(h6.peopleLable.get(j));
                                }
                            }
                        }
                    }
                    // If user does not exist ask to try again
                    else
                    {
                        System.out.println("user entered does not exist, try again");
                        continue;
                    }
                    // Print the users friends
                    System.out.printf("%s's friends are : %s\n",userName,h6.getSetAsCsvString(personsFriends));
                    continue;
                }
                // User selects 2
                else if (userSel == 2){
                    System.out.print("Enter the 2 names separated by a space: ");
                    String firstName = userInput.next();
                    String secondName = userInput.next();
                    boolean isFriend=false;
                    // Check if the entered users exist
                    if (h6.personPos.containsKey(firstName) && h6.personPos.containsKey(secondName)){
                        int firstIndex = h6.personPos.get(firstName);
                        int secondIndex = h6.personPos.get(secondName);
                        // Check if direct friend
                        if (h6.adjacencyMatrix[firstIndex][secondIndex] == 1)
                            isFriend=true;
                        //Check for indirect friends
                        else
                        {
                            for (int i=0; i<h6.adjacencyMatrix[firstIndex].length; i++){
                                if (h6.adjacencyMatrix[firstIndex][i] == 1){
                                    if (h6.adjacencyMatrix[i][secondIndex]==1) {
                                        isFriend = true;
                                        break;
                                    }
                                }

                            }
                        }
                        if (isFriend)
                            System.out.printf("Yes, %s and %s are friends\n", firstName, secondName);
                        else
                            System.out.printf("No, %s and %s are not friends\n", firstName, secondName);
                    }
                    // If they entered users don't exist reprompt
                    else{
                        System.out.println("The users entered do not exist , try again");

                    }
                    continue;
                }
                // User selects to exit
                else if (userSel == 3){
                    System.out.println("Thank you, and have a great day!!");
                    break;
                }
                // User entered incorrect selection option
                else{
                    System.out.println("Incorrect selection, please enter 1,2 or 3");
                    continue;
                }

            }catch (Exception e)
            {
                System.out.println("Incorrect user input , try again");
                continue;
            }
        }

    }

    /**
     * Load the adjacency matrix
     * @throws IOException
     */
    public void loadAdjacencyMatrix() throws IOException {
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
        peopleLable = new ArrayList<String>(uniquePeople);
        Collections.sort(peopleLable);

        // Create a person to array position label
        personPos = new HashMap<String,Integer>();
        for (int i=0; i<peopleLable.size(); i++){
            personPos.put(peopleLable.get(i),i);
        }

        // Create the adjacency matrix
        adjacencyMatrix = new int[peopleLable.size()][peopleLable.size()];
        for (int i=0; i<adjacencyMatrix.length; i++)
            for (int j=0; j<adjacencyMatrix[i].length; j++)
                adjacencyMatrix[i][j]=0;

        // Populate the adjacency matrix
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
    }

    /**
     * Print the adjacency matrix
     * @param adjacencyMatrix
     * @param lables
     */
    public void printAdjacency(int[][] adjacencyMatrix, List<String> lables){
        System.out.print("          ");
        for (int i=0;i<lables.size() ; i++)
            System.out.print(String.format("%1$-" + 10 + "s",lables.get(i)));
        System.out.println();
        for (int i=0;i< adjacencyMatrix.length ; i++) {
            System.out.print(String.format("%1$-" + 10 + "s",lables.get(i) ));
            for (int j=0; j<adjacencyMatrix[i].length;j++)
                System.out.print(String.format("%1$-" + 10 + "s",adjacencyMatrix[i][j]==0?" ":adjacencyMatrix[i][j]));
            System.out.println();
        }
    }

    /**
     * Convert set to comma separated string
     * @param set
     * @return
     */
    public String getSetAsCsvString(HashSet<String> set){
        StringBuilder sb = new StringBuilder();
        for(String str: set){
            if(sb.length() != 0){
                sb.append(",");
            }
            sb.append(str);
        }
        return sb.toString();
    }


}
