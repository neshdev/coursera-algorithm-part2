import java.util.ArrayList;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
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
        if (s < 0) {
            throw new IndexOutOfBoundsException("Source s:" + s
                    + "s is out of bounds. Must be minimum 0");
        }

        if (s > (G.V() - 1)) {
            throw new IndexOutOfBoundsException("Source s:" + s
                    + "s is out of bounds. Must be max of" + (G.V() - 1));
        }
    }

    private void ValidateBounds(Iterable<Integer> source)
    {
        for (Integer s : source) {
            ValidateBounds(s);
        }
    }

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G)
    {
        ValidateIsNotNull(G);
        this.G = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w)
    {
        ValidateBounds(v);
        ValidateBounds(w);

        Queue<Integer> vQueue = new Queue<Integer>();
        vQueue.enqueue(v);

        Queue<Integer> wQueue = new Queue<Integer>();
        wQueue.enqueue(w);

        return this.length(vQueue, wQueue);
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w)
    {
        ValidateBounds(v);
        ValidateBounds(w);

        Queue<Integer> vQueue = new Queue<Integer>();
        vQueue.enqueue(v);

        Queue<Integer> wQueue = new Queue<Integer>();
        wQueue.enqueue(w);

        return this.ancestor(vQueue, wQueue);
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
        ValidateIsNotNull(v);
        ValidateIsNotNull(w);
        ValidateBounds(v);
        ValidateBounds(w);

        BreadthFirstDirectedPaths vBfps = new BreadthFirstDirectedPaths(this.G, v);
        BreadthFirstDirectedPaths wBfps = new BreadthFirstDirectedPaths(this.G, w);
        ArrayList<Integer> ancestors = new ArrayList<Integer>();

        int ancestor = -1;
        int championMinDist = Integer.MAX_VALUE;
        for (int x = 0; x < G.V(); x++) {
            if (vBfps.hasPathTo(x) && wBfps.hasPathTo(x)) {
                ancestors.add(x);
            }
        }

        for (Integer x : ancestors) {
            int dist = vBfps.distTo(x) + wBfps.distTo(x);
            if (championMinDist > dist) {
                championMinDist = dist;
                ancestor = x;
            }
        }

        if (ancestor == -1)
            championMinDist = -1;

        return championMinDist;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        ValidateIsNotNull(v);
        ValidateIsNotNull(w);
        ValidateBounds(v);
        ValidateBounds(w);

        BreadthFirstDirectedPaths vBfps = new BreadthFirstDirectedPaths(this.G, v);
        BreadthFirstDirectedPaths wBfps = new BreadthFirstDirectedPaths(this.G, w);

        ArrayList<Integer> ancestors = new ArrayList<Integer>();

        int ancestor = -1;
        int championMinDist = Integer.MAX_VALUE;
        for (int x = 0; x < G.V(); x++) {
            if (vBfps.hasPathTo(x) && wBfps.hasPathTo(x)) {
                ancestors.add(x);
            }
        }

        for (Integer x : ancestors) {
            int dist = vBfps.distTo(x) + wBfps.distTo(x);
            if (championMinDist > dist) {
                championMinDist = dist;
                ancestor = x;
            }
        }

        return ancestor;
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
