import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        boolean notExited = true;
        GraphStorage storage = new GraphStorage();
        Scanner myScanner = new Scanner(System.in);

        // Assuming the file is located in the "data" folder relative to the current directory
        System.out.print("Input file path: ");
        String fileName = myScanner.nextLine();

        storage.loadGraphFromFile("data/" + fileName); // Use the full path provided by the user
        HashMap<Integer, Set<Integer>> loadedGraph = storage.getGraph();

        System.out.println("Graph Loaded!");

        while (notExited) {
            System.out.println("MAIN MENU");
            System.out.println("[1] Get friend list");
            System.out.println("[2] Get connection");
            System.out.println("[3] Exit");
            System.out.print("\nEnter Your Choice: ");

            int mainChoice = myScanner.nextInt();

            switch (mainChoice) {
                case 1:
                boolean validId = false;
                while (!validId) {
                    System.out.print("Enter ID of person: ");
                    if(myScanner.hasNextInt()) {
                        int id = myScanner.nextInt();
                        myScanner.nextLine(); // Consume newline left-over
                        if (loadedGraph.containsKey(id)) {
                            Set<Integer> friends = loadedGraph.get(id);
                            System.out.println("Person " + id + " has " + friends.size() + " friends!");
                            System.out.println("List of friends: " + friends + "\n");
                            validId = true; // Set flag to true as we have found a valid ID
                        } else {
                            System.out.println("ID not found, please try again.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a number.");
                        myScanner.next(); // Consume the invalid input
                    }
                }
                break;
                case 2:
                boolean validConnectionFound = false;
                while (!validConnectionFound) {
                    System.out.print("Enter ID of first person: ");
                    int personOneId = -1;
                    while (!myScanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number for the first person.");
                        myScanner.next(); // Consume the invalid input
                    }
                    personOneId = myScanner.nextInt();
                    myScanner.nextLine(); // Consume the newline character
            
                    System.out.print("Enter ID of second person: ");
                    int personTwoId = -1;
                    while (!myScanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number for the second person.");
                        myScanner.next(); // Consume the invalid input
                    }
                    personTwoId = myScanner.nextInt();
                    myScanner.nextLine(); // Consume the newline character
            
                    if (!loadedGraph.containsKey(personOneId) || !loadedGraph.containsKey(personTwoId)) {
                        System.out.println("One or both IDs are not in the graph. Please try again.");
                    } else {
                        List<Integer> connectionPath = GraphAlgorithms.bfs(loadedGraph, personOneId, personTwoId);
                        if (connectionPath != null && !connectionPath.isEmpty()) {
                            validConnectionFound = true; // A valid connection was found
                            System.out.println("There is a connection from " + personOneId + " to " + personTwoId + "!");
                            for (int i = 0; i < connectionPath.size() - 1; i++) {
                                System.out.println(connectionPath.get(i) + " is friends with " + connectionPath.get(i + 1));
                            }
                            System.out.println("");
                        } else {
                            System.out.println("Cannot find a connection between " + personOneId + " and " + personTwoId + ". Please try again.");
                        }
                    }
                }
                break;
            
            
            

                case 3:
                    System.out.println("Exiting...");
                    notExited = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }

        myScanner.close();
    }
}
