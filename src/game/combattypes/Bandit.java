package game.combattypes;

import edu.monash.fit2099.engine.actors.Actor;
import game.gameweapons.GreatKnife;

/**
 * A player class that can be select by user and return correct starting hit point and weapon.
 * It returns Great Knife as starting weapon and 414 hit points
 * Created by:
 * @author Lantao Yang
 * Modified by: Sam Abdi
 *
 */
public class Bandit extends CombatArcheTypes {
    /**
     * Bandit Player Class, with pre-set hit point and weapon.
     */
    public Bandit() {
        super("Bandit", 414, new GreatKnife());
    }

    /**
     * Updating players stats and adding weapon
     * @param player The player updating players stats and adding weapon
     */
    @Override
    public void run(Actor player) {
        player.addWeaponToInventory(this.getWeapon());
        player.resetMaxHp(this.getHitPoint());
    }
}
