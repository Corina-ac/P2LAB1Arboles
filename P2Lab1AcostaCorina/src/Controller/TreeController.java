package Controller;

import Model.ExpressionTree.ExpressionTree;
import Model.BinaryTree.BinaryTree;
import Model.Stack.ListStack; 

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TreeController {
    
    private String captureConsoleOutput(Runnable action) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        
        try {
            action.run(); 
            return baos.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "EXECUTION ERROR: " + e.getMessage();
        } finally {
            System.setOut(originalOut);
        }
    }
    
    // -------------------------------------------------------------------------
    // --- Expression Tree Logic ---
    // -------------------------------------------------------------------------
    
    public String processExpression(String infixExpression) {
        ExpressionTree tree = new ExpressionTree();
        
        return captureConsoleOutput(() -> {
            try {
                tree.buildTree(infixExpression);
                
                System.out.println("╔═══════════════════════════════════════════════════╗");
                System.out.println("║ EXPRESSION TREE RESULTS                           ║");
                System.out.println("╚═══════════════════════════════════════════════════╝");
                System.out.println("\nINFIX EXPRESSION: " + infixExpression);
                
                System.out.println("\n--- TRAVERSALS ---");
                System.out.println(tree.preorderTraversal()); 
                System.out.println(tree.inorderTraversal());   
                System.out.println(tree.postorderTraversal()); 
                
                tree.printTreeHierarchy(); 
                tree.printHorizontalHierarchy(); 

            } catch (Exception e) {
                System.err.println("ERROR building the tree: " + e.getMessage());
                e.printStackTrace(System.err);
            }
        });
    }

    // -------------------------------------------------------------------------
    // --- Generic Binary Tree Logic ---
    // -------------------------------------------------------------------------
    
    public String executeBinaryOperations() {
        BinaryTree myTree = new BinaryTree();
        
        return captureConsoleOutput(() -> {
            System.out.println("╔═══════════════════════════════════════════════════════╗");
            System.out.println("║ GENERIC BINARY TREE OPERATIONS                        ║");
            System.out.println("╚═══════════════════════════════════════════════════════╝");
            
            // Construir un árbol de ejemplo
            System.out.println("\nBuilding example tree with values: 50, 30, 70, 20, 40, 60, 80");
            int[] values = {50, 30, 70, 20, 40, 60, 80};
            for (int value : values) {
                myTree.insert(value);
            }
            
            System.out.println("\n--- TREE INFORMATION ---");
            System.out.println("Total Nodes: " + myTree.size());
            System.out.println("Tree Height: " + myTree.height());
            System.out.println("Number of Leaves: " + myTree.countLeaves());
            
            System.out.println("\n--- TRAVERSALS ---");
            System.out.println("Pre-order:  " + myTree.preorder());
            System.out.println("In-order:   " + myTree.inorder());
            System.out.println("Post-order: " + myTree.postorder());
            System.out.println("Level-order: " + myTree.levelOrder());
            
            // Stack Operations Demo
            System.out.println("\n╔═══════════════════════════════════════════════════════════════╗");
            System.out.println("║        STACK OPERATIONS DEMO (ListStack)                      ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════╝");
            
            ListStack stack = new ListStack();
            
            try {
                System.out.println("\nPushing elements onto the stack:");
                stack.pushStack("Item A");
                System.out.println("  - Pushed: Item A");
                
                stack.pushStack("Item B");
                System.out.println("  - Pushed: Item B");
                
                System.out.println("\nPopping element:");
                stack.popStack();
                System.out.println("  - Popped top element");
                
                stack.pushStack("Item C");
                System.out.println("\n  - Pushed: Item C");
                
                System.out.println("\nPeek operation:");
                System.out.println("  - Top element is: '" + stack.peek() + "'");
                
                System.out.println("\nPopping element:");
                stack.popStack();
                System.out.println("  - Popped top element");
                
                System.out.println("\n✓ Stack operations completed successfully");
                
            } catch (Exception e) {
                System.err.println("Error during stack operation: " + e.getMessage());
            }
        });
    }
}