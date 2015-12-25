public class MoveToFront {
    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode()
    {
        
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode()
    {
        
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