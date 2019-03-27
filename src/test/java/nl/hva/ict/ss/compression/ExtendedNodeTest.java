package nl.hva.ict.ss.compression;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ExtendedNodeTest extends NodeTest {

    @Test
    public void OutputStreamIsPreOrder() throws IOException {
        System.out.println("test");
        compressor = new HuffmanCompression("ddyyyyyttt");
        Node root = compressor.getCompressionTree();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(("huffman-tree.bin")))) {
            root.write(objectOutputStream);
        }
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(("huffman-tree.bin")))) {
            assertNull(input.readObject()); //a
            assertNull(input.readObject()); //a
            assertEquals(2, (input.readObject())); //1
            assertEquals("d", (input.readObject()).toString()); //a
            assertEquals(3, (input.readObject())); //b
            assertEquals("t", (input.readObject()).toString()); //2
            assertEquals(5, (input.readObject())); //6
            assertEquals("y", (input.readObject()).toString()); //2
        } catch (ClassNotFoundException e) {
            System.out.println("erorr");
        }
    }
}