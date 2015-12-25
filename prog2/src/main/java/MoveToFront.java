import java.util.LinkedList;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    /**
     * apply move-to-front encoding,
     * reading from standard input and writing to standard output
     */
    public static void encode() {
        LinkedList<Character> alphabet = new LinkedList<Character>();
        for (char c = 0; c < 256; c++) {
            alphabet.addLast(c);
        }

        char c;
        while (!BinaryStdIn.isEmpty()) {
            c = BinaryStdIn.readChar();
            // Get index
            int index = alphabet.indexOf(c);
            alphabet.remove(index);

            // Output
            BinaryStdOut.write((char) index);

            // Adjust alphabet
            alphabet.addFirst(c);
        }

        BinaryStdOut.flush();
    }

    /**
     * apply move-to-front decoding,
     * reading from standard input and writing to standard output
     */
    public static void decode() {
        LinkedList<Character> alphabet = new LinkedList<Character>();
        for (char c = 0; c < 256; c++) {
            alphabet.addLast(c);
        }

        int index;
        while (!BinaryStdIn.isEmpty()) {
            index = BinaryStdIn.readChar();
            char c = alphabet.remove(index);
            BinaryStdOut.write(c);

            // Adjust alphabet
            alphabet.addFirst(c);
        }

        BinaryStdOut.flush();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args)
    {
        if ( args.length == 0) throw new IllegalArgumentException("Need to pass - or +");
        String type = args[0];
        if ( type == "+"){
            decode();
        }
        
        if ( type == "-"){
            encode();
        }
    }
    
}