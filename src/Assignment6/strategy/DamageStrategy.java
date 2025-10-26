package Assignment6.strategy;

import Assignment6.model.Player;

/**
 * Concrete Strategy: Damage Upgrade
 * Increases the player's bullet damage by a multiplier.
 */
public class DamageStrategy implements UpgradeStrategy {
    
    private static final double DAMAGE_MULTIPLIER = 2.0;
    
    @Override
    public void applyUpgrade(Player player) {
        // Multiply current damage instead of replacing it
        double newDamage = player.getCurrentDamage() * DAMAGE_MULTIPLIER;
        player.setCurrentDamage(newDamage);
    }
    
    @Override
    public String getStrategyName() {
        return "Damage Upgrade";
    }
    
    @Override
    public String getDescription() {
        return "Doubles bullet damage (x" + DAMAGE_MULTIPLIER + ")";
    }
}
