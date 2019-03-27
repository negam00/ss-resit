package nl.hva.ict.ss.compression;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ExtendedHuffmanComressionTest extends HuffmanCompressionTest {
    HuffmanCompression huffmanCompression;

    @Test
    public void nodeWeightTest() {
        compressor = new HuffmanCompression("ttoozzzzlll");
        Node root = compressor.getCompressionTree();
        assertEquals(11, root.getWeight());
        assertEquals(4, root.getLeft().getWeight());
        assertEquals(7, root.getRight().getWeight());
}


    @Test
    public void valueTest() {
        huffmanCompression = new HuffmanCompression("ppotato");
        Node node = huffmanCompression.getCompressionTree();
        ArrayList<String> listTest = huffmanCompression.codeListBuilder(node, new StringBuilder());

        assertEquals(listTest.get(0).substring(6), "00");
        assertEquals(listTest.get(1).substring(6), "01");
        assertEquals(listTest.get(2).substring(6), "10");
        assertEquals(listTest.get(3).substring(6), "11");

    }



}
