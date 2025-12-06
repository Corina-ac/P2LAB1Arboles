package View;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    
    private static final Color PRIMARY_COLOR = new Color(59, 130, 246);      
    private static final Color SECONDARY_COLOR = new Color(99, 102, 241);   
    private static final Color BACKGROUND_COLOR = new Color(249, 250, 251);  
    private static final Color CARD_BACKGROUND = Color.WHITE;
    private static final Color TEXT_PRIMARY = new Color(17, 24, 39);         
    private static final Color TEXT_SECONDARY = new Color(107, 114, 128);  
    private static final Color HOVER_COLOR = new Color(37, 99, 235);        
    
    public MainPanel() {
        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);
        
        JPanel navigationPanel = createNavigationPanel();
        this.add(navigationPanel, BorderLayout.NORTH);
        
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(BACKGROUND_COLOR);
        
        JPanel welcomePanel = createWelcomePanel();
        
        cardPanel.add(welcomePanel, "START");
        cardPanel.add(new BinaryTreePanel(), "BINARY_TREE"); 
        cardPanel.add(new ExpressionTreePanel(), "EXPRESSION_TREE"); 
        
        this.add(cardPanel, BorderLayout.CENTER);
        
        cardLayout.show(cardPanel, "START");
    }
    
    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);
        
        JLabel titleLabel = new JLabel("Tree Simulator");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 42));
        titleLabel.setForeground(TEXT_PRIMARY);
        panel.add(titleLabel, gbc);
        
        JLabel subtitleLabel = new JLabel("Visualiza y analiza estructuras de árboles");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitleLabel.setForeground(TEXT_SECONDARY);
        gbc.insets = new Insets(0, 0, 40, 0);
        panel.add(subtitleLabel, gbc);
        
        JPanel cardsContainer = new JPanel(new GridLayout(1, 2, 30, 0));
        cardsContainer.setBackground(BACKGROUND_COLOR);
        cardsContainer.setMaximumSize(new Dimension(800, 250));
        
        JPanel card1 = createFeatureCard(
            "🌳",
            "Árbol Binario Genérico",
            "Explora operaciones básicas de árboles binarios y estructuras de pila",
            () -> cardLayout.show(cardPanel, "BINARY_TREE")
        );
        
        JPanel card2 = createFeatureCard(
            "➕",
            "Árbol de Expresiones",
            "Construye y analiza árboles desde expresiones infijas matemáticas",
            () -> cardLayout.show(cardPanel, "EXPRESSION_TREE")
        );
        
        cardsContainer.add(card1);
        cardsContainer.add(card2);
        
        gbc.insets = new Insets(0, 50, 0, 50);
        panel.add(cardsContainer, gbc);
        
        return panel;
    }
    
    private JPanel createFeatureCard(String icon, String title, String description, Runnable action) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(CARD_BACKGROUND);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
            new EmptyBorder(30, 25, 30, 25)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel descLabel = new JLabel("<html><div style='text-align: center; width: 200px;'>" + 
                                      description + "</div></html>");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        descLabel.setForeground(TEXT_SECONDARY);
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        card.add(iconLabel);
        card.add(Box.createRigidArea(new Dimension(0, 15)));
        card.add(titleLabel);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(descLabel);
        
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBackground(new Color(248, 250, 252));
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
                    new EmptyBorder(29, 24, 29, 24)
                ));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBackground(CARD_BACKGROUND);
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
                    new EmptyBorder(30, 25, 30, 25)
                ));
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                action.run();
            }
        });
        
        return card;
    }
    
    private JPanel createNavigationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CARD_BACKGROUND);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(229, 231, 235)),
            new EmptyBorder(15, 30, 15, 30)
        ));
        
        // Logo/Título a la izquierda
        JLabel logoLabel = new JLabel("🌲 Tree Simulator");
        logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        logoLabel.setForeground(PRIMARY_COLOR);
        
        // Panel de botones a la derecha
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        buttonsPanel.setBackground(CARD_BACKGROUND);
        
        JButton btnBinary = createNavButton("Árbol Binario", "🌳");
        btnBinary.addActionListener(e -> cardLayout.show(cardPanel, "BINARY_TREE"));
        
        JButton btnExpressions = createNavButton("Expresiones", "➕");
        btnExpressions.addActionListener(e -> cardLayout.show(cardPanel, "EXPRESSION_TREE"));
        
        buttonsPanel.add(btnBinary);
        buttonsPanel.add(btnExpressions);
        
        panel.add(logoLabel, BorderLayout.WEST);
        panel.add(buttonsPanel, BorderLayout.EAST);
        
        return panel;
    }
    
    private JButton createNavButton(String text, String icon) {
        JButton button = new JButton(icon + " " + text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setForeground(TEXT_PRIMARY);
        button.setBackground(CARD_BACKGROUND);
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorderPainted(false);
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(243, 244, 246));
                button.setForeground(PRIMARY_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(CARD_BACKGROUND);
                button.setForeground(TEXT_PRIMARY);
            }
        });
        
        return button;
    }
}
