package nl.hva.ict.ss.compression;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class HuffmanCompression {
    private final String text;

    public HuffmanCompression(String text) {
        this.text = text;
    }

    public HuffmanCompression(InputStream input) {
        Scanner sc = new Scanner(input);
        sc.useDelimiter("\\Z"); // EOF marker
        text = sc.next();
    }

    /**
     * Returns the compression ratio assuming that every characters in the text uses 8 bits.
     * @return the compression ratio.
     */ // todo aanbegonnen
    public double getCompressionRatio() {

        Node root = getCompressionTree();
        int ogBits = root.getWeight() * 8;
        int total = 0;

        ArrayList<String> node = new ArrayList<>();
//        ArrayList<Node> weightChar



        return 0.0;
    }

    /**
     * Returns the root of the compression tree.
     * @return the root of the compression tree.
     */
    Node getCompressionTree() {
        return null;
    }

    /**
     * Returns a list with the character and the code that is used to encode it.
     * The format per entry is: "'char' -> code"
     * For "aba" this would result in: ["'b' -> 0", "'a' -> 1"]
     * And for "cacbcac" this would result in: ["'b' -> 00", "'a' -> 01", "'c' -> 1"]
     * @return the Huffman codes
     */
    String[] getCodes() {
        return null;
    }

}
