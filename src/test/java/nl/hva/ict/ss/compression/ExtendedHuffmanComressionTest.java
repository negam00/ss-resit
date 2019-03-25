package nl.hva.ict.ss.compression;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ExtendedHuffmanComressionTest extends HuffmanCompressionTest {
    HuffmanCompression compressor;
//
//    @Test
//    public void checkNodeValueDistribution() {
//        compressor = new HuffmanCompression("aaabbbbccccccccccc"); // 3*valueTest, 4*b, 11*c
//        Node root = compressor.getCompressionTree();
//        assertEquals(18, root.getWeight());
//        assertEquals(3, root.getLeft().getLeft().getWeight());
//        assertEquals(4, root.getLeft().getRight().getWeight());
//        assertEquals(11, root.getRight().getWeight());
//    }
//
//    @Test //Checks whether it makes sufficient nodes for each leaf and whether middle nodes are empty
//    public void evenLeavesMeansDoubleNodes() {
//        compressor = new HuffmanCompression("aabbccddeeffgghh"); // 2-2 2-2 2-2 2-2
//        Node root = compressor.getCompressionTree();
//        assertEquals(16, root.getWeight());
//        assertEquals(2, root.getLeft().getLeft().getLeft().getWeight());
//        assertEquals(2, root.getLeft().getRight().getRight().getWeight());
//        assertEquals(2, root.getRight().getRight().getRight().getWeight());
//        assertEquals("isNull", null, root.getRight().getCharacter());
//        assertEquals("isNull", null, root.getRight().getRight().getCharacter());
//    }
//
//
//    @Test
//    public void testTree(){
//
//
//    }


    @Test
    public void valueTest() {
        compressor = new HuffmanCompression("potato");
        Node node = compressor.getCompressionTree();
        ArrayList<String> listTest = compressor.codeListBuilder(node, new StringBuilder());
        System.out.print("Results: \n");

        for (String n : compressor.codeListBuilder(node, new StringBuilder())) {
            System.out.println(n);
        }

        System.out.print("Binary Values: \n");
        System.out.println("0 : "+ listTest.get(0).substring(6));
        System.out.println("1 : "+ listTest.get(1).substring(6));
        System.out.println("2 : "+ listTest.get(2).substring(6));
        System.out.println("3 : "+ listTest.get(3).substring(6));


        assertEquals(listTest.get(0).substring(6), "0");
        assertEquals(listTest.get(1).substring(6), "100");
        assertEquals(listTest.get(2).substring(6), "101");
        assertEquals(listTest.get(3).substring(6), "11");

    }

}
