package Executable;

import View.MainPanel;
import javax.swing.SwingUtilities;
import javax.swing.JFrame; 
import javax.swing.WindowConstants; 

public class MainApp {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(" Tree & Stack Simulator (MVC)");
            
            MainPanel mainPanel = new MainPanel();
            
            frame.add(mainPanel);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
            frame.setSize(1000, 700); 
            frame.setLocationRelativeTo(null); 
            frame.setVisible(true);
        });
    }
}