
package Main;
import java.util.*;
public class BinarySearchTree {
    Node root;
    
    
    public BinarySearchTree(int data){
        this.root = new Node(data);
    }
    public BinarySearchTree(){
        this.root = null;
    }
    
    
    
    public void insert(int key){
        if(root == null){              //inserting to empty Tree
            root = new Node(key);
            return;
        }
        Node parent = root;
        Node temp = root;
        
        while(temp != null){          // finding appropriate parent for new node
           if(temp.data < key){
               parent = temp;
               temp = parent.right;
           }else if(temp.data > key ){
               parent = temp;
               temp = parent.left;
           }else{
               return;
           }
        }
        
        if(parent.data < key){                //inserting new node into Tree 
            parent.right = new Node(key);
        }else{
            parent.left = new Node(key);
        }
        
    }
    
    
    public Node search(int key){
        Node temp = root;
        while(temp != null){
            if(temp.data < key){
                temp = temp.right;
            }else if(temp.data > key){
                temp = temp.left;
            }else{
                return temp;
            }
        }
        return null;
    }
    
    public void delete(int key){
        if(root == null){
            return;
        }
        Node temp = root;
        Node parent = root;
        while(temp != null){          
           if(temp.data < key){
               parent = temp;
               temp = parent.right;
           }else if(temp.data > key ){
               parent = temp;
               temp = parent.left;
           }else{
               if(temp.left == null & temp.right == null){
                   deleteIfNoChild(parent,temp);
                   return;
               }else if(temp.left !=null & temp.right != null){
                   deleteIfBothChild(parent,temp);
                   return;
               }else{
                   deleteIfOneChild(parent,temp);
                   return;
               }
           }
        }
    }
    public void deleteIfNoChild(Node parent, Node temp){
        if(temp.data != root.data){
            if(parent.right == temp){
                parent.right = null;
            }else{
                parent.left = null;
            }
        }else{
            root = null;
        }
    }
    
    public void deleteIfOneChild(Node parent,Node temp){
        if(temp.data != root.data){
            if(parent.left == temp){
                if(temp.left != null){
                    parent.left = temp.left;
                }else{
                    parent.left = temp.right;
                }
            }else{
                if(temp.left != null){
                    parent.right = temp.left;
                }else{
                    parent.right = temp.right;
                }
            }
        }else{
            root = null;
        }
    }
    
    public void deleteIfBothChild(Node parent, Node temp){
        int data = inSucc(temp);
        delete(data);
        temp.data = data;
    }
    
    public int inSucc(Node n){
        Node temp = n.right;
        while(temp.left != null){
            temp = temp.left;
        }
        return temp.data;
        
    }
    
    public void inorderTraversal(){
        inorderTraversalHelper(root);
        System.out.println("");
    }
    public void inorderTraversalHelper(Node root){
        if (root != null) {
            inorderTraversalHelper(root.left);
            System.out.print(root + " ");
            inorderTraversalHelper(root.right);
        }
    }
    
    
    public void preorderTraversal(){
        preorderTraversalHelper(root);
        System.out.println("");
    }
    public void preorderTraversalHelper(Node root){
        if(root != null){
            System.out.print(root + " ");
            preorderTraversalHelper(root.left);
            preorderTraversalHelper(root.right);
        }
    }
    
    
    public void postorderTraversal(){
        postorderTraversalHelper(root);
        System.out.println("");
    }
    public void postorderTraversalHelper(Node root){
        if(root != null){
            postorderTraversalHelper(root.left);
            postorderTraversalHelper(root.right);
            System.out.print(root + " ");
        }
    }
    
    
    private int getHeight(Node node){
        if(node == null){
            return -1;
        }
        
        int left_height = getHeight(node.left);
        int right_height = getHeight(node.right);
        
        return 1 + Math.max(left_height,right_height);    
    }
    
    public int displayHeight(Node node){
        return getHeight(node);
    }
    
    private int getImbalanceFactor(Node node){
        return getHeight(node.left) - getHeight(node.right);
    }
    
    public void findImbalancedNode(){
        if (root != null) {
            inorderTraversalHelper(root.left);
            if(Math.abs(getImbalanceFactor(root)) >= 2){
                 System.out.println(root);
            }
            inorderTraversalHelper(root.right);
        }
    }
    
    
    public void rebalance() {
        List<Integer> sortedValues = new ArrayList<>();
        inorderCollect(root, sortedValues);  
        root = buildBalancedBST(sortedValues, 0, sortedValues.size() - 1);  
    }

    private void inorderCollect(Node node, List<Integer> list) {
        if (node != null) {
            inorderCollect(node.left, list);
            list.add(node.data);
            inorderCollect(node.right, list);
        }
    }
    private Node buildBalancedBST(List<Integer> list, int start, int end) {
        if (start > end) return null;

        int mid = (start + end) / 2;
        Node node = new Node(list.get(mid));
        node.left = buildBalancedBST(list, start, mid - 1);
        node.right = buildBalancedBST(list, mid + 1, end);

        return node;
    }
    
    
            
}
