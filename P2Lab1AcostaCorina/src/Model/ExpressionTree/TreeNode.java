package Model.ExpressionTree;

public class TreeNode { // Renamed 'NodoArbol' to 'TreeNode'
    
    private String data; // Renamed 'dato' to 'data'
    private TreeNode left; // Renamed 'izdo' to 'left'
    private TreeNode right; // Renamed 'dcho' to 'right'

    public TreeNode(String data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public TreeNode(String data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public String getData() { // Renamed 'getDato'
        return data;
    }

    public TreeNode getLeft() { // Renamed 'getIzdo'
        return left;
    }

    public TreeNode getRight() { // Renamed 'getDcho'
        return right;
    }
}