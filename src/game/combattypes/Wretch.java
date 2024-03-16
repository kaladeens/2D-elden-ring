package game.combattypes;

import edu.monash.fit2099.engine.actors.Actor;
import game.gameweapons.Club;

/**
 * A player class that can be select by user and return correct starting hit point and weapon.
 * It returns Club as starting weapon and 414 hit points
 * Created by:
 * @author Lantao Yang
 * Modified by:
 *
 */

public class Wretch extends CombatArcheTypes {
    /**
     * Constructors:
     */

    public Wretch() {
        super("Wretch", 414, new Club());
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
