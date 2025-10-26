package Assignment6.model;

import Assignment6.strategy.UpgradeStrategy;
import Assignment6.strategy.DamageStrategy;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Player class - the Context in Strategy Pattern.
 * Maintains reference to current UpgradeStrategy and delegates upgrade behavior to it.
 */
public class Player {
    
    private static final int PLAYER_SIZE = 20;
    private static final double BASE_DAMAGE = 25.0;
    private static final double BASE_SPEED = 5.0;
    private static final int MAX_HEALTH = 100;
    
    private Vector2D position;
    private Vector2D velocity;
    private int health;
    private int maxHealth;
    
    // Base stats
    private double baseDamage;
    private double baseSpeed;
    
    // Current stats (affected by strategy)
    private double currentDamage;
    private double currentSpeed;
    private int pierceCount;
    
    // Strategy Pattern - the key component
    private UpgradeStrategy upgradeStrategy;
    private List<UpgradeStrategy> activeStrategies;
    
    //Creates a player at the specified position.

    public Player(double x, double y) {
        this.position = new Vector2D(x, y);
        this.velocity = new Vector2D(0, 0);
        this.maxHealth = MAX_HEALTH;
        this.health = MAX_HEALTH;
        
        // Initialize base stats
        this.baseDamage = BASE_DAMAGE;
        this.baseSpeed = BASE_SPEED;
        
        // Initialize current stats
        this.currentDamage = BASE_DAMAGE;
        this.currentSpeed = BASE_SPEED;
        this.pierceCount = 0;
        
        // Initialize active strategies list
        this.activeStrategies = new ArrayList<>();
        
        // No default strategy - player starts with base stats
        this.upgradeStrategy = null;
    }
    
    //Sets the upgrade strategy (Strategy Pattern core method)
    public void setUpgradeStrategy(UpgradeStrategy strategy) {
        this.upgradeStrategy = strategy;
        applyCurrentStrategy();
    }
    
    //Adds a strategy to the active strategies list (allows combining multiple strategies)
    public void addStrategy(UpgradeStrategy strategy) {
        if (!activeStrategies.contains(strategy)) {
            activeStrategies.add(strategy);
            applyAllStrategies();
        }
    }
    
    //Removes a strategy from the active strategies list
    public void removeStrategy(UpgradeStrategy strategy) {
        activeStrategies.remove(strategy);
        applyAllStrategies();
    }
    
    //Clears all active strategies and resets to base stats
    public void clearStrategies() {
        activeStrategies.clear();
        resetToBaseStats();
    }
    
    //Resets player stats to base values
    private void resetToBaseStats() {
        this.currentDamage = baseDamage;
        this.currentSpeed = baseSpeed;
        this.pierceCount = 0;
    }
    
    //Applies all active strategies in sequence
    private void applyAllStrategies() {
        resetToBaseStats();
        for (UpgradeStrategy strategy : activeStrategies) {
            strategy.applyUpgrade(this);
        }
    }
    
    //Applies the current upgrade strategy.Delegates the upgrade logic to the strategy object
    public void applyCurrentStrategy() {
        if (upgradeStrategy != null) {
            upgradeStrategy.applyUpgrade(this);
        }
    }
    
    //Gets the list of active strategies.
    public List<UpgradeStrategy> getActiveStrategies() {
        return activeStrategies;
    }
    
    //Creates and returns a bullet fired towards the target position.
    public Bullet shoot(double targetX, double targetY) {
        return new Bullet(
            position.getX() + PLAYER_SIZE / 2.0,
            position.getY() + PLAYER_SIZE / 2.0,
            targetX,
            targetY,
            currentDamage,
            pierceCount
        );
    }
    
    //Updates player position based on velocity.
    public void update() {
        position = position.add(velocity);
        
        // Keep player in bounds (assuming 800x600 window)
        if (position.getX() < 0) position.setX(0);
        if (position.getY() < 0) position.setY(0);
        if (position.getX() > 800 - PLAYER_SIZE) position.setX(800 - PLAYER_SIZE);
        if (position.getY() > 600 - PLAYER_SIZE) position.setY(600 - PLAYER_SIZE);
    }
    
    //Sets player movement direction based on input.
    public void move(double dx, double dy) {
        Vector2D direction = new Vector2D(dx, dy);
        if (direction.magnitude() > 0) {
            velocity = direction.normalize().multiply(currentSpeed);
        } else {
            velocity = new Vector2D(0, 0);
        }
    }
    
    //Stops player movement
    public void stop() {
        velocity = new Vector2D(0, 0);
    }
    
    //Reduces player health by damage amount
    public void takeDamage(double damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }
    
    //Checks if player is still alive.
    public boolean isAlive() {
        return health > 0;
    }
    
    // Getters and Setters
    public Vector2D getPosition() {
        return position;
    }
    
    public int getHealth() {
        return health;
    }
    
    public int getMaxHealth() {
        return maxHealth;
    }
    
    public double getBaseDamage() {
        return baseDamage;
    }
    
    public double getBaseSpeed() {
        return baseSpeed;
    }
    
    public double getCurrentDamage() {
        return currentDamage;
    }
    
    public void setCurrentDamage(double currentDamage) {
        this.currentDamage = currentDamage;
    }
    
    public double getCurrentSpeed() {
        return currentSpeed;
    }
    
    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }
    
    public int getPierceCount() {
        return pierceCount;
    }
    
    public void setPierceCount(int pierceCount) {
        this.pierceCount = pierceCount;
    }
    
    public int getSize() {
        return PLAYER_SIZE;
    }
    
    public UpgradeStrategy getUpgradeStrategy() {
        return upgradeStrategy;
    }
}
