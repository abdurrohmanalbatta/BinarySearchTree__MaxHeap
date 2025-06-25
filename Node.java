package Main;
 
public class Node {
    public int data;
    public Node left;
    public Node right;
    
    public Node(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
    @Override
    public String toString(){
        return "" + this.data;
    }
    
}
