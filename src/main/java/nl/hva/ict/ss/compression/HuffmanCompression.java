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
        Node root = getCompressionTree();
        int originalBits = root.getWeight() * 8;// original would be 8 bits per char = total occurrences *8
        int totalShortenedChar = 0;

        ArrayList<String> nodeCodes = createCodeList(root, new StringBuilder());
        ArrayList<Node> charsWithWeight = createNodeList();

        for (Node node : charsWithWeight) {
            for (String nodeCodeString : nodeCodes) {
                if (nodeCodeString.charAt(1) == node.getCharacter()) {
                    totalShortenedChar += node.getWeight() * (nodeCodeString.length() - 6);
                }
            }
        }
        System.out.println("Original amount of bits: " + originalBits);
        System.out.println("Shortened amount of bits: " + totalShortenedChar);
        return (double) totalShortenedChar / (double) originalBits;
    }
    Node getCompressionTree() {
        ArrayList<Node> nodeList = createNodeList();
        while (nodeList.size() > 2) {
            Node node1 = nodeList.get(nodeList.size() - 1);
            nodeList.remove(nodeList.get(nodeList.size() - 1));
            Node node2 = nodeList.get(nodeList.size() - 1);
            nodeList.remove(nodeList.get(nodeList.size() - 1));
            nodeList.add(new Node(node1, node2));
            Collections.sort(nodeList, Collections.reverseOrder());
        }
        Node root = new Node(nodeList.get(nodeList.size() - 1), nodeList.get(nodeList.size() - 2));
        return root;
    }



    //todo mijn code begint hier

    ArrayList<Node> createNodeList() {
        ArrayList<Node> nodelist = new ArrayList<>();
        int maxAscii = 128;
        int charsInText = 0;
        int letterCount[] = new int[maxAscii];
        for (int i = 0; i < maxAscii; i++) {
            letterCount[i] = 0;
        }
        for (int i = 0; i < text.length(); i++) {
            int currentChar = text.charAt(i);
            letterCount[currentChar]++;
        }
        for (int i = 0; i < maxAscii; i++) {
            if (letterCount[i] > 0) {
                ++charsInText;
            }
        }
        for (Integer c = 0; c <= charsInText; c++) {
            {
                int biggestNumber = 0;
                int biggestIndex = 0;
                for (int i = 0; i < maxAscii; i++) {
                    if (letterCount[i] > biggestNumber) {
                        biggestIndex = i;
                        biggestNumber = letterCount[i];
                    }
                }
                if (biggestNumber > 0) {
                    nodelist.add(new Node(letterCount[biggestIndex], (char) biggestIndex));
                    letterCount[biggestIndex] = 0;
                }
            }
        }
        Collections.sort(nodelist, Collections.reverseOrder());
        return nodelist;
    }


    //todo

    ArrayList<String> createCodeList(Node node, StringBuilder str) {
        ArrayList<String> addingList = new ArrayList<>();
        StringBuilder leftString = new StringBuilder(str.toString());
        StringBuilder rightString = new StringBuilder(str.toString());
        if (node.getCharacter() != null) {
            addingList.add("'" + node.getCharacter() + "'" + "-> " + str);
        } else {
            for (String s : createCodeList(node.getLeft(), leftString.append("0"))) {
                addingList.add(s);
            }
            for (String s : createCodeList(node.getRight(), rightString.append("1"))) {
                addingList.add(s);
            }
        }
        return addingList;
    }






    //todo
    String[] getCodes() {
        ArrayList<String> nodeCodes = createCodeList(getCompressionTree(), new StringBuilder());
        String codes[] = new String[nodeCodes.size()];
        for (int i = 0; i < nodeCodes.size(); i++) {
            codes[i] = nodeCodes.get(i);
        }
        return codes;
    }
}
