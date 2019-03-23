package nl.hva.ict.ss.compression;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Node implements Comparable<Node>, Serializable {
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

    public static Node read(ObjectInputStream input) throws IOException, ClassNotFoundException {
        Object temp = input.readObject();
        if (temp == null){
            return new Node(read(input),read(input));
        }
        else {
            Node newNode = new Node((int) temp, (char)input.readObject());
            return newNode;
        }
    }

    public void write(ObjectOutputStream output) throws IOException {
        createOutput(output);
        output.flush();
        output.close();
    }
    void createOutput(ObjectOutputStream output) throws IOException {
        try {
            if (character == null){
                output.writeObject(null);
                left.createOutput(output);
                right.createOutput(output);
            }
            else {
                output.writeObject(weight);
                output.writeObject(character);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
