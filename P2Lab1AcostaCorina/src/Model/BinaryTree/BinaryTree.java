package Model.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    private Node root;
    
    public BinaryTree() {
        this.root = null;
    }
    
    public Node getRoot() {
        return root;
    }
    
    public void insert(int value) {
        root = insertRec(root, value);
    }
    
    private Node insertRec(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        
        int nodeValue = Integer.parseInt(node.getData().toString());
        
        if (value < nodeValue) {
            Node leftChild = insertRec(node.getLeftSubtree(), value);
            return new Node(leftChild, nodeValue, node.getRightSubtree());
        } else if (value > nodeValue) {
            Node rightChild = insertRec(node.getRightSubtree(), value);
            return new Node(node.getLeftSubtree(), nodeValue, rightChild);
        }
        
        return node;
    }
    
    public boolean search(int value) {
        return searchRec(root, value);
    }
    
    private boolean searchRec(Node node, int value) {
        if (node == null) {
            return false;
        }
        
        int nodeValue = Integer.parseInt(node.getData().toString());
        
        if (value == nodeValue) {
            return true;
        }
        
        if (value < nodeValue) {
            return searchRec(node.getLeftSubtree(), value);
        } else {
            return searchRec(node.getRightSubtree(), value);
        }
    }
    
    public void clear() {
        root = null;
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    // Obtener el tamaño del árbol (número de nodos)
    public int size() {
        return sizeRec(root);
    }
    
    private int sizeRec(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + sizeRec(node.getLeftSubtree()) + sizeRec(node.getRightSubtree());
    }
    
    public int height() {
        return heightRec(root);
    }
    
    private int heightRec(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(heightRec(node.getLeftSubtree()), heightRec(node.getRightSubtree()));
    }
    
    public int countLeaves() {
        return countLeavesRec(root);
    }
    
    private int countLeavesRec(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.getLeftSubtree() == null && node.getRightSubtree() == null) {
            return 1;
        }
        return countLeavesRec(node.getLeftSubtree()) + countLeavesRec(node.getRightSubtree());
    }
    
    public String preorder() {
        StringBuilder sb = new StringBuilder();
        preorderRec(root, sb);
        return sb.toString().trim();
    }
    
    private void preorderRec(Node node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.getData()).append(" ");
            preorderRec(node.getLeftSubtree(), sb);
            preorderRec(node.getRightSubtree(), sb);
        }
    }
    
    public String inorder() {
        StringBuilder sb = new StringBuilder();
        inorderRec(root, sb);
        return sb.toString().trim();
    }
    
    private void inorderRec(Node node, StringBuilder sb) {
        if (node != null) {
            inorderRec(node.getLeftSubtree(), sb);
            sb.append(node.getData()).append(" ");
            inorderRec(node.getRightSubtree(), sb);
        }
    }
    
    public String postorder() {
        StringBuilder sb = new StringBuilder();
        postorderRec(root, sb);
        return sb.toString().trim();
    }
    
    private void postorderRec(Node node, StringBuilder sb) {
        if (node != null) {
            postorderRec(node.getLeftSubtree(), sb);
            postorderRec(node.getRightSubtree(), sb);
            sb.append(node.getData()).append(" ");
        }
    }
    
    public String levelOrder() {
        if (root == null) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            sb.append(current.getData()).append(" ");
            
            if (current.getLeftSubtree() != null) {
                queue.add(current.getLeftSubtree());
            }
            if (current.getRightSubtree() != null) {
                queue.add(current.getRightSubtree());
            }
        }
        
        return sb.toString().trim();
    }
}