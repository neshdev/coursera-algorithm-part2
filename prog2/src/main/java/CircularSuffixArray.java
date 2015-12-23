import java.util.Arrays;

public class CircularSuffixArray
{
    
    private static class Node implements Comparable<Node>
    {
        private Node next;
        private int index;
        private char key;
        
        public int compareTo(Node o)
        {
            if ( this.key > o.key) return 1;
            else if ( this.key < o.key) return -1;
            else {
                int returnCode = 0;
                Node root = this;
                
                Node comp = o.next;
                Node cur = this.next;
                
                
                while(true){
                    if ( cur.key > comp.key ){
                        returnCode = 1;
                        break;
                    }
                    
                    if ( cur.key < comp.key ){
                        returnCode = -1;
                        break;
                    }
                    
                    if ( root.equals(cur))
                        break;
                    
                    comp = comp.next;
                    cur = cur.next;
                }
                return returnCode;
            }
        }
    }
        
    private int length;
    private int[] indexes;
    
    // circular suffix array of s
    public CircularSuffixArray(String s)
    {
        if ( s == null) throw new NullPointerException("argument s is null");
        this.length = s.length();
        Node[] nodes = new Node[this.length];
        indexes = new int[this.length];

        Node root = new Node();
        Node x = root;
        for (int i = 0; i < this.length - 1; i++) {
            nodes[i] = x;
            x.key = s.charAt(i);
            x.index = i;
            x.next = new Node();
            x = x.next;
        }
        
        for (int i = this.length - 1; i < this.length; i++) {
            nodes[i] = x;
            x.key = s.charAt(i);
            x.index = i;
            x.next = root;
        }
        
        Arrays.sort(nodes);
        for (int i = 0; i < nodes.length; i++) {
            indexes[i] = nodes[i].index;
        }
        
    }

    // length of s
    public int length()
    {
        return length;
    }

    // returns index of ith sorted suffix
    public int index(int i)
    {
        if (( 0 > i ) || ( i >= this.length())) throw new IndexOutOfBoundsException();
        return indexes[i];
    }

    // unit testing of the methods (optional)
    public static void main(String[] args)
    {
        new CircularSuffixArray("AAA");
    }
}
