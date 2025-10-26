package Assignment6.strategy;

import Assignment6.model.Player;

/**
 * Concrete Strategy: Speed Upgrade
 * Increases the player's movement speed by a multiplier.
 */
public class SpeedStrategy implements UpgradeStrategy {
    
    private static final double SPEED_MULTIPLIER = 1.8;
    
    @Override
    public void applyUpgrade(Player player) {
        // Multiply current speed instead of replacing it
        double newSpeed = player.getCurrentSpeed() * SPEED_MULTIPLIER;
        player.setCurrentSpeed(newSpeed);
    }
    
    @Override
    public String getStrategyName() {
        return "Speed Upgrade";
    }
    
    @Override
    public String getDescription() {
        return "Increases movement speed (x" + SPEED_MULTIPLIER + ")";
    }
}
