package nl.hva.ict.ss.compression;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Node implements Comparable<Node> {
    private Node left;
    private Node right;
    private int weight;
    private Character character;

    public Node(int weight, Character c) {
        this.weight = weight;
        this.character = c;
    }

    public Node(Node left, Node right) {
        this.weight = left.weight + right.weight;
        this.left = left;
        this.right = right;
    }

    private void getOutput(ObjectOutputStream outputStream){
        try {
            if (null == character){ // check if there are any characters
                outputStream.writeObject(null);
                // left and right receive weight and character

                left.getOutput(outputStream);
                right.getOutput(outputStream);
            } else {
                // null node is given to outputstream
                outputStream.writeObject(weight);
                outputStream.writeObject(character);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void output(ObjectOutputStream output) throws IOException {
        getOutput(output);
        // close the output
        output.flush();
        output.close();
    }

    public static Node read(ObjectInputStream input) throws IOException, ClassNotFoundException {
        Object tmp = input.readObject();
        if (null == tmp) {
            // check if data is null or not
            return new Node(read(input), read(input));
        } else {
            Node node = new Node((int)tmp, (char) input.readObject());
            return node;
        }

    }


    @Override
    public int compareTo(Node o) {
        return weight - o.getWeight();
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getWeight() {
        return weight;
    }

    public Character getCharacter() {
        return character;
    }

}
