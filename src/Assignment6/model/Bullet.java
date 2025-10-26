package Assignment6.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Bullet class representing projectiles fired by the player.
 * Contains position, velocity, damage, and pierce capabilities.
 */
public class Bullet {
    
    private static final double BULLET_SPEED = 10.0;
    private static final int BULLET_SIZE = 6;
    
    private Vector2D position;
    private Vector2D velocity;
    private double damage;
    private int pierceCount;
    private int pierceRemaining;
    private boolean active;
    private Set<Enemy> hitEnemies; // Track enemies already hit by this bullet
    
    //Creates a bullet at the given position, moving towards the target.
    public Bullet(double startX, double startY, double targetX, double targetY, double damage, int pierceCount) {
        this.position = new Vector2D(startX, startY);
        this.damage = damage;
        this.pierceCount = pierceCount;
        this.pierceRemaining = pierceCount;
        this.active = true;
        this.hitEnemies = new HashSet<>();
        
        // Calculate direction vector
        Vector2D direction = new Vector2D(targetX - startX, targetY - startY);
        this.velocity = direction.normalize().multiply(BULLET_SPEED);
    }
    
    //Updates bullet position based on velocity.
    public void update() {
        position = position.add(velocity);
    }
    
    //Checks if bullet can still pierce enemies.
    public boolean canPierce() {
        return pierceRemaining > 0;
    }
    
    //Checks if this enemy was already hit by this bullet.
    public boolean hasHitEnemy(Enemy enemy) {
        return hitEnemies.contains(enemy);
    }
    
    //Marks an enemy as hit by this bullet.
    public void markEnemyHit(Enemy enemy) {
        hitEnemies.add(enemy);
    }
    
    //Decrements the pierce counter when hitting an enemy.
    public void decrementPierce() {
        pierceRemaining--;
        if (pierceRemaining < 0) {
            active = false;
        }
    }
    
    //Checks if bullet is out of bounds.
    public boolean isOutOfBounds(int width, int height) {
        return position.getX() < 0 || position.getX() > width ||
               position.getY() < 0 || position.getY() > height;
    }
    
    // Getters
    public Vector2D getPosition() {
        return position;
    }
    
    public double getDamage() {
        return damage;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public int getSize() {
        return BULLET_SIZE;
    }
}
