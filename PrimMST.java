import edu.princeton.cs.algs4.*;

public class PrimMST{
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;
    
    public PrimMST(EdgeWeightedGraph G){
        System.out.println(G.toString());
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        
        for(int v = 0; v < G.V(); v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<Double>(G.V());
        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while(!pq.isEmpty())
            visit(G, pq.delMin());
    }
    private void visit(EdgeWeightedGraph G, int v){
        marked[v] = true;
        for(Edge e : G.adj(v)){
            int w = e.other(v);
            if(marked[w]) continue;
            if(e.weight() < distTo[w]){
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if(pq.contains(w)) pq.changeKey(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }
    public Iterable<Edge> edges(){
        Queue<Edge> mst = new Queue<Edge>();
        for(int v = 0; v < edgeTo.length; v++){
            Edge e = edgeTo[v];
            if(e != null){
                mst.enqueue(e);
            }
        }
        return mst;
    }
    public static void main(String[] args){
        String[] vertices = {"A", "B", "C", "D", "E", "F", "G"};
		int source = 2;
		int n = 7;
		EdgeWeightedGraph G = new EdgeWeightedGraph(n);
		Edge e1 = new Edge(0, 1, 6);
		Edge e2 = new Edge(0, 2, 12);
		Edge e3 = new Edge(1, 2, 3);
		Edge e4 = new Edge(1, 4, 4);
		Edge e5 = new Edge(1, 5, 7);
		Edge e6 = new Edge(1, 6, 4);
		Edge e7 = new Edge(2, 3, 9);
		Edge e8 = new Edge(2, 4, 5);
		Edge e9 = new Edge(4, 5, 2);
		G.addEdge(e1);
		G.addEdge(e2);
		G.addEdge(e3);
		G.addEdge(e4);
		G.addEdge(e5);
		G.addEdge(e6);
		G.addEdge(e7);
		G.addEdge(e8);
		G.addEdge(e9);
        
        PrimMST prim = new PrimMST(G);
        System.out.println(prim.edges());
        
    }
}