package nl.hva.ict.ss.compression;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
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
     *
     * @return the compression ratio.
     */
    public double getCompressionRatio() {
        Node top = getCompressionTree();

        int total = 0;
        int og = top.getWeight() * 8;
        ArrayList<Node> cWeight = nodeListBuilder();
        ArrayList<String> codes = codeListBuilder(top, new StringBuilder());

        for (Node node : cWeight) {
            //
            for (String nodeCodeString : codes) {

                if (nodeCodeString.charAt(1) == node.getCharacter()) {
                    total += node.getWeight() * (nodeCodeString.length() - 6);
                }
            }
        }
        System.out.println("Bits before " + og + "\nBits after " + total);
        return (double) total / (double) og;
    }

    Node getCompressionTree() {
        ArrayList<Node> nodes = nodeListBuilder();
        while ( 2 <nodes.size()) {
            //n1 is removed
            Node n1 = nodes.get(nodes.size() - 1);
            nodes.remove(nodes.get(nodes.size() - 1));
            // n2 is removed
            Node n2 = nodes.get(nodes.size() - 1);
            nodes.remove(nodes.get(nodes.size() - 1));


            //add the old nodes on new spot
            nodes.add(new Node(n1, n2));
            Collections.sort(nodes, Collections.reverseOrder());
        }
        Node top = new Node(nodes.get(nodes.size() - 1), nodes.get(nodes.size() - 2));
        return top;
    }



    ArrayList<Node> nodeListBuilder() {
        ArrayList<Node> listNode = new ArrayList<>();

        int asc = 128;
        int occurence = 0;
        int countArr[] = new int[asc];

        int a = 0;
        while (a < asc) {
            countArr[a] = 0;
            a++;
        }

        int b = 0;
        int charAt;
        while (b < text.length()) {
            charAt = text.charAt(b);
            b++;
            countArr[charAt]++;
        }
        int c = 0;
        while (c < asc) {
            if (0 < countArr[c]) {
                ++occurence;
            }
            c++;
        }
        for (int t = 0; t < asc; t++) {
            int maxNum = 0;
            int maxPointer = 0;
            int r = 0;

            while (r < asc) {
                if (countArr[r] > maxNum) {
                    maxPointer = r;
                    maxNum = countArr[r];
                }
                r++;
            }
            if (0 < maxNum) {
                listNode.add(new Node(countArr[maxPointer],
                        (char) maxPointer));
                countArr[maxPointer] = 0;
            }
        }
        listNode.sort(Collections.reverseOrder());
        return listNode;
    }


    ArrayList<String> codeListBuilder(Node node, StringBuilder str) {
        StringBuilder buildRight = new StringBuilder(str.toString());
        StringBuilder buildLeft = new StringBuilder(str.toString() );
        ArrayList<String> buildList = new ArrayList<>();

        if (null != node.getCharacter()) {
            buildList.add("'" + node.getCharacter() + "'" + "-> " + str);
        } else {
            buildList.addAll(codeListBuilder(node.getLeft(), buildLeft.append("0")));
            buildList.addAll(codeListBuilder(node.getRight(), buildRight.append("1")));
        }
        return buildList;
    }



    /**
     * Returns a list with the character and the code that is used to encode it.
     * The format per entry is: "'char' -> code"
     * For "aba" this would result in: ["'b' -> 0", "'a' -> 1"]
     * And for "cacbcac" this would result in: ["'b' -> 00", "'a' -> 01", "'c' -> 1"]
     * @return the Huffman codes
     */
    String[] getCodes () {
        ArrayList<String> charNodeHolder = codeListBuilder(getCompressionTree(),
                new StringBuilder());
        String codeString[] = new String[charNodeHolder.size()];
        int i = 0;
        while (i < charNodeHolder.size()) {
            codeString[i] = charNodeHolder.get(i);
            i++;
        }
        return codeString;
    }
}
