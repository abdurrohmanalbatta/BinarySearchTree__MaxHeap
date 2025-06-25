package Main;
import java.util.*;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Random rand = new Random();
        Set<Integer> uniqueValues = new HashSet<>();


        while (uniqueValues.size() < 20) {
            int num = rand.nextInt(100); 
            if (uniqueValues.add(num)) {
                bst.insert(num);
            }
        }

        bst.rebalance();


        for (int i = 0; i < 10; i++) {
            int num = rand.nextInt(100);
            bst.insert(num);
            bst.rebalance();
        }


        for (int i = 0; i < 10; i++) {
            int num = rand.nextInt(100);
            bst.delete(num);
            bst.rebalance();
        }

        System.out.println("\nIn-order Traversal:");
        bst.inorderTraversal();

        System.out.println("\nPre-order Traversal:");
        bst.preorderTraversal();

        System.out.println("\nPost-order Traversal:");
        bst.postorderTraversal();

        int finalHeight = bst.displayHeight(bst.root);
        System.out.println("\nHeight of final tree: " + finalHeight);
        

        System.out.println("");
        System.out.println("Problem2:");   
        System.out.println("");

        
        
        MaxHeap heap = new MaxHeap();
        Random rand2 = new Random(20);
        for (int i = 0; i < 20; i++) {
            int priority = rand2.nextInt(100) + 1;
            int lifespan = rand2.nextInt(5) + 1;
            heap.insert(priority, lifespan);
        }
        
        for (int i = 0; i < 10; i++) {
            Event max = heap.extractMax();
            if (max != null) {
                System.out.println("Ectracted event " + max);
                heap.displayHeap();
                System.out.println();
            }
        }
        
          
          
    
    }
}
