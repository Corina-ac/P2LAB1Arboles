package View;
import Model.BinaryTree.BinaryTree;
import Model.BinaryTree.Node;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class BinaryTreePanel extends JPanel {
    private JTextField txtValue;
    private JPanel treePanel;
    private JTextArea txtTraversals;
    private JTextArea txtInfo;
    private BinaryTree binaryTree;
    
    // Paleta de colores
    private static final Color PRIMARY_COLOR = new Color(59, 130, 246);
    private static final Color SUCCESS_COLOR = new Color(16, 185, 129);
    private static final Color WARNING_COLOR = new Color(245, 158, 11);
    private static final Color BACKGROUND_COLOR = new Color(249, 250, 251);
    private static final Color CARD_BACKGROUND = Color.WHITE;
    private static final Color TEXT_PRIMARY = new Color(17, 24, 39);
    private static final Color TEXT_SECONDARY = new Color(107, 114, 128);
    private static final Color ERROR_COLOR = new Color(239, 68, 68);
    private static final Color BORDER_COLOR = new Color(229, 231, 235);
    
    public BinaryTreePanel() {
        binaryTree = new BinaryTree();
        setLayout(new BorderLayout(0, 20));
        setBackground(BACKGROUND_COLOR);
        setBorder(new EmptyBorder(30, 40, 30, 40));
        
        // Panel de encabezado e input
        JPanel topPanel = createTopPanel();
        add(topPanel, BorderLayout.NORTH);
        
        // Panel central con scroll
        JScrollPane scrollPane = new JScrollPane(createCenterPanel());
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private JPanel createTopPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND_COLOR);
        
        // Título
        JLabel titleLabel = new JLabel("Generic Binary Tree");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Descripción
        JLabel descLabel = new JLabel(
            "<html><p style='margin-top: 8px;'>Build and visualize a binary search tree " +
            "by adding values dynamically.</p></html>"
        );
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descLabel.setForeground(TEXT_SECONDARY);
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        panel.add(descLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 25)));
        
        // Panel de entrada
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
        
        // Panel izquierdo con controles
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(CARD_BACKGROUND);
        
        // Input para valor
        JPanel inputPanel = new JPanel(new BorderLayout(0, 8));
        inputPanel.setBackground(CARD_BACKGROUND);
        
        JLabel inputLabel = new JLabel("Enter Value");
        inputLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        inputLabel.setForeground(TEXT_PRIMARY);
        
        txtValue = new JTextField();
        txtValue.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtValue.setForeground(TEXT_PRIMARY);
        txtValue.setBackground(new Color(249, 250, 251));
        txtValue.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            new EmptyBorder(10, 12, 10, 12)
        ));
        
        // Enter key para insertar
        txtValue.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    insertValue();
                }
            }
        });
        
        // Efecto focus
        txtValue.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                txtValue.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
                    new EmptyBorder(9, 11, 9, 11)
                ));
            }
            public void focusLost(FocusEvent evt) {
                txtValue.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
                    new EmptyBorder(10, 12, 10, 12)
                ));
            }
        });
        
        JLabel hintLabel = new JLabel("Enter integers (e.g., 50, 30, 70)");
        hintLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        hintLabel.setForeground(TEXT_SECONDARY);
        
        inputPanel.add(inputLabel, BorderLayout.NORTH);
        inputPanel.add(txtValue, BorderLayout.CENTER);
        inputPanel.add(hintLabel, BorderLayout.SOUTH);
        
        leftPanel.add(inputPanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Panel de ejemplos
        JPanel examplesPanel = createExamplesPanel();
        leftPanel.add(examplesPanel);
        
        // Panel derecho con botones
        JPanel rightPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        rightPanel.setBackground(CARD_BACKGROUND);
        
        JButton btnInsert = createStyledButton("Insert", PRIMARY_COLOR);
        btnInsert.addActionListener(e -> insertValue());
        
        JButton btnClear = createStyledButton("Clear Tree", ERROR_COLOR);
        btnClear.addActionListener(e -> clearTree());
        
        JButton btnExample = createStyledButton("Load Example", WARNING_COLOR);
        btnExample.addActionListener(e -> loadExampleTree());
        
        rightPanel.add(btnInsert);
        rightPanel.add(btnClear);
        rightPanel.add(btnExample);
        
        card.add(leftPanel, BorderLayout.CENTER);
        card.add(rightPanel, BorderLayout.EAST);
        
        return card;
    }
    
    private JPanel createExamplesPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(243, 244, 246));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel titleLabel = new JLabel("Quick Add:");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        titleLabel.setForeground(TEXT_SECONDARY);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        buttonsPanel.setBackground(new Color(243, 244, 246));
        
        int[] quickValues = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45};
        
        for (int value : quickValues) {
            JButton btn = new JButton(String.valueOf(value));
            btn.setFont(new Font("Monospaced", Font.PLAIN, 11));
            btn.setBackground(Color.WHITE);
            btn.setForeground(TEXT_PRIMARY);
            btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                new EmptyBorder(3, 8, 3, 8)
            ));
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            btn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(SUCCESS_COLOR);
                    btn.setForeground(Color.WHITE);
                }
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(Color.WHITE);
                    btn.setForeground(TEXT_PRIMARY);
                }
            });
            
            btn.addActionListener(e -> {
                txtValue.setText(String.valueOf(value));
                insertValue();
            });
            
            buttonsPanel.add(btn);
        }
        
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(buttonsPanel);
        
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
        
        // Panel de información
        JPanel infoPanel = createInfoPanel();
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Panel de recorridos
        JPanel traversalsPanel = createTraversalsPanel();
        traversalsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panel.add(treePanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(infoPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(traversalsPanel);
        
        return panel;
    }
    
    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CARD_BACKGROUND);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel label = new JLabel("Tree Information");
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(TEXT_PRIMARY);
        label.setBorder(new EmptyBorder(0, 0, 12, 0));
        
        txtInfo = new JTextArea();
        txtInfo.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtInfo.setForeground(TEXT_PRIMARY);
        txtInfo.setBackground(new Color(247, 248, 250));
        txtInfo.setEditable(false);
        txtInfo.setBorder(new EmptyBorder(12, 12, 12, 12));
        txtInfo.setRows(3);
        txtInfo.setText("Tree is empty. Add values to get started.");
        
        panel.add(label, BorderLayout.NORTH);
        panel.add(txtInfo, BorderLayout.CENTER);
        
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
    
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    private void insertValue() {
        String input = txtValue.getText().trim();
        
        if (input.isEmpty()) {
            showError("Please enter a value.");
            return;
        }
        
        try {
            int value = Integer.parseInt(input);
            binaryTree.insert(value);
            txtValue.setText("");
            txtValue.requestFocus();
            updateTreeVisualization();
            
        } catch (NumberFormatException e) {
            showError("Please enter a valid integer.");
        }
    }
    
    private void clearTree() {
        int result = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to clear the tree?",
            "Confirm Clear",
            JOptionPane.YES_NO_OPTION
        );
        
        if (result == JOptionPane.YES_OPTION) {
            binaryTree.clear();
            updateTreeVisualization();
        }
    }
    
    private void loadExampleTree() {
        binaryTree.clear();
        int[] exampleValues = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45};
        
        for (int value : exampleValues) {
            binaryTree.insert(value);
        }
        
        updateTreeVisualization();
    }
    
    private void updateTreeVisualization() {
        // Actualizar visualización del árbol
        visualizeTree();
        
        // Actualizar información
        displayInfo();
        
        // Actualizar recorridos
        displayTraversals();
    }
    
    private void visualizeTree() {
        treePanel.removeAll();
        treePanel.setLayout(new BorderLayout());
        
        if (binaryTree.isEmpty()) {
            JLabel emptyLabel = new JLabel("Tree is empty. Add values to visualize.");
            emptyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            emptyLabel.setForeground(TEXT_SECONDARY);
            emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            treePanel.add(emptyLabel, BorderLayout.CENTER);
        } else {
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
            TreeVisualPanel visualPanel = new TreeVisualPanel(binaryTree.getRoot());
            
            treePanel.add(headerPanel, BorderLayout.NORTH);
            treePanel.add(visualPanel, BorderLayout.CENTER);
        }
        
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
        
        JPanel nodeItem = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        nodeItem.setBackground(BACKGROUND_COLOR);
        JLabel nodeBox = new JLabel("  ");
        nodeBox.setOpaque(true);
        nodeBox.setBackground(PRIMARY_COLOR);
        nodeBox.setBorder(new EmptyBorder(0, 8, 0, 8));
        JLabel nodeLabel = new JLabel("Node");
        nodeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        nodeLabel.setForeground(TEXT_SECONDARY);
        nodeItem.add(nodeBox);
        nodeItem.add(nodeLabel);
        
        panel.add(nodeItem);
        
        return panel;
    }
    
    private void displayInfo() {
        if (binaryTree.isEmpty()) {
            txtInfo.setText("Tree is empty. Add values to get started.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Total Nodes: ").append(binaryTree.size()).append("\n");
            sb.append("Tree Height: ").append(binaryTree.height()).append("\n");
            sb.append("Number of Leaves: ").append(binaryTree.countLeaves());
            
            txtInfo.setText(sb.toString());
        }
    }
    
    private void displayTraversals() {
        if (binaryTree.isEmpty()) {
            txtTraversals.setText("No traversals available for empty tree.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Pre-order:  ").append(binaryTree.preorder()).append("\n");
            sb.append("In-order:   ").append(binaryTree.inorder()).append("\n");
            sb.append("Post-order: ").append(binaryTree.postorder()).append("\n");
            sb.append("Level-order: ").append(binaryTree.levelOrder());
            
            txtTraversals.setText(sb.toString());
        }
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    // Clase interna para visualizar el árbol gráficamente
    private class TreeVisualPanel extends JPanel {
        private Node root;
        private static final int NODE_WIDTH = 50;
        private static final int NODE_HEIGHT = 50;
        private static final int LEVEL_HEIGHT = 80;
        
        public TreeVisualPanel(Node root) {
            this.root = root;
            setBackground(CARD_BACKGROUND);
            setPreferredSize(calculatePanelSize());
        }
        
        private Dimension calculatePanelSize() {
            if (root == null) return new Dimension(400, 200);
            
            int height = getTreeHeight(root) * LEVEL_HEIGHT + 100;
            int width = (int) Math.pow(2, getTreeHeight(root)) * (NODE_WIDTH + 20);
            
            return new Dimension(Math.max(600, width), Math.max(400, height));
        }
        
        private int getTreeHeight(Node node) {
            if (node == null) return 0;
            return 1 + Math.max(getTreeHeight(node.getLeftSubtree()), getTreeHeight(node.getRightSubtree()));
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
        
        private void drawTree(Graphics2D g2d, Node node, int x, int y, int xOffset, String position) {
            if (node == null) return;
            
            // Dibujar líneas a los hijos
            if (node.getLeftSubtree() != null) {
                int childX = x - xOffset;
                int childY = y + LEVEL_HEIGHT;
                g2d.setColor(BORDER_COLOR);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(x, y + NODE_HEIGHT / 2, childX, childY - 20);
                drawTree(g2d, node.getLeftSubtree(), childX, childY, xOffset / 2, "Left");
            }
            
            if (node.getRightSubtree() != null) {
                int childX = x + xOffset;
                int childY = y + LEVEL_HEIGHT;
                g2d.setColor(BORDER_COLOR);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(x, y + NODE_HEIGHT / 2, childX, childY - 20);
                drawTree(g2d, node.getRightSubtree(), childX, childY, xOffset / 2, "Right");
            }
            
            // Dibujar etiqueta de posición
            g2d.setColor(BACKGROUND_COLOR);
            g2d.fillRoundRect(x - 25, y - 25, 50, 18, 8, 8);
            g2d.setColor(BORDER_COLOR);
            g2d.setStroke(new BasicStroke(1));
            g2d.drawRoundRect(x - 25, y - 25, 50, 18, 8, 8);
            g2d.setColor(TEXT_SECONDARY);
            g2d.setFont(new Font("Segoe UI", Font.BOLD, 10));
            FontMetrics fm = g2d.getFontMetrics();
            int labelWidth = fm.stringWidth(position);
            g2d.drawString(position, x - labelWidth / 2, y - 11);
            
            // Dibujar nodo
            g2d.setColor(Color.WHITE);
            g2d.fillRoundRect(x - NODE_WIDTH / 2, y, NODE_WIDTH, NODE_HEIGHT, 8, 8);
            g2d.setColor(PRIMARY_COLOR);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawRoundRect(x - NODE_WIDTH / 2, y, NODE_WIDTH, NODE_HEIGHT, 8, 8);
            
            // Dibujar valor
            g2d.setColor(PRIMARY_COLOR);
            g2d.setFont(new Font("Segoe UI", Font.BOLD, 18));
            fm = g2d.getFontMetrics();
            String valueStr = node.getData().toString();
            int textWidth = fm.stringWidth(valueStr);
            int textHeight = fm.getAscent();
            g2d.drawString(valueStr, x - textWidth / 2, y + NODE_HEIGHT / 2 + textHeight / 3);
        }
    }
}