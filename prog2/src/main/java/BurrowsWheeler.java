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
            int index = csa.index(i);
            if ( index == 0) {
                BinaryStdOut.write(index);
                break;
            }
        }
        
        for (int i = 0; i < N; i++) {            
            int index = csa.index(i);
            int charIndex = index - 1;
            if (charIndex != -1){
                char c = s.charAt(charIndex);
                BinaryStdOut.write( c);
            } else {
                char c = s.charAt(N - 1);
                BinaryStdOut.write( c);
            }
        }
        
    }

    // apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
    public static void decode()
    {
        
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