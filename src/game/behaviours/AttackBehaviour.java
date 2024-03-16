package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Utils.RandomNumberGenerator;
import game.gameactions.combatrelated.AreaAttack;
import game.gameactions.combatrelated.AttackAction;
import game.gamecapabilities.AttackType;
import game.gamecapabilities.Type;
import game.gamecapabilities.Status;

import java.util.ArrayList;
import java.util.Random;

/**
 * Attack Behavior for Actor
 * Created by:
 * @author Sam Abdi
 */
public class AttackBehaviour implements Behaviour{
    /**
     * The random number generator.
     */
    private final Random random = new Random();

    /**
     * Return correct attack action
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return correct attack action
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();
        Location here=map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor() && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
                if  (!destination.getActor().hasCapability(actor.findCapabilitiesByType(Type.class).get(0))){ //if they arent the same type
                    if (actor.hasCapability(AttackType.SPECIAL) && RandomNumberGenerator.getBool(50)) {
                        if (actor.getWeaponInventory().isEmpty() && actor.hasCapability(AttackType.AREA)) {
                            return new AreaAttack();
                        }
                        else{
                            Weapon weapon=actor.getWeaponInventory().get(0);
                            if (weapon.getSkill(actor)!=null){
                                return weapon.getSkill(actor);
                            }
                            else{
                                actions.add(new AttackAction(destination.getActor(), exit.getName(),weapon));
                            }

                        }

                    }
                    else{
                        if (!actor.getWeaponInventory().isEmpty()) {
                            actions.add(new AttackAction(destination.getActor(), exit.getName(),actor.getWeaponInventory().get(0)));
                        }
                        else{
                            actions.add(new AttackAction(destination.getActor(), exit.getName()));
                        }
                    }
                }

            }
        }
        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        }
        return null;
    }
}
