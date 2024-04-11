import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class GraphStorage {

    private HashMap<Integer, Set<Integer>> graph = new HashMap<>();

    public void loadGraphFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int node1 = Integer.parseInt(parts[0]);
                int node2 = Integer.parseInt(parts[1]);

                graph.computeIfAbsent(node1, k -> new HashSet<>()).add(node2);
                graph.computeIfAbsent(node2, k -> new HashSet<>()).add(node1); // For undirected graph
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, Set<Integer>> getGraph() {
        return graph;
    }
}
