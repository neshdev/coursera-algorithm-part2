import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    // apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
    public static void encode()
    {
        StringBuilder sb = new StringBuilder();
        while (!BinaryStdIn.isEmpty())
        {
            char c = BinaryStdIn.readChar();
            sb.append(c);
        }
        String s = sb.toString();
        int N = s.length();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        for (int i = 0; i < N; i++) {            
            int sortIndex = csa.index(i);
            if ( sortIndex == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }
        
        for (int i = 0; i < N; i++) {            
            int sortIndex = csa.index(i);
            int charIndex = sortIndex - 1;
            if (charIndex != -1){
                char c = s.charAt(charIndex);
                BinaryStdOut.write(c);
            } else {
                char c = s.charAt(N - 1);
                BinaryStdOut.write(c);
            }
        }
        
        BinaryStdOut.flush();
        
    }

    // apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
    public static void decode()
    {
        int first = BinaryStdIn.readInt();
        String s = BinaryStdIn.readString();
                
        char[] tail = s.toCharArray();
        char[] head = s.toCharArray();
        Arrays.sort(head);
        int[] next = next(tail, head);

        int index = first;
        for (char ignored : tail) {
            BinaryStdOut.write(head[index]);
            index = next[index];
        }

        BinaryStdOut.flush();
        
    }
    
    private static int R = 256;
    
    private static int[] next(char[] tail, char[] head){
        int[] searchStart = new int[R];

        int[] next = new int[tail.length];
        for (int i = 0; i < tail.length; i++) {
            char c = tail[i];
            int index = search(head, searchStart[c], c);
            searchStart[c] = index + 1;
            next[index] = i;
        }

        return next;
    }
    
    private static int search(char[] characters, int from, char c) {
        for (int i = from; i < characters.length; i++) {
            if (characters[i] == c) {
                return i;
            }
        }
        return -1;
    }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args)
    {
        if ( args.length == 0) throw new IllegalArgumentException("args should be greater than 1");
        String type = args[0];
        if ( type == "+"){
            decode();
        }
        
        if ( type == "-"){
            encode();
        }
    }
}