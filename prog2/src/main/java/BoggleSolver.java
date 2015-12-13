import java.util.Iterator;
import java.util.Set;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;


class DirectedDFS1{
    
    private boolean[] marked;
    private int[] edgeTo;
    
    public DirectedDFS1(DirectedGraph1 G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        dfs(G, s);
    }
    
    private void dfs(DirectedGraph1 g, int v){
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]){
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }
}


class DirectedGraph1
{
    private Bag<Integer>[] adj;
    private int e;
    private int v;
    
    public DirectedGraph1(int V){
        this.v = V;
        this.e = 0;
        
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    
    public void addEdge(int v, int w){
        adj[v].add(w);
        e++;
    }
    
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
    
    
    public int E(){
        return e;
    }
    
    public int V(){
        return v;
    }
    
    public Iterable<Integer> Edges(){
        Queue<Integer> q = new Queue<Integer>();
        for (int i = 0; i < adj.length; i++) {
            for (Integer v : adj[i]) {
                q.enqueue(v);
            }
        }
        return q;
    }
}

public class BoggleSolver
{
    private SET<String> words;
    
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary)
    {
        this.words = new SET<String>();
        for (int i = 0; i < dictionary.length; i++) {
            words.add(dictionary[i]);
        }
    }

    
    private boolean[][] marked;
    
    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board)
    {
        marked = new boolean[board.rows()][board.cols()];
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                
            }
        }
        
        
        throw new NullPointerException();
    }
    
    // i - rows
    // j - cols
    // returns v - the vertex
    public static int convertToV(int i, int j, BoggleBoard board){
        int v = i * board.rows() + j;
        return v;
    }
    
    //  v - the vertex
    //
    // return i - rows;
    public static int convertToI(int v, BoggleBoard board){
        return v / board.rows();
    }
    
    // v - the vertex
    //
    // return j - column
    public static int convertToJ(int v, BoggleBoard board){
        return v % board.rows();
    }
    
    public static Iterable<Integer> adj(int v, BoggleBoard board){
        Queue<Integer> q = new Queue<Integer>();
        
        int topLeft = v - board.cols() - 1;
        int top = v - board.cols();
        int topRight = v - board.cols() + 1;
        
        int left = v - 1;
        int right = v + 1;
        
        int bottomLeft = v + board.cols() - 1;
        int bottom = v + board.cols();
        int bottomRight = v + board.cols() + 1;
        
        int r = board.rows();
        int c = board.cols();
        if (v == 0){ //good
            q.enqueue(right);
            q.enqueue(bottom);
            q.enqueue(bottomRight);
        } else if ( v == (c - 1) ){
            q.enqueue(left);
            q.enqueue(bottomLeft);
            q.enqueue(bottom);
        } else if ( v == ((c * r - c)) ){
            q.enqueue(top);
            q.enqueue(topRight);
            q.enqueue(right);
        } else if ( v == (c*r) - 1){ //good
            q.enqueue(top);
            q.enqueue(topLeft);
            q.enqueue(left);
        } else if ( (v > 0) && (v < (c - 1)) ){ //good
            q.enqueue(left);
            q.enqueue(right);
            q.enqueue(bottomLeft);
            q.enqueue(bottom);
            q.enqueue(bottomRight);
        } else if ( v > ((c * r - c) - 1) && v < (c*r) - 1  ){
            q.enqueue(topLeft);
            q.enqueue(top);
            q.enqueue(topRight);
            q.enqueue(left);
            q.enqueue(right);
        } else if ( v % c == (c - 1)){ //good
            q.enqueue(topLeft);
            q.enqueue(top);
            q.enqueue(left);
            q.enqueue(bottomLeft);
            q.enqueue(bottom);
        } else if ( v % c == 0){ //good
            q.enqueue(top);
            q.enqueue(topRight);
            q.enqueue(right);
            q.enqueue(bottom);
            q.enqueue(bottomRight); 
        } else { //good
            q.enqueue(topLeft);
            q.enqueue(top);
            q.enqueue(topRight);
            q.enqueue(left);
            q.enqueue(right);
            q.enqueue(bottomLeft);
            q.enqueue(bottom);
            q.enqueue(bottomRight);
        }
        
        return q;
    }
    
    private void dfs(int i, int j, BoggleBoard board){
        if (!marked[i][j]){
            if ( i == 0) {
                
            }
            
            if ( i == (board.rows() - 1)){
                
            }
            
            if ( j == 0){
                
            }
            
            if ( j == (board.cols() - 1) ){
                
            }
        }
    }
    

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word)
    {
        throw new NullPointerException();
    }
    
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board))
        {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }
}