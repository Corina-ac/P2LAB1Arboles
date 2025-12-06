package View;
import Model.ExpressionTree.ExpressionTree;
import Model.ExpressionTree.TreeNode;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class ExpressionTreePanel extends JPanel {
    private JTextField txtExpression;
    private JPanel treePanel;
    private JTextArea txtTraversals;
    private ExpressionTree expressionTree;
    
    private static final Color PRIMARY_COLOR = new Color(59, 130, 246);
    private static final Color SUCCESS_COLOR = new Color(16, 185, 129);
    private static final Color BACKGROUND_COLOR = new Color(249, 250, 251);
    private static final Color CARD_BACKGROUND = Color.WHITE;
    private static final Color TEXT_PRIMARY = new Color(17, 24, 39);
    private static final Color TEXT_SECONDARY = new Color(107, 114, 128);
    private static final Color ERROR_COLOR = new Color(239, 68, 68);
    private static final Color BORDER_COLOR = new Color(229, 231, 235);
    
    public ExpressionTreePanel() {
        expressionTree = new ExpressionTree();
        setLayout(new BorderLayout(0, 20));
        setBackground(BACKGROUND_COLOR);
        setBorder(new EmptyBorder(30, 40, 30, 40));
        
        JPanel topPanel = createTopPanel();
        add(topPanel, BorderLayout.NORTH);
        
        JScrollPane scrollPane = new JScrollPane(createCenterPanel());
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private JPanel createTopPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND_COLOR);
        
        JLabel titleLabel = new JLabel("Expression Tree");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel descLabel = new JLabel(
            "<html><p style='margin-top: 8px;'>Build and visualize expression trees " +
            "from infix mathematical expressions.</p></html>"
        );
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descLabel.setForeground(TEXT_SECONDARY);
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        panel.add(descLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 25)));
        
        JPanel inputCard = createInputCard();
        inputCard.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(inputCard);
        
        return panel;
    }
    
    private JPanel createInputCard() {
        JPanel card = new JPanel(new BorderLayout(15, 15));
        card.setBackground(CARD_BACKGROUND);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        JPanel leftPanel = new JPanel(new BorderLayout(0, 8));
        leftPanel.setBackground(CARD_BACKGROUND);
        
        JLabel inputLabel = new JLabel("Infix Expression");
        inputLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        inputLabel.setForeground(TEXT_PRIMARY);
        
        txtExpression = new JTextField("(A+B)*((C+D)/(E+F))");
        txtExpression.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtExpression.setForeground(TEXT_PRIMARY);
        txtExpression.setBackground(new Color(249, 250, 251));
        txtExpression.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            new EmptyBorder(10, 12, 10, 12)
        ));
        
        txtExpression.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buildAndShowTree();
                }
            }
        });
        
        txtExpression.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                txtExpression.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
                    new EmptyBorder(9, 11, 9, 11)
                ));
            }
            public void focusLost(FocusEvent evt) {
                txtExpression.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
                    new EmptyBorder(10, 12, 10, 12)
                ));
            }
        });
        
        JPanel examplesPanel = createExamplesPanel();
        
        leftPanel.add(inputLabel, BorderLayout.NORTH);
        leftPanel.add(txtExpression, BorderLayout.CENTER);
        leftPanel.add(examplesPanel, BorderLayout.SOUTH);
        
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(CARD_BACKGROUND);
        
        JButton btnBuild = createStyledButton("Build Tree");
        btnBuild.addActionListener(e -> buildAndShowTree());
        btnBuild.setPreferredSize(new Dimension(140, 44));
        
        rightPanel.add(btnBuild, BorderLayout.SOUTH);
        
        card.add(leftPanel, BorderLayout.CENTER);
        card.add(rightPanel, BorderLayout.EAST);
        
        return card;
    }
    
    private JPanel createExamplesPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel.setBackground(CARD_BACKGROUND);
        
        JLabel label = new JLabel("Examples:");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        label.setForeground(TEXT_SECONDARY);
        panel.add(label);
        
        String[] examples = {
            "(A-(B-(C-D)/(E+F))",
            "(A+B)*((C+D)/(E+F))",
            "(A-B)/((C*D)-(E/F))",
            "A+B*C",
            "(A+B)*(C-D)"
        };
        
        for (String example : examples) {
            JButton btn = new JButton(example);
            btn.setFont(new Font("Monospaced", Font.PLAIN, 10));
            btn.setBackground(BACKGROUND_COLOR);
            btn.setForeground(TEXT_PRIMARY);
            btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                new EmptyBorder(3, 8, 3, 8)
            ));
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            btn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(PRIMARY_COLOR);
                    btn.setForeground(Color.WHITE);
                    btn.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(PRIMARY_COLOR, 1),
                        new EmptyBorder(3, 8, 3, 8)
                    ));
                }
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(BACKGROUND_COLOR);
                    btn.setForeground(TEXT_PRIMARY);
                    btn.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER_COLOR, 1),
                        new EmptyBorder(3, 8, 3, 8)
                    ));
                }
            });
            
            btn.addActionListener(e -> txtExpression.setText(example));
            panel.add(btn);
        }
        
        return panel;
    }
    
    private JPanel createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND_COLOR);
        
        // Panel del árbol
        treePanel = new JPanel();
        treePanel.setBackground(CARD_BACKGROUND);
        treePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            new EmptyBorder(30, 30, 30, 30)
        ));
        treePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Panel de recorridos
        JPanel traversalsPanel = createTraversalsPanel();
        traversalsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panel.add(treePanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(traversalsPanel);
        
        return panel;
    }
    
    private JPanel createTraversalsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CARD_BACKGROUND);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel label = new JLabel("Tree Traversals");
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(TEXT_PRIMARY);
        label.setBorder(new EmptyBorder(0, 0, 12, 0));
        
        txtTraversals = new JTextArea();
        txtTraversals.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtTraversals.setForeground(TEXT_PRIMARY);
        txtTraversals.setBackground(new Color(247, 248, 250));
        txtTraversals.setEditable(false);
        txtTraversals.setBorder(new EmptyBorder(12, 12, 12, 12));
        txtTraversals.setRows(4);
        
        panel.add(label, BorderLayout.NORTH);
        panel.add(txtTraversals, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(PRIMARY_COLOR);
        button.setBorder(new EmptyBorder(12, 24, 12, 24));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(37, 99, 235));
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(PRIMARY_COLOR);
            }
        });
        
        return button;
    }
    
    private void buildAndShowTree() {
        String expression = txtExpression.getText().trim();
        
        if (expression.isEmpty()) {
            showError("Please enter an expression.");
            return;
        }
        
        try {
            // Construir el árbol usando tu modelo existente
            expressionTree.buildTree(expression);
            
            // Visualizar el árbol
            visualizeTree();
            
            // Mostrar recorridos
            displayTraversals();
            
        } catch (Exception e) {
            showError("Error: " + e.getMessage());
        }
    }
    
    private void visualizeTree() {
        treePanel.removeAll();
        treePanel.setLayout(new BorderLayout());
        
        // Título y leyenda
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(CARD_BACKGROUND);
        
        JLabel titleLabel = new JLabel("Tree Structure");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(TEXT_PRIMARY);
        
        JPanel legendPanel = createLegendPanel();
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(legendPanel, BorderLayout.EAST);
        headerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
        
        // Panel del árbol visual
        TreeVisualPanel visualPanel = new TreeVisualPanel(expressionTree.getRoot());
        
        treePanel.add(headerPanel, BorderLayout.NORTH);
        treePanel.add(visualPanel, BorderLayout.CENTER);
        
        treePanel.revalidate();
        treePanel.repaint();
    }
    
    private JPanel createLegendPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            new EmptyBorder(8, 12, 8, 12)
        ));
        
        // Operador
        JPanel operatorItem = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        operatorItem.setBackground(BACKGROUND_COLOR);
        JLabel operatorBox = new JLabel("  ");
        operatorBox.setOpaque(true);
        operatorBox.setBackground(PRIMARY_COLOR);
        operatorBox.setBorder(new EmptyBorder(0, 8, 0, 8));
        JLabel operatorLabel = new JLabel("Operator");
        operatorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        operatorLabel.setForeground(TEXT_SECONDARY);
        operatorItem.add(operatorBox);
        operatorItem.add(operatorLabel);
        
        JPanel operandItem = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        operandItem.setBackground(BACKGROUND_COLOR);
        JLabel operandBox = new JLabel("  ");
        operandBox.setOpaque(true);
        operandBox.setBackground(SUCCESS_COLOR);
        operandBox.setBorder(new EmptyBorder(0, 8, 0, 8));
        JLabel operandLabel = new JLabel("Operand");
        operandLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        operandLabel.setForeground(TEXT_SECONDARY);
        operandItem.add(operandBox);
        operandItem.add(operandLabel);
        
        panel.add(operatorItem);
        panel.add(operandItem);
        
        return panel;
    }
    
    private void displayTraversals() {
        StringBuilder sb = new StringBuilder();
        
        String preorder = expressionTree.preorderTraversal();
        String inorder = expressionTree.inorderTraversal();
        String postorder = expressionTree.postorderTraversal();
        
        sb.append(preorder).append("\n");
        sb.append(inorder).append("\n");
        sb.append(postorder);
        
        txtTraversals.setText(sb.toString());
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private class TreeVisualPanel extends JPanel {
        private TreeNode root;
        private static final int NODE_WIDTH = 60;
        private static final int NODE_HEIGHT = 60;
        private static final int LEVEL_HEIGHT = 100;
        private static final int HORIZONTAL_GAP = 20;
        
        public TreeVisualPanel(TreeNode root) {
            this.root = root;
            setBackground(CARD_BACKGROUND);
            setPreferredSize(calculatePanelSize());
        }
        
        private Dimension calculatePanelSize() {
            if (root == null) return new Dimension(400, 200);
            
            int height = getTreeHeight(root) * LEVEL_HEIGHT + 100;
            int width = getTreeWidth(root) * (NODE_WIDTH + HORIZONTAL_GAP) + 100;
            
            return new Dimension(Math.max(600, width), Math.max(400, height));
        }
        
        private int getTreeHeight(TreeNode node) {
            if (node == null) return 0;
            return 1 + Math.max(getTreeHeight(node.getLeft()), getTreeHeight(node.getRight()));
        }
        
        private int getTreeWidth(TreeNode node) {
            if (node == null) return 0;
            if (node.getLeft() == null && node.getRight() == null) return 1;
            return getTreeWidth(node.getLeft()) + getTreeWidth(node.getRight());
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            if (root != null) {
                int startX = getWidth() / 2;
                drawTree(g2d, root, startX, 50, getWidth() / 4, "Root");
            }
        }
        
        private void drawTree(Graphics2D g2d, TreeNode node, int x, int y, int xOffset, String position) {
            if (node == null) return;
            
            if (node.getLeft() != null) {
                int childX = x - xOffset;
                int childY = y + LEVEL_HEIGHT;
                g2d.setColor(BORDER_COLOR);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(x, y + NODE_HEIGHT / 2, childX, childY - 20);
                drawTree(g2d, node.getLeft(), childX, childY, xOffset / 2, "Left");
            }
            
            if (node.getRight() != null) {
                int childX = x + xOffset;
                int childY = y + LEVEL_HEIGHT;
                g2d.setColor(BORDER_COLOR);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(x, y + NODE_HEIGHT / 2, childX, childY - 20);
                drawTree(g2d, node.getRight(), childX, childY, xOffset / 2, "Right");
            }
            
            g2d.setColor(BACKGROUND_COLOR);
            g2d.fillRoundRect(x - 25, y - 25, 50, 20, 10, 10);
            g2d.setColor(BORDER_COLOR);
            g2d.setStroke(new BasicStroke(1));
            g2d.drawRoundRect(x - 25, y - 25, 50, 20, 10, 10);
            g2d.setColor(TEXT_SECONDARY);
            g2d.setFont(new Font("Segoe UI", Font.BOLD, 11));
            FontMetrics fm = g2d.getFontMetrics();
            int labelWidth = fm.stringWidth(position);
            g2d.drawString(position, x - labelWidth / 2, y - 10);
            
            boolean isOperator = isOperatorNode(node.getData());
            Color nodeColor = isOperator ? PRIMARY_COLOR : SUCCESS_COLOR;
            
            g2d.setColor(Color.WHITE);
            g2d.fillRoundRect(x - NODE_WIDTH / 2, y, NODE_WIDTH, NODE_HEIGHT, 10, 10);
            g2d.setColor(nodeColor);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawRoundRect(x - NODE_WIDTH / 2, y, NODE_WIDTH, NODE_HEIGHT, 10, 10);
            
            g2d.setColor(nodeColor);
            g2d.setFont(new Font("Segoe UI", Font.BOLD, 24));
            fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(node.getData());
            int textHeight = fm.getAscent();
            g2d.drawString(node.getData(), x - textWidth / 2, y + NODE_HEIGHT / 2 + textHeight / 3);
        }
        
        private boolean isOperatorNode(String value) {
            return value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/") || value.equals("^");
        }
    }
}