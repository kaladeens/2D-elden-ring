package game.gameactions.combatrelated;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.gamecapabilities.Status;

import java.util.Random;

/**
 * An Area attack action to attack another actor
 * Created by:
 * @author Sam Abdi
 * Modify by Issac van der Vliet
 */
public class AreaAttack extends Action {


    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;

    /**
     * Area attack without weapon
     */
    public AreaAttack(){}

    /**
     * Area attack with weapon
     * @param weapon the weapon that actor using
     */
    public AreaAttack(Weapon weapon){
        this.weapon=weapon;
    }



    /**
     * When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
     * the actor will hit the target. If so, deal damage to the target and determine whether the target is killed.
     *
     * @param actor The actor performing the attack action.
     * @param map The map the actor is on.
     * @return the result of the attack, e.g. whether the target is killed, etc.
     * @see DeathAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result="Area attack: " ;
        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }
        int count=0;
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination =exit.getDestination();
            if (destination.containsAnActor() && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                Actor target=destination.getActor();
                if (count == 0){
                    result += new AttackAction(target,exit.getName(),weapon).execute(actor,map) +" ";
                }
                else {
                    result += " And then "+new AttackAction(target, exit.getName(), weapon).execute(actor, map)+" ";
                }

                count ++;
            }

        }
        return result;
    }

    /**
     * Output the description on menu
     * When actor hold weapon, the verb of attacking will be swapped to correct one
     * @param actor The actor performing the action.
     * @return Actor performing description
     */
    @Override
    public String menuDescription(Actor actor) {
        String verb = "attacks";
        if (weapon!=null){
            verb= weapon.verb();
        }

        return actor + " " + verb + " everyone around them with " + (weapon != null ? weapon : "Intrinsic Weapon");
    }
}
