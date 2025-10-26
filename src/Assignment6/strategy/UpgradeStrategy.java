package Assignment6.strategy;

import Assignment6.model.Player;

/**
 * Strategy Pattern Interface for modular player upgrades
 * This interface defines the contract for all upgrade strategies
 */
public interface UpgradeStrategy {
    //Applies the specific upgrade to the player
    void applyUpgrade(Player player);
    
    //Gets the name of this strategy
    String getStrategyName();
    
    //Gets a description of what this strategy does
    String getDescription();
}
