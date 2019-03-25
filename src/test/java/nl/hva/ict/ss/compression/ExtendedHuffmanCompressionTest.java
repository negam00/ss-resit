package nl.hva.ict.ss.compression;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ExtendedHuffmanCompressionTest extends HuffmanCompressionTest {
    HuffmanCompression compressor;

    @Test
    public void checkNodeValueDistribution() {
        compressor = new HuffmanCompression("aaabbbbccccccccccc"); // 3*codeListBinaryValueTest, 4*b, 11*c
        Node root = compressor.getCompressionTree();
        assertEquals(18, root.getWeight());
        assertEquals(3, root.getLeft().getLeft().getWeight());
        assertEquals(4, root.getLeft().getRight().getWeight());
        assertEquals(11, root.getRight().getWeight());
    }

    @Test //Checks whether it makes sufficient nodes for each leaf and whether middle nodes are empty
    public void evenLeavesMeansDoubleNodes() {
        compressor = new HuffmanCompression("aabbccddeeffgghh"); // 2-2 2-2 2-2 2-2
        Node root = compressor.getCompressionTree();
        assertEquals(16, root.getWeight());
        assertEquals(2, root.getLeft().getLeft().getLeft().getWeight());
        assertEquals(2, root.getLeft().getRight().getRight().getWeight());
        assertEquals(2, root.getRight().getRight().getRight().getWeight());
        assertEquals("isNull", null, root.getRight().getCharacter());
        assertEquals("isNull", null, root.getRight().getRight().getCharacter());
    }

    @Test
    public void codeListBinaryValueTest() {
        compressor = new HuffmanCompression("aabbccddd"); // 3*codeListBinaryValueTest, 4*b, 7*c
        Node root = compressor.getCompressionTree();
        System.out.println("READING FROM TREE");
        ArrayList<String> testListHolder = compressor.createCodeList(root, new StringBuilder());

        for (String n : compressor.createCodeList(root, new StringBuilder())) {
            System.out.println(n);
        }

        System.out.println("codeListBinaryValueTest");
        System.out.println("Index 0 = "+ testListHolder.get(0).substring(6));
        System.out.println("Index 3 = "+ testListHolder.get(3).substring(6));
        assertEquals(testListHolder.get(0).substring(6), "00");
        assertEquals(testListHolder.get(3).substring(6), "11");
    }

}
