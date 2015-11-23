import java.util.Iterator;

import javax.xml.xpath.XPath;

import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP
{
    private Digraph G;

    private void ValidateIsNotNull(Object obj)
    {
        if (obj == null)
            throw new NullPointerException();
    }

    private void ValidateBounds(int s)
    {
        if (0 > s) {
            throw new IndexOutOfBoundsException("Source s:" + s
                    + "s is out of bounds. Must be minimum 0");
        }

        if (s > G.V() - 1) {
            throw new IndexOutOfBoundsException("Source s:" + s
                    + "s is out of bounds. Must be max of" + (G.V() - 1));
        }
    }

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G)
    {
        ValidateIsNotNull(G);
        this.G = G;
        
        Digraph rev = G.reverse();
                
        this.marked = new boolean[G.V()];
        this.path = new Integer[G.V()];
        this.scc = new Integer[G.V()];
        this.count = 0;
        
        for (int s = 0; s < rev.V(); s++) {
            if (!marked[s]){
                bfs(rev, s);
                count++;
            }
        }
        
        StdOut.print("done with bfs");
    }
    
    private boolean[] marked;
    private Integer[] path;
    private Integer[] scc;
    private int count;
    
    private void bfs(Digraph g, int s){
        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(s);
        marked[s] = true;
        scc[s] = count;
        StdOut.println("BFS(" + s + ")" );
        
        while(q.size() > 0){
            int v = q.dequeue();
            StdOut.println("\tIterable(" + v + ")" );
            for (Integer w : g.adj(v)) {
                if (!marked[w]){
                    q.enqueue(w);
                    marked[w] = true;
                    path[w] = v;
                    scc[w] = count;
                }
            }
        }
        
        StdOut.println("BFS(" + s + ")" );
    }
    
    private Iterable<Integer> pathTo(int v){
        Stack<Integer> pathToRoot = new Stack<Integer>();
        
        Integer current = new Integer(v);
        Integer next = path[current];
        
        pathToRoot.push(current);
        
        while(next != null){
            current = next;
            pathToRoot.push(current);
            next = path[current];
        }
        
        return pathToRoot;
    }
    
    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w)
    {
        ValidateBounds(v);
        ValidateBounds(w);
        if ( scc[v] != scc[w] ) return -1;
        
        Iterable<Integer> vPath = pathTo(v);
        Iterable<Integer> wPath = pathTo(w);
        
        Iterator<Integer> vIterator = vPath.iterator();
        Iterator<Integer> wIterator = wPath.iterator();
        
        int length = 2;
        while (vIterator.hasNext() && wIterator.hasNext()) {
            int vItem = vIterator.next();
            int wItem = wIterator.next();
            if ( vItem != wItem){
                break;
            }
        }
        
        while(vIterator.hasNext()){
            vIterator.next();
            length++;
        }
        
        while(wIterator.hasNext()){
            wIterator.next();
            length++;
        }
        
        return length;
        
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w)
    {
        ValidateBounds(v);
        ValidateBounds(w);
        if ( scc[v] != scc[w] ) return -1;
        int leastCommonAncestor = -1;
        
        
        Iterable<Integer> vPath = pathTo(v);
        Iterable<Integer> wPath = pathTo(w);
        
        Iterator<Integer> vIterator = vPath.iterator();
        Iterator<Integer> wIterator = wPath.iterator();
        
        while (vIterator.hasNext() && wIterator.hasNext()) {
            int vItem = vIterator.next();
            int wItem = wIterator.next();
            if ( vItem != wItem){
                break;
            }
            leastCommonAncestor = vItem;
        }
        
        
        return leastCommonAncestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
        ValidateIsNotNull(v);
        ValidateIsNotNull(w);
        return -1;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        ValidateIsNotNull(v);
        ValidateIsNotNull(w);
        return -1;
    }

    // do unit testing of this class
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
