package game.gamegrounds.specialground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactions.spawningrelated.SummonAction;

/**
 * Summon Sign on ground class
 * Created by:
 * @author Isaac van der Vliet
 */

public class SummonSign extends Ground {
    public SummonSign(){
        super('=');
    }

    /**
     * Summon action add to actor
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return success action message
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        actions.add(new SummonAction(location));
        return actions;
    }
}
