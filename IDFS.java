import java.util.*;

class IDFS {
    private int V;
    private LinkedList<Integer> adj[];

    IDFS(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<>();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    boolean DFS_Traversal(int s, int d) {
        boolean visited[] = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        stack.push(s);

        while (!stack.isEmpty()) {
            int current = stack.pop();
            visited[current] = true;

            if (current == d)
                return true;

            for (int neighbor : adj[current]) {
                if (!visited[neighbor]) {
                    stack.push(neighbor);
                }
            }
        }
        return false;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Vertices: ");
        int V = scanner.nextInt();
        IDFS g = new IDFS(V);

        System.out.print("Edges: ");
        int E = scanner.nextInt();

        System.out.println("Enter edges:");
        for (int i = 0; i < E; i++) {
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            g.addEdge(v, w);
        }

        System.out.print("Source: ");
        int source = scanner.nextInt();
        System.out.print("Destination: ");
        int destination = scanner.nextInt();

        System.out.println("Path exists: " + g.DFS_Traversal(source, destination));

        scanner.close();
    }
}

