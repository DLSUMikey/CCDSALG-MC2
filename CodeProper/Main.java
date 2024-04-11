import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        GraphStorage storage = new GraphStorage();
        storage.loadGraphFromFile("data/Caltech36.txt"); // replace with your file's path
        HashMap<Integer, List<Integer>> loadedGraph = storage.getGraph();

        // Print out the graph to see if it looks right
        for (Integer person : loadedGraph.keySet()) {
            System.out.println("Person " + person + " has friends: " + loadedGraph.get(person));
        }
    }
}
