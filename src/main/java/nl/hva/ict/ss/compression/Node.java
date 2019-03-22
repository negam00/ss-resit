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

    //todo gemaakt maar ja
    void getOutput(ObjectOutputStream outputStream){
        try {
            if (null == character){
                outputStream.writeObject(null);
                // todo even uitleg comment hier tussen zetten
                left.getOutput(outputStream);
                right.getOutput(outputStream);
            } else {
                outputStream.writeObject(weight);
                outputStream.writeObject(character);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // todo gedaan maar misschien nog aanpassen
    public void write(ObjectOutputStream output) throws IOException {
        getOutput(output);

        output.flush();
        output.close();

    }

    //todo gedaan maar nog even beetje namen aanpassen
    public static Node read(ObjectInputStream input) throws IOException, ClassNotFoundException {
        Object tmp = input.readObject();
        if (null == tmp) {
            return new Node(read(input), read(input));
        } else {
            Node node = new Node((int)tmp, (char) input.readObject());
            return node;
        }

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
