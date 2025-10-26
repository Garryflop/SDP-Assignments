package Assignment6;

import Assignment6.model.Bullet;
import Assignment6.model.Enemy;
import Assignment6.model.Player;
import Assignment6.strategy.DamageStrategy;
import Assignment6.strategy.PierceStrategy;
import Assignment6.strategy.SpeedStrategy;
import Assignment6.strategy.UpgradeStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Main game panel that handles rendering, input, and game loop
 */
public class GamePanel extends JPanel implements ActionListener {
    
    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 600;
    private static final int TIMER_DELAY = 16; // ~60 FPS
    
    private Player player;
    private List<Enemy> enemies;
    private List<Bullet> bullets;
    private Timer gameTimer;
    
    // Input tracking
    private boolean wPressed, aPressed, sPressed, dPressed;
    
    // Available strategies
    private UpgradeStrategy damageStrategy;
    private UpgradeStrategy speedStrategy;
    private UpgradeStrategy pierceStrategy;
    
    //Initializes the game panel with all components
    public GamePanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.DARK_GRAY);
        setFocusable(true);
        
        initializeGame();
        setupInputListeners();
        
        // Start game loop
        gameTimer = new Timer(TIMER_DELAY, this);
        gameTimer.start();
    }
    
    //Initializes game objects and strategies
    private void initializeGame() {
        // Initialize player at center
        player = new Player(PANEL_WIDTH / 2.0, PANEL_HEIGHT / 2.0);
        
        // Initialize collections
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        
        // Initialize strategies
        damageStrategy = new DamageStrategy();
        speedStrategy = new SpeedStrategy();
        pierceStrategy = new PierceStrategy();
        
        // No default strategy - player starts with base stats
    }
    
    //Sets up keyboard and mouse input listeners
    private void setupInputListeners() {
        // Keyboard input for movement and strategy switching
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e.getKeyCode());
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                handleKeyRelease(e.getKeyCode());
            }
        });
        
        // Mouse input for shooting and enemy spawning
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMouseClick(e);
            }
        });
    }
    
    //Handles keyboard key press events
    private void handleKeyPress(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W:
                wPressed = true;
                break;
            case KeyEvent.VK_A:
                aPressed = true;
                break;
            case KeyEvent.VK_S:
                sPressed = true;
                break;
            case KeyEvent.VK_D:
                dPressed = true;
                break;
            case KeyEvent.VK_1:
                // Toggle damage strategy
                toggleStrategy(damageStrategy);
                break;
            case KeyEvent.VK_2:
                // Toggle speed strategy
                toggleStrategy(speedStrategy);
                break;
            case KeyEvent.VK_3:
                // Toggle pierce strategy
                toggleStrategy(pierceStrategy);
                break;
            case KeyEvent.VK_R:
                // Reset all strategies
                player.clearStrategies();
                break;
        }
        updatePlayerMovement();
    }
    
    //Toggles a strategy on/off for combining multiple strategies
    private void toggleStrategy(UpgradeStrategy strategy) {
        if (player.getActiveStrategies().contains(strategy)) {
            player.removeStrategy(strategy);
        } else {
            player.addStrategy(strategy);
        }
    }
    
    //Handles keyboard key release events
    private void handleKeyRelease(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W:
                wPressed = false;
                break;
            case KeyEvent.VK_A:
                aPressed = false;
                break;
            case KeyEvent.VK_S:
                sPressed = false;
                break;
            case KeyEvent.VK_D:
                dPressed = false;
                break;
        }
        updatePlayerMovement();
    }
    
    //Updates player movement based on current input state.

    private void updatePlayerMovement() {
        double dx = 0;
        double dy = 0;
        
        if (wPressed) dy -= 1;
        if (sPressed) dy += 1;
        if (aPressed) dx -= 1;
        if (dPressed) dx += 1;
        
        player.move(dx, dy);
    }
    
    //Handles mouse click events for shooting and spawning enemies
    private void handleMouseClick(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            // Left click: shoot
            Bullet bullet = player.shoot(e.getX(), e.getY());
            bullets.add(bullet);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            // Right click: spawn enemy
            Enemy enemy = new Enemy(e.getX(), e.getY());
            enemies.add(enemy);
        }
    }
    
    //Game loop - called every frame
    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
        repaint();
    }
    
    //Updates all game objects
    private void updateGame() {
        // Update player
        player.update();
        
        // Update bullets
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            bullet.update();
            
            // Remove bullets that are out of bounds
            if (bullet.isOutOfBounds(PANEL_WIDTH, PANEL_HEIGHT) || !bullet.isActive()) {
                bulletIterator.remove();
            }
        }
        
        // Check bullet-enemy collisions
        checkCollisions();
        
        // Remove dead enemies
        enemies.removeIf(enemy -> !enemy.isAlive());
    }
    
    //Checks for collisions between bullets and enemies
    private void checkCollisions() {
        for (Bullet bullet : bullets) {
            if (!bullet.isActive()) {
                continue;
            }
            
            for (Enemy enemy : enemies) {
                // Skip if enemy is dead or already hit by this bullet
                if (!enemy.isAlive() || bullet.hasHitEnemy(enemy)) {
                    continue;
                }
                
                if (isColliding(bullet, enemy)) {
                    // Deal damage to enemy
                    enemy.takeDamage(bullet.getDamage());
                    
                    // Mark this enemy as hit by this bullet
                    bullet.markEnemyHit(enemy);
                    
                    // Handle pierce logic
                    if (bullet.canPierce()) {
                        bullet.decrementPierce();
                        // Bullet continues flying and can hit other enemies
                    } else {
                        // No pierce left, deactivate bullet
                        bullet.setActive(false);
                    }
                }
            }
        }
    }
    
    //Checks if a bullet is colliding with an enemy.
    private boolean isColliding(Bullet bullet, Enemy enemy) {
        double dx = bullet.getPosition().getX() - enemy.getPosition().getX();
        double dy = bullet.getPosition().getY() - enemy.getPosition().getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        
        return distance < (bullet.getSize() + enemy.getSize() / 2.0);
    }
    
    //Renders all game objects
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Enable anti-aliasing for smoother graphics
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw player
        drawPlayer(g2d);
        
        // Draw enemies
        drawEnemies(g2d);
        
        // Draw bullets
        drawBullets(g2d);
        
        // Draw UI
        drawUI(g2d);
    }
    
    //Draws the player
    private void drawPlayer(Graphics2D g2d) {
        g2d.setColor(Color.CYAN);
        g2d.fillRect(
            (int) player.getPosition().getX(),
            (int) player.getPosition().getY(),
            player.getSize(),
            player.getSize()
        );
        
        // Draw player health bar
        g2d.setColor(Color.RED);
        g2d.fillRect(
            (int) player.getPosition().getX(),
            (int) player.getPosition().getY() - 10,
            player.getSize(),
            5
        );
        g2d.setColor(Color.GREEN);
        int healthWidth = (int) (player.getSize() * ((double) player.getHealth() / player.getMaxHealth()));
        g2d.fillRect(
            (int) player.getPosition().getX(),
            (int) player.getPosition().getY() - 10,
            healthWidth,
            5
        );
    }
    
    //Draws all enemies
    private void drawEnemies(Graphics2D g2d) {
        for (Enemy enemy : enemies) {
            // Draw enemy with health-based color
            g2d.setColor(enemy.getHealthColor());
            g2d.fillOval(
                (int) (enemy.getPosition().getX() - enemy.getSize() / 2.0),
                (int) (enemy.getPosition().getY() - enemy.getSize() / 2.0),
                enemy.getSize(),
                enemy.getSize()
            );
            
            // Draw enemy health bar
            g2d.setColor(Color.RED);
            g2d.fillRect(
                (int) (enemy.getPosition().getX() - enemy.getSize() / 2.0),
                (int) (enemy.getPosition().getY() - enemy.getSize() / 2.0 - 10),
                enemy.getSize(),
                5
            );
            g2d.setColor(Color.GREEN);
            int healthWidth = (int) (enemy.getSize() * enemy.getHealthPercentage());
            g2d.fillRect(
                (int) (enemy.getPosition().getX() - enemy.getSize() / 2.0),
                (int) (enemy.getPosition().getY() - enemy.getSize() / 2.0 - 10),
                healthWidth,
                5
            );
        }
    }
    
    //Draws all bullets
    private void drawBullets(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        for (Bullet bullet : bullets) {
            g2d.fillOval(
                (int) (bullet.getPosition().getX() - bullet.getSize() / 2.0),
                (int) (bullet.getPosition().getY() - bullet.getSize() / 2.0),
                bullet.getSize(),
                bullet.getSize()
            );
        }
    }
    
    //Draws the user interface (controls and current strategy)
    private void drawUI(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Draw controls
        g2d.drawString("Controls:", 10, 20);
        g2d.drawString("WASD - Move", 10, 40);
        g2d.drawString("Left Click - Shoot", 10, 60);
        g2d.drawString("Right Click - Spawn Enemy", 10, 80);
        g2d.drawString("1/2/3 - Toggle Strategy", 10, 100);
        g2d.drawString("R - Reset Strategies", 10, 120);
        
        // Draw active strategies
        g2d.drawString("Active Strategies:", 10, 160);
        
        int yOffset = 180; // Declare yOffset here
        
        if (player.getActiveStrategies().isEmpty()) {
            g2d.setColor(Color.GRAY);
            g2d.drawString("None (Press 1/2/3)", 10, yOffset);
            yOffset += 20;
        } else {
            for (UpgradeStrategy strategy : player.getActiveStrategies()) {
                g2d.setColor(Color.YELLOW);
                g2d.drawString("• " + strategy.getStrategyName(), 10, yOffset);
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.setFont(new Font("Arial", Font.PLAIN, 11));
                g2d.drawString("  " + strategy.getDescription(), 10, yOffset + 15);
                g2d.setFont(new Font("Arial", Font.BOLD, 14));
                yOffset += 35;
            }
        }
        
        // Draw stats
        g2d.setColor(Color.CYAN);
        g2d.drawString("Stats:", 10, yOffset + 20);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.drawString("Damage: " + String.format("%.1f", player.getCurrentDamage()), 10, yOffset + 40);
        g2d.drawString("Speed: " + String.format("%.1f", player.getCurrentSpeed()), 10, yOffset + 60);
        g2d.drawString("Pierce: " + player.getPierceCount(), 10, yOffset + 80);
        
        // Draw enemy count
        g2d.drawString("Enemies: " + enemies.size(), 10, yOffset + 110);
    }
}
