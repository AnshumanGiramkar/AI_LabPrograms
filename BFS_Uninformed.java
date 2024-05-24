import java.io.*;
import java.util.*;

class Graph {
    private int V;
    private LinkedList<Integer> adj[];

    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    boolean BFS(int s, int d) {
        boolean visited[] = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[s] = true;
        queue.add(s);

        while (!queue.isEmpty()) {
            s = queue.poll();
            if (s == d)
                return true;

            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        return false;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of vertices:");
        int V = scanner.nextInt();
        Graph g = new Graph(V);

        System.out.println("Enter the number of edges:");
        int E = scanner.nextInt();

        System.out.println("Enter edges (vertex pairs separated by space):");
        for (int i = 0; i < E; i++) {
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            g.addEdge(v, w);
        }

        System.out.println("Enter the source vertex:");
        int source = scanner.nextInt();
        System.out.println("Enter the destination vertex:");
        int destination = scanner.nextInt();

        System.out.println("Path exists between source and destination: " + g.BFS(source, destination));
    }
}
