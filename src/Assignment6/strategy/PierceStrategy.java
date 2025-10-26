package Assignment6.strategy;

import Assignment6.model.Player;

/**
 * Concrete Strategy: Pierce Upgrade
 * Allows bullets to pierce through multiple enemies.
 */
public class PierceStrategy implements UpgradeStrategy {
    
    private static final int PIERCE_COUNT = 3;
    
    @Override
    public void applyUpgrade(Player player) {
        // Add pierce count instead of replacing it
        player.setPierceCount(player.getPierceCount() + PIERCE_COUNT);
    }
    
    @Override
    public String getStrategyName() {
        return "Pierce Upgrade";
    }
    
    @Override
    public String getDescription() {
        return "Bullets pierce through " + PIERCE_COUNT + " enemies";
    }
}
