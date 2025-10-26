package Assignment6;

import javax.swing.*;

/**
 * Main application class for the Modular Upgrade System game
 * Demonstrates the Strategy Pattern for runtime-switchable player upgrades
 */
public class ModularUpgradeGame {
    
    //Entry point for the application.
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety
        SwingUtilities.invokeLater(ModularUpgradeGame::createAndShowGUI);
    }
    
    //Creates and displays the game window
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Strategy Pattern - Modular Upgrade System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        // Add game panel
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        
        // Pack and center the window
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // Request focus for keyboard input
        gamePanel.requestFocusInWindow();
        
        System.out.println("=== Modular Upgrade System Started ===");
        System.out.println("Strategy Pattern Implementation");
        System.out.println("NEW: Combine multiple strategies simultaneously!");
        System.out.println("Controls:");
        System.out.println("  WASD - Move player");
        System.out.println("  Left Click - Shoot");
        System.out.println("  Right Click - Spawn enemy");
        System.out.println("  1 - Toggle Damage Strategy (2x damage)");
        System.out.println("  2 - Toggle Speed Strategy (1.8x speed)");
        System.out.println("  3 - Toggle Pierce Strategy (pierce 3 enemies)");
        System.out.println("  R - Reset all strategies");
        System.out.println("TIP: Press 1, 2, and 3 to combine all upgrades!");
    }
}
