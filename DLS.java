import java.util.*;

class DLS {
    private int V;
    private LinkedList<Integer> adj[];

    DLS(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<>();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    boolean DLS_Traversal(int s, int d, int depthLimit) {
        boolean visited[] = new boolean[V];
        return DLSUtil(s, d, depthLimit, visited);
    }

    boolean DLSUtil(int v, int d, int limit, boolean visited[]) {
        if (v == d)
            return true;

        if (limit <= 0)
            return false;

        visited[v] = true;

        for (int n : adj[v]) {
            if (!visited[n] && DLSUtil(n, d, limit - 1, visited))
                return true;
        }
        return false;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Vertices: ");
        int V = scanner.nextInt();
        DLS g = new DLS(V);

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
        System.out.print("Depth Limit: ");
        int depthLimit = scanner.nextInt();

        System.out.println("Path exists within depth limit: " + g.DLS_Traversal(source, destination, depthLimit));

        scanner.close();
    }
}
