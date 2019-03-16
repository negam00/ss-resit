package nl.hva.ict.ss.compression;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Node  implements Comparable<Node> {
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

    public void write(ObjectOutputStream output) throws IOException {
    }

    public static Node read(ObjectInputStream input) throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public int compareTo(Node o) {
        return 0;
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
