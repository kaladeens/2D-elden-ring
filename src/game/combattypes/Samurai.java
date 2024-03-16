package game.combattypes;

import edu.monash.fit2099.engine.actors.Actor;
import game.gameweapons.Uchigatana;

/**
 * A player class that can be select by user and return correct starting hit point and weapon.
 * It returns Uchigatana as starting weapon and 455 hit points
 * Created by:
 * @author Lantao Yang
 * Modified by:
 *
 */

public class Samurai extends CombatArcheTypes {
    /**
     * Constructor:
     */
    public Samurai() {
        super("Samurai", 455, new Uchigatana());
    }

    /**
     * Updating players stats and adding weapon
     * @param player The player updating players stats and adding weapon
     */
    public void run(Actor player){
        player.addWeaponToInventory(this.getWeapon());
        player.resetMaxHp(this.getHitPoint());
    }
}
