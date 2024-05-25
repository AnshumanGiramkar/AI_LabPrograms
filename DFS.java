import java.util.*;

class DFS {
    private int V;
    private LinkedList<Integer> adj[];

    DFS(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<>();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    boolean DFSUtil(int v, boolean visited[], int d) {
        visited[v] = true;
        if (v == d)
            return true;

        for (int n : adj[v]) {
            if (!visited[n]) {
                if (DFSUtil(n, visited, d))
                    return true;
            }
        }
        return false;
    }

    boolean DFS_Traversal(int s, int d) {
        boolean visited[] = new boolean[V];
        return DFSUtil(s, visited, d);
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Vertices: ");
        int V = scanner.nextInt();
        DFS g = new DFS(V);

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
