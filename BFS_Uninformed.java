import java.util.*;

class BFS{
    private int V;
    private LinkedList<Integer> adj[];

    BFS(int v){
        this.V=v;
        adj = new LinkedList[v];
        for(int i=0; i<v; i++){
            adj[i]= new LinkedList<>();
        }
    }

    void addEdge(int s, int d){
        adj[s].add(d);
    }

    boolean BFS_Traversal(int s, int d){
        boolean[] visited=new boolean[V];
        Queue<Integer> queue=new LinkedList<>();

        queue.add(s);
        visited[s]=true;

        while(!queue.isEmpty()){
            s=queue.poll();
            if(s==d){
                return true;
            }
            for(int i: adj[s]){
                if(!visited[i]){
                    visited[i]=true;
                    queue.add(i);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);

        System.out.println("Enter number of Vertices:");
        int v=in.nextInt();
        BFS g=new BFS(v);

        System.out.println("Enter number of Edges:");
        int e=in.nextInt();

        System.out.println("Enter Edges like this- (v1,v2):");
        for(int i=0; i<e; i++){
            int v1=in.nextInt();
            int v2=in.nextInt();
            g.addEdge(v1,v2);
        }


        System.out.println("Enter Source Vertex:");
        int source=in.nextInt();

        System.out.println("Enter Destination Vertex:");
        int destination=in.nextInt();

        boolean flag=g.BFS_Traversal(source, destination);

        System.out.println("Traversal status: "+flag);

        in.close();
    }

}
