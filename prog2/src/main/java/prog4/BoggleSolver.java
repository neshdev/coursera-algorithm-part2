package prog4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;




public class BoggleSolver
{
    private static class Trie<Value>
    {
        private static final int R = 256;
        
        private static class Node
        {
            private Object val;
            private Node[] next = new Node[R];
        }
        
        public Trie(){
            
        }
        
        private Node root;
        
        public void put(String key, Value value){
            root = put(root, key, value, 0 );
        }
        
        private Node put(Node x, String key, Value val, int d){
            if ( x == null) { x = new Node();}
            if ( d == key.length()) { x.val = val; return x; }
            char c = key.charAt(d);
            x.next[c] = put(x.next[c], key, val, d+1);
            return x;
        }
        
        public boolean contains(String key){
            return get(key) != null;
        }
        
        public Value get(String key){
            Node x = get(root, key, 0);
            if (x == null) return null;
            return (Value)x.val;
        }
        
        private Node get(Node x, String key, int d){
            if ( x == null) return x;
            if ( d == key.length() ) return x;
            char c = key.charAt(d);
            return get(x.next[c], key, d+1);
        }
        
        public boolean matchPrefix(String prefix){
            Node x = get(root, prefix, 0);
            return ( x != null); 
        }
    }
    
    
    private Trie<Integer> words;

    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary)
    {
        this.words = new Trie<Integer>();
        for (int i = 0; i < dictionary.length; i++) {
            String word = dictionary[i];
            words.put(word, score(word));
        }
    }

    private boolean[] marked;

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board)
    {
        SET<String> q = new SET<String>();

        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                marked = new boolean[board.rows() * board.cols()];
                int v = convertToV(i, j, board);
                //System.out.println(v);
                dfs(v, board, getLetter(v, board), q);
            }
        }

        return q;
    }

    // i - rows
    // j - cols
    // returns v - the vertex
    private int convertToV(int i, int j, BoggleBoard board)
    {
        int v = i * board.rows() + j;
        return v;
    }

    //  v - the vertex
    //
    // return i - rows;
    private int convertToI(int v, BoggleBoard board)
    {
        return v / board.cols();
    }

    // v - the vertex
    //
    // return j - column
    private int convertToJ(int v, BoggleBoard board)
    {
        return v % board.cols();
    }

    private String getLetter(int v, BoggleBoard board)
    {
        int i = convertToI(v, board);
        int j = convertToJ(v, board);
        char letter = board.getLetter(i, j);
        if ( letter == 'Q') {
            return "QU";
        }
        else return Character.toString(letter);
    }

    private Iterable<Integer> adj(int v, BoggleBoard board)
    {
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
        if (v == 0) {
            q.enqueue(right);
            q.enqueue(bottom);
            q.enqueue(bottomRight);
        }
        else if (v == (c - 1)) {
            q.enqueue(left);
            q.enqueue(bottomLeft);
            q.enqueue(bottom);
        }
        else if (v == ((c * r - c))) {
            q.enqueue(top);
            q.enqueue(topRight);
            q.enqueue(right);
        }
        else if (v == (c * r) - 1) {
            q.enqueue(topLeft);
            q.enqueue(top);
            q.enqueue(left);
        }
        else if ((v > 0) && (v < (c - 1))) {
            q.enqueue(left);
            q.enqueue(right);
            q.enqueue(bottomLeft);
            q.enqueue(bottom);
            q.enqueue(bottomRight);
        }
        else if (v > ((c * r - c) - 1) && v < (c * r) - 1) {
            q.enqueue(topLeft);
            q.enqueue(top);
            q.enqueue(topRight);
            q.enqueue(left);
            q.enqueue(right);
        }
        else if (v % c == (c - 1)) {
            q.enqueue(topLeft);
            q.enqueue(top);
            q.enqueue(left);
            q.enqueue(bottomLeft);
            q.enqueue(bottom);
        }
        else if (v % c == 0) {
            q.enqueue(top);
            q.enqueue(topRight);
            q.enqueue(right);
            q.enqueue(bottom);
            q.enqueue(bottomRight);
        }
        else { //good
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

    private void dfs(int v, BoggleBoard board, String prefix, SET<String> q){
        if (!words.matchPrefix(prefix)) return;
        if ( words.contains(prefix) && (prefix.length() >= 3)) q.add(prefix);
        //StdOut.println(prefix);
        marked[v] = true;
        for (Integer w : this.adj(v, board)) {
            if (!marked[w]){
                dfs(w, board, prefix + this.getLetter(w, board), q);
                marked[w] = false;
            }
        }
    }

    private int score(String word){
        int score = 0;
        switch (word.length()) {
        case 0:
        case 1:
        case 2:
            score = 0;
            break;
        case 3:
        case 4:
            score = 1;
            break;
        case 5:
            score = 2;
            break;
        case 6:
            score = 3;
            break;
        case 7:
            score = 5;
            break;
        default:
            score = 11;
            break;
        }
        return score;
    }
    
    
    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word)
    {
        if (!this.words.contains(word)) return 0;
        return score(word);
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
