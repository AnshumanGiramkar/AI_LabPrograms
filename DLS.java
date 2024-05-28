import java.util.*;

class Node {
    String value;
    List<Node> neighbors;

    Node(String value) {
        this.value = value;
        this.neighbors = new ArrayList<>();
    }

    void addNeighbor(Node neighbor) {
        this.neighbors.add(neighbor);
    }
}

public class DepthLimitedSearch {
    public static void main(String[] args) {
        // Creating nodes
        Node root = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        
        // Connecting nodes
        root.addNeighbor(nodeB);
        root.addNeighbor(nodeC);
        nodeB.addNeighbor(nodeD);
        nodeB.addNeighbor(nodeE);
        nodeC.addNeighbor(nodeF);

        // Perform Depth-Limited Search
        int limit = 2; // depth limit
        String target = "E";
        boolean found = depthLimitedSearch(root, target, limit);
        
        if (found) {
            System.out.println("Target " + target + " found within depth limit " + limit);
        } else {
            System.out.println("Target " + target + " NOT found within depth limit " + limit);
        }
    }

    public static boolean depthLimitedSearch(Node node, String target, int limit) {
        return dlsHelper(node, target, limit, 0);
    }

    private static boolean dlsHelper(Node node, String target, int limit, int depth) {
        System.out.println("Visiting Node " + node.value + " at depth " + depth);
        
        if (node.value.equals(target)) {
            return true;
        }
        
        if (depth >= limit) {
            return false;
        }
        
        for (Node neighbor : node.neighbors) {
            if (dlsHelper(neighbor, target, limit, depth + 1)) {
                return true;
            }
        }
        
        return false;
    }
}
