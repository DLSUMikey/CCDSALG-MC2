import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GraphAlgorithms {

    public static List<Integer> bfs(HashMap<Integer, Set<Integer>> graph, int start, int goal) {
        if (!graph.containsKey(start) || !graph.containsKey(goal)) {
            return null; // No path if start or goal not in graph
        }

        Queue<Integer> toVisit = new LinkedList<>();
        Map<Integer, Integer> prev = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        toVisit.add(start);
        visited.add(start);

        while (!toVisit.isEmpty()) {
            int current = toVisit.poll();
            if (current == goal) {
                return constructPath(prev, goal);
            }

            for (int neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    prev.put(neighbor, current);
                    toVisit.add(neighbor);
                }
            }
        }
        return null; // Return null if path doesn't exist
    }

    private static List<Integer> constructPath(Map<Integer, Integer> prev, int goal) {
        List<Integer> path = new ArrayList<>();
        for (Integer at = goal; at != null; at = prev.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
