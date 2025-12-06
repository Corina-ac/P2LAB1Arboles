package Model.BinaryTree;

public class Node {
    
    private Object data; // Renamed 'dato' to 'data'
    private Node left;   // Renamed 'izdo' to 'left'
    private Node right;  // Renamed 'dcho' to 'right'

    public Node(Object data) {
        this.data = data;
        this.left = null;
        this.right = null;
        System.out.println("LOG: Created new Node with data: '" + data + "'");
    }

    public Node(Node left, Object data, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
        String leftData = (left != null) ? left.getData().toString() : "null";
        String rightData = (right != null) ? right.getData().toString() : "null";
        System.out.println("LOG: Created new Node (Parent) with data: '" + data + 
                           "' linking to left: '" + leftData + 
                           "' and right: '" + rightData + "'");
    }

    public Object getData() { // Renamed 'getDato' to 'getData'
        return data;
    }

    public Node getLeftSubtree() { // Renamed 'subarbolIzdo' to 'getLeftSubtree'
        return left;
    }

    public Node getRightSubtree() { // Renamed 'sumarbolDerecho' to 'getRightSubtree'
        return right;
    }
}