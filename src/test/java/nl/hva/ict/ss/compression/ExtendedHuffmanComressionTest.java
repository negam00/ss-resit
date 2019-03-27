package nl.hva.ict.ss.compression;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ExtendedHuffmanComressionTest extends HuffmanCompressionTest {
    HuffmanCompression huffmanCompression;

    @Test
    public void nodeWeightTest() {
        compressor = new HuffmanCompression("ttoozzzzlll"); // 2*t, 4*z 3*l 2* o = 1+4+3+2= 11
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
        System.out.print("Results: \n");

        for (String n : huffmanCompression.codeListBuilder(node, new StringBuilder())) {
            System.out.println(n);
        }

        System.out.print("Binary Values: \n");
        System.out.println("0 : "+ listTest.get(0).substring(6));
        System.out.println("1 : "+ listTest.get(1).substring(6));
        System.out.println("2 : "+ listTest.get(2).substring(6));
        System.out.println("3 : "+ listTest.get(3).substring(6));


        assertEquals(listTest.get(0).substring(6), "00");
        assertEquals(listTest.get(1).substring(6), "01");
        assertEquals(listTest.get(2).substring(6), "10");
        assertEquals(listTest.get(3).substring(6), "11");

    }

//editing

////todo ask what this does.
//    @Test
//    public void distributionTest(){
//        huffmanCompression = new HuffmanCompression("spudow");
//        Node node = huffmanCompression.getCompressionTree();
//
//        System.out.println("SPUDOW: " + node.getWeight());
//        System.out.println("SPUDOW: " + node.getLeft().getWeight());
//        System.out.println("SPUDOW: " + node.getLeft().getLeft().getWeight());
//        System.out.println("SPUDOW: " + node.getLeft().getRight().getWeight());
//
//    }


//
//    @Test //Checks whether it makes sufficient nodes for each leaf and whether middle nodes are empty
//    public void evenLeavesMeansDoubleNodes() {
//        huffmanCompression = new HuffmanCompression("aabbccddeeffgghh"); // 2-2 2-2 2-2 2-2
//        Node root = huffmanCompression.getCompressionTree();
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



}
