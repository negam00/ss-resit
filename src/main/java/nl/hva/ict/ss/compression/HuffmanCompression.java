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

        int a=0;
        //todo gemaakte verandering: for loop = while loop, int a=0;
         while(a < maxAscii){
             letterCount[a] = 0;
             a++;
         }

    //todo gemaakte verandering: for loop = while loop, int b=0, int currentChar, ;
        int b =0;
         int currentChar;
         while(b < text.length()){
            currentChar = text.charAt(b);
             b++;
             letterCount[currentChar]++;
         }


        //todo gemaakte verandering: for loop = while loop, int d=0 if(requisites omgedraaid qua volgorde). ;
        int c = 0;
        while (c < maxAscii) {
            if(0 < letterCount[c] ){
            ++charsInText;
            }
            c++;
        }





            //todo while loop gemaakt
        //todo: change names, change ifloop 2

            for (int t = 0; t < maxAscii; t++) {
                int biggestNumber = 0;
                int biggestIndex = 0;
                int r = 0;
//                int g =0;
                while(r < maxAscii){
                    if (letterCount[r] > biggestNumber) {
                        biggestIndex = r;
                        biggestNumber = letterCount[r];
                    }
                    r++;
                }

                 if (biggestNumber > 0) {
                    nodelist.add(new Node(letterCount[biggestIndex], (char) biggestIndex));
                    letterCount[biggestIndex] = 0;
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
    //todo gemaakte veranderingen: For loop = while loop, int i=0; boven aangemaakt.
    String[] getCodes() {
        ArrayList<String> nodeCodes = createCodeList(getCompressionTree(), new StringBuilder());
        String codes[] = new String[nodeCodes.size()];
        int i =0;
        while(i < nodeCodes.size()){
            codes[i] = nodeCodes.get(i);
            i++;
        }
        return codes;
    }
}
