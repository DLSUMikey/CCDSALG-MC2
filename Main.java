import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean notExited = true;
        GraphStorage storage = new GraphStorage();
        Scanner myScanner = new Scanner(System.in);

        // Assuming the file is located in the "data" folder relative to the current directory
        System.out.print("Input file path: ");
        String fileName = myScanner.nextLine();

        storage.loadGraphFromFile("data/" + fileName); // Use the full path provided by the user
        HashMap<Integer, List<Integer>> loadedGraph = storage.getGraph();

        System.out.println("Graph Loaded!");

        while (notExited) {
            System.out.println("MAIN MENU");
            System.out.println("[1] Get friend list");
            System.out.println("[2] Get connection");
            System.out.println("[3] Exit");
            System.out.print("\nEnter Your Choice: ");

            int mainChoice = myScanner.nextInt();
            System.out.println(mainChoice);

            switch (mainChoice) {
                case 1:
                boolean validId = false;
                while (!validId) {
                    System.out.print("Enter ID of person: ");
                    if(myScanner.hasNextInt()) {
                        int id = myScanner.nextInt();
                        myScanner.nextLine(); // Consume newline left-over
                        if (loadedGraph.containsKey(id)) {
                            List<Integer> friends = loadedGraph.get(id);
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
                case 4:
                    System.out.println("2");
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
