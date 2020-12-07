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

        printAdjacency(adjacencyMatrix, peopleLable);

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
                    List<String> personsFriends = new ArrayList<String>();
                    System.out.print("Enter name : ");
                    String userName = userInput.next();
                    if (personPos.containsKey(userName)){

                        int personIndex = personPos.get(userName);
                        for (int i=0; i<adjacencyMatrix[personIndex].length;i++)    {
                            if (adjacencyMatrix[personIndex][i] == 1){
                                personsFriends.add(peopleLable.get(i));
                            }
                        }
                    }
                    else
                    {
                        System.out.println("user entered does not exist, try again");
                        continue;
                    }
                    System.out.printf("%s's friends are : %s\n",userName,getListAsCsvString(personsFriends));
                    continue;
                }
                // User selects 2
                else if (userSel == 2){
                    System.out.print("Enter the 2 names separated by a space: ");
                    String firstName = userInput.next();
                    String secondName = userInput.next();
//                    System.out.println(names);
//                    String firstName = names.split(" ")[0];
//                    String secondName = names.split(" ")[1];
                    if (personPos.containsKey(firstName) && personPos.containsKey(secondName)){
                        if (adjacencyMatrix[personPos.get(firstName)][personPos.get(secondName)] == 1)
                            System.out.println("Yes");
                        else
                            System.out.println("No");
                    }
                    else{
                        System.out.println("The users entered do not exist , try again");

                    }
                    continue;
                }
                else if (userSel == 3){
                    System.out.println("Thank you , and have a great day!!");
                    break;
                }
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

    public static String getListAsCsvString(List<String> list){
        StringBuilder sb = new StringBuilder();
        for(String str:list){
            if(sb.length() != 0){
                sb.append(",");
            }
            sb.append(str);
        }
        return sb.toString();
    }
}