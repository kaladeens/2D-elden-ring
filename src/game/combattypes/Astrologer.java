package game.combattypes;

import edu.monash.fit2099.engine.actors.Actor;
import game.gameweapons.Scimitar;

/**
 * A player class that can be select by user and return correct starting hit point and weapon.
 * It returns Scimitar as starting weapon and 396 hit points
 * Created by:
 * @author Issac van der Vliet
 * Modified by: Sam Abdi
 *
 */

public class Astrologer extends CombatArcheTypes {


    public Astrologer() {
        super("Astrologer", 396, new Scimitar());
    }
    @Override
    public void run(Actor player) {
        player.addWeaponToInventory(this.getWeapon());
        player.resetMaxHp(this.getHitPoint());
    }
}
