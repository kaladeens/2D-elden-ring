package game.gameactions.combatrelated;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Utils.RandomNumberGenerator;

/**
 * Special Move of Great Knife
 * Created by:
 * @author Sam Abdi
 */

public class QuickStepAction extends AttackAction {



    public QuickStepAction(Actor target, String direction, Weapon weapon) {
        super(target,direction,weapon);
    }

    /**
     * Performing Quick Step Special Move
     *
     * @param actor The actor performing the attack action.
     * @param map The map the actor is on.
     * @return message of action performing
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (!(RandomNumberGenerator.getBool(getWeapon().chanceToHit()))) { //in this attack it is 60 that is accuracy
            return actor + " misses " + getTarget() + ".";
        }

        int damage = getWeapon().damage();
        String result = actor + " " + getWeapon().verb() + " " + getTarget() + " for " + damage + " damage.";
        getTarget().hurt(damage);
        if (!getTarget().isConscious()) {
            result += new DeathAction(actor).execute(getTarget(), map);
        }
        for(Exit exit: map.locationOf(actor).getExits()){
            if (!exit.getDestination().containsAnActor()&& exit.getDestination().canActorEnter(actor)){
                map.moveActor(actor,exit.getDestination());
                result+= actor +" and evades by moving to "+exit.getName();
                break;

            }
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
        return actor +" can do quickstep action on "+getTarget()+" with "+getWeapon() ;
    }
}
