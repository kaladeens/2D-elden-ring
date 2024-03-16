package game.gameactions.combatrelated;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Utils.RandomNumberGenerator;
/**
 * Special Move of Uchigatana
 * Created by:
 * @author Sam Abdi
 */
public class UnsheathAttack extends AttackAction {

    private int hitAccuracy;

    /**
     * Constructor
     * @param hitAccuracy Special Move Hit Accuracy
     */
    public UnsheathAttack(Actor target, String direction, Weapon weapon,int hitAccuracy) {
        super(target, direction, weapon);
        this.hitAccuracy=hitAccuracy;
    }
    /**
     * Performing Unsheathe Special Move
     *
     * @param actor The actor performing the attack action.
     * @param map The map the actor is on.
     * @return message of action performing
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (!(RandomNumberGenerator.getBool(hitAccuracy))) { //in this attack it is 60 that is accuracy
            return actor + " misses " + getTarget() + ".";
        }

        int damage = getWeapon().damage()*2;
        String result = actor + " " + getWeapon().verb() + " " + getTarget() + " for " + damage + " damage.";
        getTarget().hurt(damage);
        if (!getTarget().isConscious()) {
            result += new DeathAction(actor).execute(getTarget(), map);
        }

        return result;
    }

    /**
     * Return message to the menu
     *
     * @param actor The actor performing the action.
     * @return menu selection Message with actor, target and weapon.
     */
    @Override
    public String menuDescription(Actor actor){
        return actor +" can perform Unsheathe attack on "+getTarget()+" with "+getWeapon()+" but has accuracy of "+hitAccuracy+"%";
    }
}
