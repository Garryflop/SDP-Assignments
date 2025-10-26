package Assignment6.model;

import java.awt.Color;

//Enemy class with health component
public class Enemy {
    
    private static final int ENEMY_SIZE = 30;
    private static final double MAX_HEALTH = 100.0;
    
    private Vector2D position;
    private double health;
    private double maxHealth;
    
    //Creates an enemy at the specified position.
    public Enemy(double x, double y) {
        this.position = new Vector2D(x, y);
        this.maxHealth = MAX_HEALTH;
        this.health = MAX_HEALTH;
    }
    
    //Reduces enemy health by the specified damage amount.
    public void takeDamage(double damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }
    
    //Checks if enemy is still alive.
    public boolean isAlive() {
        return health > 0;
    }
    
    //Gets health percentage for visual feedback.
    public double getHealthPercentage() {
        return health / maxHealth;
    }
    
    //Gets color based on health (green to red gradient).
    public Color getHealthColor() {
        float healthPercent = (float) getHealthPercentage();
        return new Color(1.0f - healthPercent, healthPercent, 0.0f);
    }
    
    // Getters
    public Vector2D getPosition() {
        return position;
    }
    
    public double getHealth() {
        return health;
    }
    
    public double getMaxHealth() {
        return maxHealth;
    }
    
    public int getSize() {
        return ENEMY_SIZE;
    }
}
