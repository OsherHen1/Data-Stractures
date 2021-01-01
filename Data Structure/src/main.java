import java.util.*;
import java.util.LinkedList;

public class main {
    public static void main(String[] args) {
        AVLTree a = new AVLTree();
        a.insert(10);
        a.insert(20);
        a.insert(5);
//        a.insert(7);
//        a.insert(4);
        //a.insert(15);
        a.delete(10);
        System.out.println("root: "+a.getRoot().getData());
        a.printTreeInOrder();
        System.out.println(a.getRoot().getPredecessor().getData());
    }
}