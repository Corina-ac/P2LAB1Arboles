package Model.ExpressionTree;

import java.util.Stack;

public class ExpressionTree { 
    
    private TreeNode root; 
    
    public ExpressionTree() {
        this.root = null;
    }

    public TreeNode getRoot() {
        return root;
    }
    
    public void buildTree(String infixExpression) { 
        String[] tokens = infixExpression.split("(?<=[-+*/^()])|(?=[-+*/^()])");
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>(); 
        for (String token : tokens) {
            token = token.trim();
            if (token.isEmpty()) continue;

            if (!ExpressionUtils.isOperator(token) && !token.equals("(") && !token.equals(")")) {
                nodeStack.push(new TreeNode(token));
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    processOperator(nodeStack, operatorStack); 
                }
                if (!operatorStack.isEmpty()) {
                    operatorStack.pop();
                }
            } else if (ExpressionUtils.isOperator(token)) {
                while (!operatorStack.isEmpty() && 
                       !operatorStack.peek().equals("(") && 
                       ExpressionUtils.precedence(operatorStack.peek()) >= ExpressionUtils.precedence(token)) {
                    processOperator(nodeStack, operatorStack);
                }
                operatorStack.push(token);
            }
        }

        while (!operatorStack.isEmpty()) {
            processOperator(nodeStack, operatorStack);
        }

        if (!nodeStack.isEmpty()) {
            this.root = nodeStack.pop();
        }
    }

    private void processOperator(Stack<TreeNode> nodeStack, Stack<String> operatorStack) { 
        String op = operatorStack.pop();
        TreeNode right = nodeStack.pop(); 
        TreeNode left = nodeStack.pop(); 
        TreeNode newNode = new TreeNode(op, left, right); 
        nodeStack.push(newNode);
    }
    
    public String inorderTraversal() { 
        return "INORDER (Infix Expression): " + inorder(root); 
    }
    
    private String inorder(TreeNode node) {
        if (node == null) {
            return "";
        }
        
        boolean isOperator = ExpressionUtils.isOperator(node.getData()); 
        String result = ""; 
        
        if (isOperator) result += "(";
        
        result += inorder(node.getLeft()); 
        result += node.getData(); 
        result += inorder(node.getRight()); 
        
        if (isOperator) result += ")";
        
        return result;
    }

    public String postorderTraversal() { 
        return "POSTORDER (Evaluation): " + postorder(root); 
    }
    
    private String postorder(TreeNode node) { 
        if (node == null) {
            return "";
        }
        return postorder(node.getLeft()) + " " + postorder(node.getRight()) + " " + node.getData(); 
    }

    public String preorderTraversal() {
        return "PREORDER (Polish Notation): " + preorder(root); 
    }
    
    private String preorder(TreeNode node) { 
        if (node == null) {
            return "";
        }
        return node.getData() + " " + preorder(node.getLeft()) + " " + preorder(node.getRight()); 
    }
    
    public void printTreeHierarchy() { 
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║    VISUAL TREE HIERARCHY                   ║"); 
        System.out.println("╚════════════════════════════════════════════╝\n");
        if (root != null) {
            printVisualTree(root, "", true); 
        } else {
            System.out.println("The tree is empty."); 
        }
    }

    private void printVisualTree(TreeNode node, String prefix, boolean isLast) { 
        if (node == null) {
            return;
        }

        System.out.print(prefix);
        System.out.print(isLast ? "└── " : "├── ");
        
        String type = ExpressionUtils.isOperator(node.getData()) ? "OP" : "VAL"; 
        System.out.println("[" + node.getData() + "] (" + type + ")");

        String parentPrefix = prefix + (isLast ? "    " : "│   ");

        if (node.getLeft() != null || node.getRight() != null) {
            if (node.getRight() != null) {
                printVisualTree(node.getRight(), parentPrefix, node.getLeft() == null);
            }
            if (node.getLeft() != null) {
                printVisualTree(node.getLeft(), parentPrefix, true);
            }
        }
    }

    public void printHorizontalHierarchy() { 
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║   HORIZONTAL TREE VISUALIZATION            ║"); 
        System.out.println("╚════════════════════════════════════════════╝\n");
        if (root != null) {
            printHorizontal(root, 0, "ROOT"); 
        } else {
            System.out.println("The tree is empty."); 
        }
    }

    private void printHorizontal(TreeNode node, int level, String position) { 
        if (node == null) {
            return;
        }

        if (node.getRight() != null) {
            printHorizontal(node.getRight(), level + 1, "RIGHT"); 
        }

        for (int i = 0; i < level; i++) {
            System.out.print("        ");
        }
        
        String type = ExpressionUtils.isOperator(node.getData()) ? "OPERATOR" : "OPERAND"; 
        System.out.println("┌─[" + node.getData() + "]─ " + type + " (" + position + ")");

        if (node.getLeft() != null) {
            printHorizontal(node.getLeft(), level + 1, "LEFT"); 
        }
    }
}