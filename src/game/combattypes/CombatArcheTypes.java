package game.combattypes;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * Class representing user player class that has been chosen in the start of the game.
 * Created by:
 * @author Lantao Yang
 * Modified by: Sam Abdi
 *
 */

public abstract class CombatArcheTypes {
    private String archeTypeName;
    private int hitPoint;
    private WeaponItem weapon;

    /**
     * Constructor:
     *
     * @param archeTypeName The name of Archetype in this game.
     * @param hitPoint The base hit point setting for each Archetype.
     * @param weapon The pre-set weapon for each Archetype
     */

    public CombatArcheTypes(String archeTypeName, int hitPoint, WeaponItem weapon){
        this.archeTypeName=archeTypeName;
        this.hitPoint=hitPoint;
        this.weapon=weapon;
    }

    /**
     * In the original abstract class like something about it updating the players stats and adding weapon to it
     * @param player The player updating players stats and adding weapon
     */
    public abstract void run(Actor player);

    /**
     * Get pre-set weapon from Class
     * @return weapon class
     */
    public WeaponItem getWeapon() {
        return weapon;
    }

    /**
     * Get pre-set Hit Point from Class
     * @return a int, e.g."414"
     */
    public int getHitPoint() {
        return hitPoint;
    }

    /**
     * Get name from class
     * @return a String, e.g. "Bandit"
     */
    public String getArcheTypeName(){return this.archeTypeName;}
}
